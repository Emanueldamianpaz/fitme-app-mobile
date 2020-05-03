package ar.davinci.edu.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.UserApi;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.home.HomeActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private Auth0 account;
    private Gson gson;

    @OnClick(R.id.btnLogin)
    public void login() {
        Helper.displayMessageToUser(LoginActivity.this, "Autenticando...", "Aguarde un momento...").show();

        WebAuthProvider
                .init(account)
                .withScope("openid profile email app_metadata")
                .start(LoginActivity.this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull Dialog dialog) {
                        Helper.displayMessageToUser(LoginActivity.this, "Error inesperado", "Ha ocurrido un error").show();
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Helper.displayMessageToUser(LoginActivity.this, "Login incorrecto", "Ha ocurrido un error en la autenticación").show();
                    }

                    @Override
                    public void onSuccess(@NonNull Credentials credentials) {
                        String idToken = credentials.getIdToken();

                        Call<ResponseBody> createSession = UserApi.createSession(idToken);
                        try {
                            createSession.execute();
                            PrefManager.write(PrefManager.CREDENTIAL_FITME, gson.toJson(idToken));
                            startActivity(Helper.getIntent(getBaseContext(), HomeActivity.class));
                        } catch (Exception ex) {
                            Helper.displayMessageToUser(LoginActivity.this, "Error inesperado", "Ha ocurrido un error").show();
                            ex.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Realm.init(getBaseContext());
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        PrefManager.initSharedPref(getApplicationContext());

        gson = new GsonBuilder().create();
        account = new Auth0(getBaseContext());

        if (PrefManager.read(PrefManager.CREDENTIAL_FITME, null) != null) {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}

package ar.davinci.edu.views.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.davinci.edu.R;
import ar.davinci.edu.api.clients.ApiClient;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    private Auth0 account;
    public static SharedPreferences sharedPreferences;
    private Gson gson;
    private Intent intent;
    final ApiClient apiClient = new ApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        context = getBaseContext();

        bootstrapping();

        Button loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(view -> {
            Toast.makeText(context, "Iniciando sesión...", Toast.LENGTH_LONG).show();
            WebAuthProvider
                    .init(account)
                    .withScope("openid profile email app_metadata")
                    .start(LoginActivity.this, new AuthCallback() {
                        @Override
                        public void onFailure(@NonNull Dialog dialog) {
                            Toast.makeText(context, "Login incorrecto!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(AuthenticationException exception) {
                            Toast.makeText(context, "Login incorrecto!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(@NonNull Credentials credentials) {
                            String idToken = credentials.getIdToken();
                            Call<ResponseBody> createSession = apiClient.createSession(idToken);

                            try {
                                Response<ResponseBody> response = createSession.execute();
                                response.toString();

                                PrefManager.write(PrefManager.CREDENTIAL_FITME, gson.toJson(idToken));
                                context.startActivity(intent);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Toast.makeText(context, "Login incorrecto!", Toast.LENGTH_LONG).show();
                            }


                        }
                    });
        });


    }

    private void bootstrapping() {

        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        PrefManager.initSharedPref(getApplicationContext());

        gson = new GsonBuilder().create();
        account = new Auth0(context);
        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);

        if (PrefManager.read(PrefManager.CREDENTIAL_FITME, null) != null) {
            context.startActivity(Helper.getIntent(context, HomeActivity.class));
        }

    }
}

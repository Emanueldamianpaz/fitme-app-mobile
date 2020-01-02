package ar.davinci.edu.activities;

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
import ar.davinci.edu.infraestructure.api.ApiClient;
import ar.davinci.edu.infraestructure.storage.SharedPreferencesManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    private Auth0 account;
    public static SharedPreferences sharedPreferences;
    private Gson gson;
    private Intent intent;
    final ApiClient apiClient = new ApiClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                                SharedPreferencesManager.write(SharedPreferencesManager.CREDENTIAL_FITME, gson.toJson(idToken));
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
        SharedPreferencesManager.init(getApplicationContext());
        gson = new GsonBuilder().create();
        context = getBaseContext();
        account = new Auth0(context);
        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (SharedPreferencesManager.read(SharedPreferencesManager.CREDENTIAL_FITME, null) != null) {
            context.startActivity(intent);
        }

    }
}

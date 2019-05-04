package ar.davinci.edu.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {
    private Context context;
    private Auth0 account;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gson = new GsonBuilder().create();

        context = getBaseContext();
        account = new Auth0(context);
        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);


        Button loginBtn = (Button) findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

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
                                Toast.makeText(context, "Sesión iniciada correctamente!", Toast.LENGTH_LONG).show();

                                sharedPreferences.edit()
                                        .putString("credential_fitme", gson.toJson(credentials))
                                        .apply();

                                context.startActivity(intent);
                            }
                        });
            }
        });


    }
}

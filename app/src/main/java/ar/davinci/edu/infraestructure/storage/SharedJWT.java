package ar.davinci.edu.infraestructure.storage;

import com.auth0.android.jwt.JWT;

public class SharedJWT {

    private SharedJWT() {
    }

    public static JWT getJWT() {
        return new JWT(PrefManager.read(PrefManager.CREDENTIAL_FITME, null));

    }
}
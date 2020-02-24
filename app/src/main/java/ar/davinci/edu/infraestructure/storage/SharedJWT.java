package ar.davinci.edu.infraestructure.storage;

import com.auth0.android.jwt.JWT;

import ar.davinci.edu.infraestructure.security.FitmeUser;

public class SharedJWT {

    private SharedJWT() {
    }

    public static JWT getJWT() {
        return new JWT(PrefManager.read(PrefManager.CREDENTIAL_FITME, null));

    }

    public static FitmeUser getUserFromSharedP() {

        JWT payload = SharedJWT.getJWT();

        return FitmeUser.builder()
                .id(payload.getSubject())
                .name(payload.getClaim("given_name").asString())
                .last_name(payload.getClaim("family_name").asString())
                .picture(payload.getClaim("picture").asString())
                .gender(payload.getClaim("gender").asString())
                .nickname(payload.getClaim("nickname").asString())
                .email(payload.getClaim("email").asString())
                .build();
    }


}
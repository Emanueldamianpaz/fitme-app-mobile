package ar.davinci.edu.clients.apis;

import android.content.Context;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.HttpClient;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.domain.model.user.detail.UserExperience;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface UserExperienceEndpoint {

    @GET("user/{id_user}/user-routine/{id_user_routine}/user-experience")
    Call<List<UserExperience>> getUserExperiencesFromUserRoutine(
            @Path(value = "id_user", encoded = true) String userId,
            @Path(value = "id_user_routine", encoded = true) String userRoutineId,
            @Header("Authorization") String jwt);

    @POST("user/{id_user}/user-routine/{id_user_routine}/user-experience")
    Call<UserExperience> createUserExperienceForUserRoutine(
            @Body UserExperience userExperience,
            @Path(value = "id_user", encoded = true) String userId,
            @Path(value = "id_user_routine", encoded = true) String userRoutineId,
            @Header("Authorization") String jwt);
}


public class UserExperienceApi {

    private static UserExperienceEndpoint apiClient = HttpClient.retrofit.create(UserExperienceEndpoint.class);
    private static String idUser = SharedJWT.getUserFromSharedP().getId();
    private static String token = SharedJWT.getJWT().toString();

    // -------------------------------------------------------------------------------- GET
    public static void getUserExperiencesFromUserRoutine(final OnSuccessCallback callback, Context context, String userRoutineId) {
        String message = context.getString(R.string.obtaining_user_experiences);
        HttpClient.doRequest(callback, context, message,
                apiClient.getUserExperiencesFromUserRoutine(idUser, userRoutineId, token)
        );
    }

    // -------------------------------------------------------------------------------- GET
    public static void createUserExperienceForUserRoutine(final OnSuccessCallback callback, Context context, String userRoutineId, UserExperience userExperience) {
        String message = context.getString(R.string.creating_user_experiences);
        HttpClient.doRequest(callback, context, message,
                apiClient.createUserExperienceForUserRoutine(userExperience, idUser, userRoutineId, token)
        );
    }

}

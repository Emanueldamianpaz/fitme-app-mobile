package ar.davinci.edu.clients.apis;

import android.content.Context;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.HttpClient;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

interface UserRoutineEndpoint {

    @GET("user/{id_user}/user-routine")
    Call<List<UserRoutine>> getListUserRoutines(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);

    @GET("user/{id_user}/user-routine/{id_user_routine}")
    Call<UserRoutine> getUserRoutine(
            @Path(value = "id_user", encoded = true) String userId,
            @Path(value = "id_user_routine", encoded = true) String userRoutineId,
            @Header("Authorization") String jwt);

}


public class UserRoutineApi {

    private static UserRoutineEndpoint apiClient = HttpClient.retrofit.create(UserRoutineEndpoint.class);
    private static String idUser = SharedJWT.getUserFromSharedP().getId();
    private static String token = SharedJWT.getJWT().toString();

    // -------------------------------------------------------------------------------- GET
    public static void getListUserRoutines(final OnSuccessCallback callback, Context context) {
        String message = context.getString(R.string.obtaining_routines);
        HttpClient.doRequest(callback, context, message, apiClient.getListUserRoutines(idUser, token));
    }

    // -------------------------------------------------------------------------------- GET
    public static void getUserRoutine(final OnSuccessCallback callback, Context context, String userRoutineId) {
        String message = context.getString(R.string.obtaining_routine_detail);
        HttpClient.doRequest(callback, context, message, apiClient.getUserRoutine(idUser, userRoutineId, token));
    }

}

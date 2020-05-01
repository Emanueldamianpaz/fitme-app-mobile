package ar.davinci.edu.clients.apis;

import android.content.Context;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.HttpClient;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.domain.dto.fitme.training.TrainingTotalStadistDTO;
import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

interface UserInfoEndpoint {

    @GET("user/{id_user}/info")
    Call<UserInfo> getUserInfo(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);

    @GET("user/{id_user}/info/training")
    Call<List<TrainingTotalStadistDTO>> getUserStadist(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);


    @PATCH("user/{id_user}/info")
    Call<UserInfo> updateUserInfo(
            @Body UserInfo userInfo,
            @Path("id_user") String userId,
            @Header("Authorization") String jwt
    );

}


public class UserInfoApi {

    private static UserInfoEndpoint apiClient = HttpClient.retrofit.create(UserInfoEndpoint.class);
    private static String idUser = SharedJWT.getUserFromSharedP().getId();
    private static String token = SharedJWT.getJWT().toString();


    public static void getUserInfo(final OnSuccessCallback callback, Context context) {
        String message = context.getString(R.string.obtaining_user_info);
        HttpClient.doRequest(callback, context, message, apiClient.getUserInfo(idUser, token));
    }

    public static void getUserStadist(final OnSuccessCallback callback, Context context) {
        String message = context.getString(R.string.obtaining_user_info_training);
        HttpClient.doRequest(callback, context, message, apiClient.getUserStadist(idUser, token));
    }


    // -------------------------------------------------------------------------------- GET
    public static void updateUserInfo(final OnSuccessCallback callback, Context context, UserInfo userInfoToUpdate) {
        String message = context.getString(R.string.updating_user_info);
        HttpClient.doRequest(callback, context, message, apiClient.updateUserInfo(userInfoToUpdate, idUser, token));
    }

}

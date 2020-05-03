package ar.davinci.edu.clients.apis;

import android.content.Context;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.HttpClient;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.domain.FitmeRoles;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoLightRequestDTO;
import ar.davinci.edu.domain.dto.fitme.user.UserSessionDTO;
import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface UserEndpoint {


    @GET("user/{id_user}")
    Call<UserInfo> getUser(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);

    @GET("user/{id_user}/light")
    Call<UserInfoLightRequestDTO> getUserLight(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);


    @POST("user/session")
    Call<ResponseBody> createSession(
            @Body UserSessionDTO userSession,
            @Header("Authorization") String jwt
    );

}


public class UserApi {

    private static UserEndpoint apiClient = HttpClient.retrofit.create(UserEndpoint.class);


    public static void getUserLight(final OnSuccessCallback callback, Context context) {
        String idUser = SharedJWT.getUserFromSharedP().getId();
        String token = SharedJWT.getJWT().toString();
        String message = context.getString(R.string.obtaining_user_info);
        HttpClient.doRequest(callback, context, message, apiClient.getUserLight(idUser, token));
    }


    public static Call<ResponseBody> createSession(String tokenId) {
        return apiClient.createSession(new UserSessionDTO(tokenId, FitmeRoles.CLIENT.toString()), tokenId);
    }


}

package ar.davinci.edu.clients.apis;

import android.content.Context;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.HttpClient;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.domain.model.training.TrainingSession;
import ar.davinci.edu.domain.model.training.detail.ExerciseRunningSession;
import ar.davinci.edu.domain.model.training.detail.NutritionSession;
import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface TrainingSessionEndpoint {

    @GET("api/training-session/{id_user}/info")
    Call<List<TrainingSession>> getTrainingSession(
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt);


    @POST("api/training-session/{id_user}/exercise")
    Call<UserInfo> addExerciseSession(
            @Body ExerciseRunningSession runningSession,
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt
    );

    @POST("api/training-session/{id_user}/nutrition")
    Call<UserInfo> addNutritionSession(
            @Body NutritionSession nutritionSession,
            @Path(value = "id_user", encoded = true) String userId,
            @Header("Authorization") String jwt
    );

}


public class TrainingSessionApi {

    private static TrainingSessionEndpoint apiClient = HttpClient.retrofit.create(TrainingSessionEndpoint.class);
    private static String idUser = SharedJWT.getUserFromSharedP().getId();
    private static String token = SharedJWT.getJWT().toString();


    public static void getTrainingSession(final OnSuccessCallback callback, Context context) {
        String message = context.getString(R.string.obtaining_exercise_running);
        HttpClient.doRequest(callback, context, message, apiClient.getTrainingSession(idUser, token));
    }


    public static void addExerciseSession(final OnSuccessCallback callback, Context context, ExerciseRunningSession exerciseRunningSession) {
        String message = context.getString(R.string.saving_running_session);
        HttpClient.doRequest(callback, context, message,
                apiClient.addExerciseSession(exerciseRunningSession, idUser, token)
        );
    }

    public static void addNutritionSession(final OnSuccessCallback callback, Context context, NutritionSession nutritionSession) {
        String message = context.getString(R.string.saving_nutrition_session);
        HttpClient.doRequest(callback, context, message,
                apiClient.addNutritionSession(nutritionSession, idUser, token)
        );
    }


}

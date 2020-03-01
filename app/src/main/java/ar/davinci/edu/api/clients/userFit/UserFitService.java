package ar.davinci.edu.api.clients.userFit;

import java.util.Set;

import ar.davinci.edu.api.dto.exercise_session.NutritionDTO;
import ar.davinci.edu.api.dto.scoring.TipDTO;
import ar.davinci.edu.api.dto.users.UserInfoDTO;
import ar.davinci.edu.api.dto.users.UserInfoLightDTO;
import ar.davinci.edu.api.dto.users.UserRoutineDTO;
import ar.davinci.edu.api.dto.users.UserSessionDTO;
import ar.davinci.edu.model.ExerciseSession;
import ar.davinci.edu.model.fitness.RunningSession;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFitService {


    // -------------------------------------------------------------------------------- GET

    @GET("user/{id}/info/light")
    Call<UserInfoLightDTO> getUserLight(@Header("Authorization") String token, @Path("id") String id);


    @GET("user/{id}/info/routines")
    Call<UserRoutineDTO> getUserRoutines(@Header("Authorization") String token, @Path(value = "id", encoded = true) String id);

    @GET("user/{id}/info/tip")
    Call<TipDTO> getUserTip(@Header("Authorization") String token, @Path("id") String id);

    @GET("api/exercise_session/{id}/info")
    Call<Set<ExerciseSession>> getExerciseSessions(@Path("id") String id);


    // -------------------------------------------------------------------------------- POST

    @POST("user/session")
    Call<ResponseBody> createSession(@Body UserSessionDTO userSession, @Header("Authorization") String token);


    @POST("api/exercise_session/{id_user}/exercise")
    Call<ResponseBody> addExerciseSession(@Body RunningSession exerciseSession, @Path("id_user") String id);

    @POST("api/exercise_session/{id_user}/nutrition")
    Call<ResponseBody> addNutritionSession(@Body NutritionDTO nutritionSession, @Path("id_user") String id);

    // -------------------------------------------------------------------------------- PATCH

    @PATCH("user/{id}/info/")
    Call<ResponseBody> updateUserInfo(@Body UserInfoDTO userInfo, @Header("Authorization") String token);


}


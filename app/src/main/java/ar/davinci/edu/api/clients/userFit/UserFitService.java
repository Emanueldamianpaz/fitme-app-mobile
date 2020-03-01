package ar.davinci.edu.api.clients.userFit;

import ar.davinci.edu.api.dto.exercise_session.NutritionDTO;
import ar.davinci.edu.api.dto.fitness.ExerciseRunningDTO;
import ar.davinci.edu.api.dto.fitness.TotalKilometersRunnedDTO;
import ar.davinci.edu.api.dto.scoring.TipDTO;
import ar.davinci.edu.api.dto.users.UserInfoDTO;
import ar.davinci.edu.api.dto.users.UserInfoLightDTO;
import ar.davinci.edu.api.dto.users.UserRoutineDTO;
import ar.davinci.edu.api.dto.users.UserSessionDTO;
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
    @GET("user/{id_user}/info/light")
    Call<UserInfoLightDTO> getUserLight(@Path("id_user") String id, @Header("Authorization") String jwt);

    @GET("user/{id_user}/info/routines")
    Call<UserRoutineDTO> getUserRoutines(@Path(value = "id_user", encoded = true) String id, @Header("Authorization") String jwt);

    @GET("user/{id_user}/info/tip")
    Call<TipDTO> getUserTip(@Path("id_user") String id, @Header("Authorization") String jwt);

    @GET("api/exercise_session/{id_user}/info")
    Call<ExerciseRunningDTO> getExerciseRunning(@Path("id_user") String id, @Header("Authorization") String jwt);

    @GET("api/exercise_session/{id_user}/info/kilometers")
    Call<TotalKilometersRunnedDTO> getTotalKilometersRunned(@Path("id_user") String id, @Header("Authorization") String jwt);

    // -------------------------------------------------------------------------------- POST

    @POST("user/session")
    Call<ResponseBody> createSession(@Body UserSessionDTO userSession, @Header("Authorization") String jwt);


    @POST("api/exercise_session/{id_user}/exercise")
    Call<ResponseBody> addExerciseSession(@Body RunningSession exerciseSession, @Path("id_user") String id, @Header("Authorization") String jwt);

    @POST("api/exercise_session/{id_user}/nutrition")
    Call<ResponseBody> addNutritionSession(@Body NutritionDTO nutritionSession, @Path("id_user") String id, @Header("Authorization") String jwt);

    // -------------------------------------------------------------------------------- PATCH

    @PATCH("user/{id_user}/info/")
    Call<ResponseBody> updateUserInfo(@Body UserInfoDTO userInfo, @Path("id_user") String id, @Header("Authorization") String jwt);


}


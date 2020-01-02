package ar.davinci.edu.infraestructure.api.userFit;

import java.util.Set;

import ar.davinci.edu.infraestructure.dto.exercise_session.ExerciseDTO;
import ar.davinci.edu.infraestructure.dto.exercise_session.NutritionDTO;
import ar.davinci.edu.infraestructure.dto.scoring.TipDTO;
import ar.davinci.edu.infraestructure.dto.users.UserInfoLightDTO;
import ar.davinci.edu.infraestructure.dto.users.UserRoutineDTO;
import ar.davinci.edu.infraestructure.dto.users.UserSessionDTO;
import ar.davinci.edu.infraestructure.model.ExerciseSession;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFitService {


    // -------------------------------------------------------------------------------- GET

    @GET("user/{id}/info/light")
    Call<UserInfoLightDTO> getUserLight(@Header("Authorization") String token, @Path("id") String id);


    @GET("user/{id}/info/routines")
    Call<UserRoutineDTO> getUserRoutines(@Header("Authorization") String token, @Path("id") String id);

    @GET("user/{id}/info/tip")
    Call<TipDTO> getUserTip(@Header("Authorization") String token, @Path("id") String id);

    @GET("api/exercise_session/{id}/info")
    Call<Set<ExerciseSession>> getExerciseSessions(@Path("id") String id);


    // -------------------------------------------------------------------------------- POST

    @POST("user/session")
    Call<ResponseBody> createSession(@Body UserSessionDTO userSession, @Header("Authorization") String token);


    @POST("api/exercise_session/{id}/exercise")
    Call<ResponseBody> addExerciseSession(@Body ExerciseDTO exerciseSession, @Path("id") String id);

    @POST("api/exercise_session/{id}/nutrition")
    Call<ResponseBody> addNutritionSession(@Body NutritionDTO nutritionSession, @Path("id") String id);

}


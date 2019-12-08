package ar.davinci.edu.infraestructure.api.userFit;

import java.util.Set;

import ar.davinci.edu.infraestructure.dto.ExerciseDTO;
import ar.davinci.edu.infraestructure.dto.NutritionDTO;
import ar.davinci.edu.infraestructure.model.ExerciseSession;
import ar.davinci.edu.infraestructure.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface UserFitService {

    @GET("user_info/:id")
    Call<User> getMyInfo(Long id);

    @GET("exercise_session/:id/info")
    Call<Set<ExerciseSession>> getExerciseSessions(Long id);

    @GET("exercise_session/:id/exercise")
    Call<ResponseBody> addExerciseSession(@Body ExerciseDTO exerciseSession, Long id);

    @GET("exercise_session/:id/nutrition")
    Call<ResponseBody> addNutritionSession(@Body NutritionDTO nutritionSession, Long id);

}


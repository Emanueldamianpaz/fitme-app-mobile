package ar.davinci.edu.infraestructure.api.userFit;

import ar.davinci.edu.infraestructure.model.User;
import ar.davinci.edu.infraestructure.model.UserFit;
import ar.davinci.edu.infraestructure.model.dto.ExerciseDTO;
import ar.davinci.edu.infraestructure.model.dto.NutritionDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface UserFitService {

    @GET("user/:id")
    Call<User> getMyInfo(Long id);

    @GET("user_fit/:id/info")
    Call<UserFit> getInfoFitSession(Long id);

    @GET("user_fit/:id/exercise")
    Call<ResponseBody> addExerciseSession(@Body ExerciseDTO exerciseSession, Long id);

    @GET("user_fit/:id/nutrition")
    Call<ResponseBody> addNutritionSession(@Body NutritionDTO nutritionSession, Long id);

}

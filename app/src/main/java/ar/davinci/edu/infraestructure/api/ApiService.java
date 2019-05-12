package ar.davinci.edu.infraestructure.api;


import java.util.List;

import ar.davinci.edu.infraestructure.model.RoutineDTO;
import ar.davinci.edu.infraestructure.model.UserFit;
import ar.davinci.edu.infraestructure.model.UserRoutine;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("routine")
    Call<List<RoutineDTO>> getRoutines();


    @GET("user_routine/:id")
    Call<UserRoutine> getUserRoutine();

    @GET("demo/users")
    Call<ResponseBody> getUsers();

    @POST("/user_info/")
    @FormUrlEncoded
    Call<UserFit> postUserExerciseSession(@Body UserFit exerciseSession);
}

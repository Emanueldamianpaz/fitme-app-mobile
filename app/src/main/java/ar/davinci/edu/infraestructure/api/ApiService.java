package ar.davinci.edu.infraestructure.api;


import java.util.List;

import ar.davinci.edu.infraestructure.model.RoutineDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("routine")
    Call<List<RoutineDTO>> getRoutines();


    @GET("demo/users")
    Call<ResponseBody> getUsers();


}

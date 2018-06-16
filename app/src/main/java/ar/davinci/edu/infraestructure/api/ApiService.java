package ar.davinci.edu.infraestructure.api;


import ar.davinci.edu.infraestructure.models.Flight;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("demo/flightList")
    Call<List<Flight>> getFlightList();

    @GET("demo/users")
    Call<ResponseBody> getUsers();

}

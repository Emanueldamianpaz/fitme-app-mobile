package ar.davinci.edu.infraestructure.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("demo/users")
    Call<ResponseBody> getUsers();

}

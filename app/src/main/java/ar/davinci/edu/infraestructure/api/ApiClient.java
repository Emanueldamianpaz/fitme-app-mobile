package ar.davinci.edu.infraestructure.api;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import ar.davinci.edu.infraestructure.models.Flight;
import ar.davinci.edu.infraestructure.models.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit;
    private ApiService service;
    private String responseRequest;
    private Gson jsonParser;
    private Context context;


    public ApiClient(Context context) {
        this.context = context;
        jsonParser = new Gson();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/emanueldamianpaz/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        service = retrofit.create(ApiService.class);
    }

    public boolean getUser(String username, String password) {

        Call<ResponseBody> result = service.getUsers();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boolean authOk = false;
        try {
            responseRequest = result.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        User[] users = jsonParser.fromJson(responseRequest, User[].class);

        if (users != null) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    authOk = true;
                }
            }
        } else {
            Toast.makeText(context, "Error del sistema!", Toast.LENGTH_LONG).show();
            authOk = false;

        }
        return authOk;
    }


    public void getListFlights(final OnSuccessCallback callback) {

        Call<List<Flight>> result = service.getFlightList();

        result.enqueue(new Callback<List<Flight>>() {
            @Override
            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<Flight>> call, Throwable throwable) {
                Toast.makeText(context, "Fallo al querer conectarse con el servidor", Toast.LENGTH_SHORT).show();
            }

        });

    }
}

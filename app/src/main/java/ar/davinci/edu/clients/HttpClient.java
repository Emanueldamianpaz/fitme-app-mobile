package ar.davinci.edu.clients;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.callback.OnSuccessCallback;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static Gson jsonParser = new Gson();
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.8:4567/fitme/") // TODO Modificar esto
            .addConverterFactory(GsonConverterFactory.create(jsonParser))
            .build();


    public static <T> void doRequest(final OnSuccessCallback callback, Context context, String message, Call<T> call) {
        ProgressDialog progressDialog = Helper.displayProgressDialog(
                context, true, message
        );
        progressDialog.show();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                progressDialog.dismiss();
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                progressDialog.dismiss();
                Helper.displayMessageToUser(context, context.getString(R.string.unexpected_error), context.getString(R.string.has_ocurried_an_error)).show();
                context.startActivity(Helper.getIntent(context, HomeActivity.class));
            }
        });
    }
}

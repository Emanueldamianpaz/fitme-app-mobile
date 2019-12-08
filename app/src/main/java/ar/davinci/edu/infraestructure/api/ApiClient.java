package ar.davinci.edu.infraestructure.api;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Set;

import ar.davinci.edu.infraestructure.api.userFit.UserFitService;
import ar.davinci.edu.infraestructure.model.User;
import ar.davinci.edu.infraestructure.model.ExerciseSession;
import ar.davinci.edu.infraestructure.model.dto.ExerciseDTO;
import ar.davinci.edu.infraestructure.model.dto.NutritionDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit;
    private UserFitService userFitService;
    private Context context;


    public ApiClient(Context context) {
        this.context = context;
        Gson jsonParser = new Gson();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fitme-app.herokuapp.com/fitme/api/")
                .addConverterFactory(GsonConverterFactory.create(jsonParser))
                .build();

        userFitService = retrofit.create(UserFitService.class);
    }

    public void getMyInfoFitSession(final OnSuccessCallback callback, Long id) {
        Call<Set<ExerciseSession>> infoFitSession = userFitService.getExerciseSessions(id);
        infoFitSession.enqueue(new Callback<Set<ExerciseSession>>() {
            @Override
            public void onResponse(Call<Set<ExerciseSession>> call, Response<Set<ExerciseSession>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<Set<ExerciseSession>> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public void getMyRoutines(final OnSuccessCallback callback, Long id) {
        Call<User> infoFitSession = userFitService.getMyInfo(id);
        infoFitSession.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public void addExerciseSession(Long id, ExerciseDTO exercise, final OnSuccessCallback callback) {

        Call<ResponseBody> infoFitSession = userFitService.addExerciseSession(exercise, id);
        infoFitSession.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public void addNutritionSession(final OnSuccessCallback callback, Long id, NutritionDTO nutrition) {

        Call<ResponseBody> infoFitSession = userFitService.addNutritionSession(nutrition, id);
        infoFitSession.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }


}

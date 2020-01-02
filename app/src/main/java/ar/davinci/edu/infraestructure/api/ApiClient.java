package ar.davinci.edu.infraestructure.api;

import android.content.Context;

import com.google.gson.Gson;

import ar.davinci.edu.infraestructure.api.userFit.UserFitService;
import ar.davinci.edu.infraestructure.dto.exercise_session.ExerciseDTO;
import ar.davinci.edu.infraestructure.dto.exercise_session.NutritionDTO;
import ar.davinci.edu.infraestructure.dto.scoring.TipDTO;
import ar.davinci.edu.infraestructure.dto.users.UserInfoLightDTO;
import ar.davinci.edu.infraestructure.dto.users.UserRoutineDTO;
import ar.davinci.edu.infraestructure.dto.users.UserSessionDTO;
import ar.davinci.edu.infraestructure.model.types.FitmeRoles;
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
                // TODO Modificar esto
                //.baseUrl("https://fitme-app.herokuapp.com/fitme/api/")
                .baseUrl("http://192.168.0.9:4567/fitme/")
                .addConverterFactory(GsonConverterFactory.create(jsonParser))
                .build();

        userFitService = retrofit.create(UserFitService.class);
    }


    // -------------------------------------------------------------------------------- GET


    public void getUserLight(final OnSuccessCallback callback, String tokenId, String userId) {
        Call<UserInfoLightDTO> getUserLight = userFitService.getUserLight(tokenId, userId);

        getUserLight.enqueue(new Callback<UserInfoLightDTO>() {
            @Override
            public void onResponse(Call<UserInfoLightDTO> call, Response<UserInfoLightDTO> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<UserInfoLightDTO> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public void getUserRoutines(final OnSuccessCallback callback, String tokenId, String userId) {
        Call<UserRoutineDTO> getUserRoutines = userFitService.getUserRoutines(tokenId, userId);

        getUserRoutines.enqueue(new Callback<UserRoutineDTO>() {
            @Override
            public void onResponse(Call<UserRoutineDTO> call, Response<UserRoutineDTO> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<UserRoutineDTO> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public void getUserTip(final OnSuccessCallback callback, String tokenId, String userId) {
        Call<TipDTO> getUserTip = userFitService.getUserTip(tokenId, userId);

        getUserTip.enqueue(new Callback<TipDTO>() {
            @Override
            public void onResponse(Call<TipDTO> call, Response<TipDTO> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<TipDTO> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }


    // -------------------------------------------------------------------------------- POST

    public Call<ResponseBody> createSession(String tokenId) {
        return userFitService.createSession(new UserSessionDTO(tokenId, FitmeRoles.CLIENT.toString()), tokenId);
    }

    public void addExerciseSession(ExerciseDTO exercise, final OnSuccessCallback callback, String userId) {

        Call<ResponseBody> infoFitSession = userFitService.addExerciseSession(exercise, userId);
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

    public void addNutritionSession(final OnSuccessCallback callback, NutritionDTO nutrition, String userId) {

        Call<ResponseBody> infoFitSession = userFitService.addNutritionSession(nutrition, userId);
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

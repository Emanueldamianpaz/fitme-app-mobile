package ar.davinci.edu.api.clients;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import ar.davinci.edu.api.clients.userFit.UserFitService;
import ar.davinci.edu.api.dto.exercise_session.NutritionDTO;
import ar.davinci.edu.api.dto.fitness.ExerciseRunningDTO;
import ar.davinci.edu.api.dto.fitness.TotalKilometersRunnedDTO;
import ar.davinci.edu.api.dto.scoring.TipDTO;
import ar.davinci.edu.api.dto.users.UserInfoDTO;
import ar.davinci.edu.api.dto.users.UserInfoLightDTO;
import ar.davinci.edu.api.dto.users.UserRoutineDTO;
import ar.davinci.edu.api.dto.users.UserSessionDTO;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.model.fitness.RunningSession;
import ar.davinci.edu.model.types.FitmeRoles;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Gson jsonParser = new Gson();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://fitme-app.herokuapp.com/fitme/")
            .addConverterFactory(GsonConverterFactory.create(jsonParser))
            .build();
    private static UserFitService userFitService = retrofit.create(UserFitService.class);

    // -------------------------------------------------------------------------------- GET
    public static void getUserLight(final OnSuccessCallback callback, Context context) {
        ProgressDialog progressDialog = Helper.displayProgressDialog(context, true, "Obteniendo informacion del usuario");
        progressDialog.show();

        Call<UserInfoLightDTO> getUserLight = userFitService.getUserLight(
                SharedJWT.getUserFromSharedP().getId(),
                SharedJWT.getJWT().toString()
        );

        getUserLight.enqueue(new Callback<UserInfoLightDTO>() {
            @Override
            public void onResponse(Call<UserInfoLightDTO> call, Response<UserInfoLightDTO> response) {
                progressDialog.dismiss();

                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<UserInfoLightDTO> call, Throwable throwable) {
                progressDialog.dismiss();
                Helper.displayMessageToUser(context, "Error inesperado", "Ha ocurrido un error").show();

                callback.error(throwable);
            }
        });
    }

    public static void getUserRoutines(final OnSuccessCallback callback, Context context) {
        Call<UserRoutineDTO> getUserRoutines = userFitService.getUserRoutines(
                SharedJWT.getUserFromSharedP().getId(),
                SharedJWT.getJWT().toString());

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

    public static void getExerciseRunning(final OnSuccessCallback callback, String userId, String tokenId, Context context) {

        Call<ExerciseRunningDTO> getExerciseRunning = userFitService.getExerciseRunning(userId, tokenId);

        getExerciseRunning.enqueue(new Callback<ExerciseRunningDTO>() {
            @Override
            public void onResponse(Call<ExerciseRunningDTO> call, Response<ExerciseRunningDTO> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<ExerciseRunningDTO> call, Throwable throwable) {

                callback.error(throwable);
            }
        });
    }

    public static void getTotalKilometersRunned(String userId, final OnSuccessCallback callback, String tokenId) {
        Call<TotalKilometersRunnedDTO> getTotalKilometersRunned = userFitService.getTotalKilometersRunned(tokenId, userId);

        getTotalKilometersRunned.enqueue(new Callback<TotalKilometersRunnedDTO>() {
            @Override
            public void onResponse(Call<TotalKilometersRunnedDTO> call, Response<TotalKilometersRunnedDTO> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<TotalKilometersRunnedDTO> call, Throwable throwable) {
                callback.error(throwable);
            }
        });
    }

    public static void getUserTip(final OnSuccessCallback callback, String tokenId, String userId) {
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

    public static Call<ResponseBody> createSession(String tokenId) {
        return userFitService.createSession(new UserSessionDTO(tokenId, FitmeRoles.CLIENT.toString()), tokenId);
    }

    public static void addExerciseSession(RunningSession exercise, final OnSuccessCallback callback) {

        Call<ResponseBody> infoFitSession = userFitService.addExerciseSession(
                exercise,
                SharedJWT.getUserFromSharedP().getId(),
                SharedJWT.getJWT().toString()
        );

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

    public static void addNutritionSession(final OnSuccessCallback callback, NutritionDTO nutrition) {

        Call<ResponseBody> infoFitSession = userFitService.addNutritionSession(
                nutrition,
                SharedJWT.getUserFromSharedP().getId(),
                SharedJWT.getJWT().toString()

        );
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

    // -------------------------------------------------------------------------------- POST

    public static void updateUserInfo(UserInfoDTO userInfoPatch, final OnSuccessCallback callback) {
        Call<ResponseBody> userInfoRequest = userFitService.updateUserInfo(
                userInfoPatch,
                SharedJWT.getUserFromSharedP().getId(),
                SharedJWT.getJWT().toString()
        );
        userInfoRequest.enqueue(new Callback<ResponseBody>() {
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

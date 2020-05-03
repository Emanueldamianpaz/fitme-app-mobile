package ar.davinci.edu.infraestructure.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.davinci.edu.R;

public class Helper {

    public static final String ACTION_NAME_SPACE = "ar.davinci.edu.infraestructure.service.LocationService";
    public static final String INTENT_EXTRA_RESULT_CODE = "resultCode";
    public static final String INTENT_USER_LAT_LNG = "userLatLng";
    public static final Gson gson = new GsonBuilder().create();

    public static AlertDialog displayMessageToUser(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null);
        return builder.create();
    }

    public static ProgressDialog displayProgressDialog(Context context, boolean cancelable, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static Intent getIntent(Context context, Class<?> goToActivity) {
        Intent intent = new Intent(context, goToActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        return intent;
    }

    public static void changeFragments(AppCompatActivity context, Fragment fragment) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentMain, fragment)
                .commit();
    }

    public static int secondToMinuteConverter(long seconds) {
        return (int) seconds / 60;
    }


    public static float calculatePace(long time, float distance) {
        return secondToMinuteConverter(time) / distance; // 1000 feet -> 304.8
    }

    public static String secondToHHMMSS(long secondsCount) {
        long seconds = secondsCount % 60;
        secondsCount -= seconds;
        long minutesCount = secondsCount / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;
        long hoursCount = minutesCount / 60;
        return "" + hoursCount + ":" + minutes + ":" + seconds;
    }
}

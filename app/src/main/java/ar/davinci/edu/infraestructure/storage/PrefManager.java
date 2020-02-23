package ar.davinci.edu.infraestructure.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class PrefManager {

    public static final String USER_ID = "user_id";
    public static final String USER_WALK = "user_walk";
    public static final String CREDENTIAL_FITME = "credential_fitme";

    private static SharedPreferences PrefController;

    public static void initSharedPref(Context context) {
        PrefController = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    private static SharedPreferences getAuthCredentials() {
        return PrefController;
    }

    private static SharedPreferences.Editor editSharedPrefs() {
        return getAuthCredentials().edit();
    }

    @Nullable
    public static String getID(String key) {
        return PrefController.getString(key, null);
    }

    public static boolean getUserWalk(String key) {
        return PrefController.getBoolean(key, false);
    }

    public static void setID(String key, String value) {
        editSharedPrefs().putString(key, value).commit();
    }

    public static void setUserWalk(String key, boolean value) {
        editSharedPrefs().putBoolean(key, value).commit();
    }

    public static boolean isAuthorized() {
        return getID(USER_ID) != null;
    }

    public static boolean isUserWalking() {
        return getUserWalk(USER_WALK);
    }

    public static String read(String key, String defValue) {
        return PrefController.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = PrefController.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static void removeSession() {
        SharedPreferences.Editor prefsEditor = PrefController.edit();
        prefsEditor.putString(PrefManager.CREDENTIAL_FITME, "");
        prefsEditor.commit();
    }
    public static boolean read(String key, boolean defValue) {
        return PrefController.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = PrefController.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return PrefController.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = PrefController.edit();
        prefsEditor.putInt(key, value).commit();
    }

}

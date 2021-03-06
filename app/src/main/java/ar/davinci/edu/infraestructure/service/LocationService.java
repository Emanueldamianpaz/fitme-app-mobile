package ar.davinci.edu.infraestructure.service;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.fitness.DispatchActivity;

public class LocationService extends Service implements LocationProvider.LocationCallback {

    //private static final String TAG = Location.class.getSimpleName();
    private static final int NOTIFICATION_ID = 11;

    private LocationProvider mLocationProvider;
    private boolean isUserWalking;
    private Location mCurrentLocation;
    private Location mPreviousLocation;
    private boolean isBroadcastAllow;
    private float mDistanceCovered;
    private int sizeSample;
    private float mSpeedAvg;
    private long startTime;

    private IBinder mIBinder = new LocalBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        mLocationProvider = new LocationProvider(this, this);
        mLocationProvider.connect();
        isUserWalking = false;
        mSpeedAvg = 0;
        startTime = 0;
        sizeSample = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void handleInitialLocation(Location location) {
        mCurrentLocation = location;
    }

    @Override
    public void handleNewLocation(Location location) {
        //Log.e(TAG, " getting new location");
        mCurrentLocation = location;
        calculateDistance();
        broadCastLocation(mCurrentLocation);
    }

    private void calculateDistance() {
        if (isUserWalking) {
            float distanceDiff = mPreviousLocation.distanceTo(mCurrentLocation); // Return meter unit

            mSpeedAvg = mSpeedAvg + mCurrentLocation.getSpeed(); // meters/seconds
            sizeSample = sizeSample + 1;

            mDistanceCovered = mDistanceCovered + distanceDiff;
            mPreviousLocation = mCurrentLocation;
        }
    }

    public void startBroadcasting() {
        //Log.e(TAG, " start broadcast");
        isBroadcastAllow = true;
        broadcastFirstLocation();
    }

    private void broadcastFirstLocation() {
        if (mCurrentLocation != null) {
            broadCastLocation(mCurrentLocation);
        }
    }

    public void stopBroadcasting() {
        //Log.e(TAG, " stop broadcast");
        isBroadcastAllow = false;
    }

    @Override
    public void onDestroy() {
        //Log.e(TAG, "onDestroy");
        isUserWalking = false;
        mLocationProvider.disconnect();

    }


    private void broadCastLocation(Location location) {

        if (isBroadcastAllow) {
            broadcastUserLocation(location);
        }

    }

    private void broadcastUserLocation(Location location) {

        Intent in = new Intent(Helper.ACTION_NAME_SPACE);
        in.putExtra(Helper.INTENT_EXTRA_RESULT_CODE, Activity.RESULT_OK);
        in.putExtra(Helper.INTENT_USER_LAT_LNG, location);
        LocalBroadcastManager.getInstance(this).sendBroadcast(in);
    }

    public long elapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public float speedAvg() {
        return mSpeedAvg / sizeSample;
    }

    public float distanceCovered() {
        return mDistanceCovered;
    }

    public boolean isUserWalking() {
        return isUserWalking;
    }

    public void startUserWalk() {
        if (!isUserWalking) {
            startTime = System.currentTimeMillis();
            mPreviousLocation = mCurrentLocation;
            mDistanceCovered = 0;
            mSpeedAvg = 0;
            sizeSample = 0;
            isUserWalking = true;
        }
    }

    public void stopUserWalk() {
        if (isUserWalking) {
            isUserWalking = false;
        }
    }

    public Location getUserLocation() {
        return mCurrentLocation;
    }


    public class LocalBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }

    // Prevent system killing the background service
    public void startForeground() {
        startForeground(NOTIFICATION_ID, createNotification());
    }

    public void stopNotification() {
        stopForeground(true);
    }


    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Siga caminando")
                .setContentText("Presione para retomar actividad")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent resultIntent = new Intent(this, DispatchActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(this, 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        return builder.build();
    }


}

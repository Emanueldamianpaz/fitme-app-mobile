package ar.davinci.edu.infraestructure.tracker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import ar.davinci.edu.activities.RunningActivity;

public class GPSTracker extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {

    private static final String LOGTAG = "android-localizacion";

    private static final int PETITION_PERMISSION_LOCALIZATION = 101;
    private static final int PETITION_CONF_UBICATION = 201;

    public GoogleApiClient apiClient;
    private Activity context;
    private TrackerKML trackerKML;

    private LocationRequest locRequest;

    public GPSTracker(Activity context, TrackerKML tracker) {
        this.context = context;
        this.trackerKML = tracker;

        apiClient = new GoogleApiClient.Builder(this.context)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        apiClient.connect();
    }


    public void toggleLocationUpdates(boolean enable) {
        if (enable) {
            enableLocationUpdates();
        } else {
            disableLocationUpdates();
        }
    }

    /**
     * TODO método importante
     * Se configuran los updates entre otras cosas
     */
    private void enableLocationUpdates() {

        locRequest = new LocationRequest();

        // TODO Sensibilidad
        locRequest.setInterval(1000);
        locRequest.setFastestInterval(600);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest locSettingsRequest =
                new LocationSettingsRequest.Builder()
                        .addLocationRequest(locRequest)
                        .build();

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        apiClient, locSettingsRequest);

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(LOGTAG, "Configuration ok!");
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            Log.i(LOGTAG, "User attention is required...");
                            status.startResolutionForResult(context, PETITION_CONF_UBICATION);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(LOGTAG, "Error to try fix location configuration");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(LOGTAG, "Can't apply configuration of necessary location");
                        break;
                }
            }
        });
    }

    private void disableLocationUpdates() {

        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.i(LOGTAG, "Begin of tracking locations");
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locRequest, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.e(LOGTAG, "Error to connect with Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this.context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETITION_PERMISSION_LOCALIZATION);
        } else {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(LOGTAG, "Interrupted connection with Google Play Services");
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            trackerKML.addPoint(loc.getLatitude(), loc.getLongitude(), loc.getAltitude());
        } else {
            // Coordinates unknows
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETITION_PERMISSION_LOCALIZATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                @SuppressWarnings("MissingPermission")
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                updateUI(lastLocation);
            } else {
                Log.e(LOGTAG, "Permission denied");
                RunningActivity.btnGPS.setChecked(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PETITION_CONF_UBICATION:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(LOGTAG, "Users no apply changes of neccesary configuration");
                        break;
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateUI(location);
    }
}

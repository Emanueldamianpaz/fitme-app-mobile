package ar.davinci.edu.views.fragments.fitness;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.WeakReference;

import ar.davinci.edu.R;
import ar.davinci.edu.api.clients.ApiClient;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.model.fitness.RunningSession;
import ar.davinci.edu.service.LocationService;
import ar.davinci.edu.views.activities.fitness.DispatchActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class WalkFragment extends Fragment implements OnMapReadyCallback {

    //private static final String TAG = WalkActivity.class.getSimpleName();
    private final static int MSG_UPDATE_TIME = 0;

    @BindView(R.id.txtDistance)
    TextView mDistanceTextView;
    @BindView(R.id.txtTime)
    TextView mTimerTextView;
    @BindView(R.id.start_stop_walk_btn)
    Button mWalkBtn;

    private GoogleMap mGoogleMap;
    private LocationService mLocationService;
    private boolean isServiceBound;
    private Marker mLocationMarker;
    private Realm mRealm;

    private final Handler mUIUpdateHandler = new UIUpdateHandler(this);


    public WalkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_walk, container, false);
        ButterKnife.bind(this, v);


        mRealm = Realm.getDefaultInstance();
        setUpMap();


        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        return v;
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            //Log.e(TAG, "Service bound");
            isServiceBound = true;
            LocationService.LocalBinder localBinder = (LocationService.LocalBinder) binder;
            mLocationService = localBinder.getService();

            if (mLocationService.isUserWalking()) {
                updateStartWalkUI();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceBound = false;
        }
    };

    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra(Helper.INTENT_EXTRA_RESULT_CODE, RESULT_CANCELED);

            if (resultCode == RESULT_OK) {
                Log.i("New marker", "New position register");
                Location userLocation = intent.getParcelableExtra(Helper.INTENT_USER_LAT_LNG);
                LatLng latLng = getLatLng(userLocation);
                updateUserMarkerLocation(latLng);
            }
        }
    };

    private void updateUserMarkerLocation(LatLng latLng) {
        mLocationMarker.setPosition(latLng);
    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(Helper.ACTION_NAME_SPACE);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(locationReceiver, intentFilter);
        startLocationService();
    }

    @Override
    public void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(locationReceiver);

        if (isServiceBound) {

            mLocationService.stopBroadcasting();
            if (!mLocationService.isUserWalking()) {
                stopLocationService();
            }
        }
        updateStopWalkUI();
        getContext().unbindService(mServiceConnection);
        isServiceBound = false;
        mRealm.close();
    }

    private void setUpMap() {
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map_fragment);

        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.start_stop_walk_btn)
    public void walkActivityBtnClick(Button button) {
        if (isServiceBound && !mLocationService.isUserWalking()) {
            initializeWalkService();
            updateWalkPref(true);
            updateStartWalkUI();
        } else if (isServiceBound && mLocationService.isUserWalking()) {
            stopWalkService();
            updateStopWalkUI();
            updateWalkPref(false);

            saveWalkData(mLocationService.distanceCovered(), mLocationService.elapsedTime(), mLocationService.speedAvg());
        }
    }

    private void initializeWalkService() {
        mLocationService.startUserWalk();
        mLocationService.startBroadcasting();
        mLocationService.startForeground();
    }

    private void stopWalkService() {
        mLocationService.stopUserWalk();
        mLocationService.stopNotification();
    }

    private void saveWalkData(final float distanceWalked, final long timeWalked, final float speedAvg) {
        AlertDialog.Builder saveBuilder = new AlertDialog.Builder(getContext());
        saveBuilder.setTitle(getString(R.string.save_walk_data_title));
        saveBuilder.setMessage(getString(R.string.save_walk_data_message));
        saveBuilder.setNegativeButton(getString(R.string.dismiss_walk_data), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                goToDispatchActivity();
            }
        });
        saveBuilder.setPositiveButton(getString(R.string.save_walk_data), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RunningSession runningSession = new RunningSession(
                        timeWalked,
                        distanceWalked,
                        Helper.calculatePace(timeWalked, distanceWalked),
                        speedAvg
                );


                ApiClient.addExerciseSession(
                        runningSession,
                        body -> {
                            Log.i("", "");
                        },
                        getContext()
                );

                goToDispatchActivity();
            }
        });

        saveBuilder.setCancelable(false);
        saveBuilder.create().show();
    }

    private void updateWalkPref(boolean isUserWalk) {
        PrefManager.setUserWalk(PrefManager.USER_WALK, isUserWalk);
    }

    private void updateStopWalkUI() {
        if (mUIUpdateHandler.hasMessages(MSG_UPDATE_TIME)) {
            mUIUpdateHandler.removeMessages(MSG_UPDATE_TIME);
            mWalkBtn.setText(R.string.start_walk);
        }
    }

    private void updateStartWalkUI() {
        mUIUpdateHandler.sendEmptyMessage(MSG_UPDATE_TIME);
        mWalkBtn.setText(R.string.stop_walk);
    }

    private void startLocationService() {
        Intent intent = new Intent(getContext(), LocationService.class);
        getContext().startService(intent);
        getContext().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void stopLocationService() {
        Intent intentService = new Intent(getContext(), LocationService.class);
        getContext().stopService(intentService);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                updateUserMapLocation();
            }
        });
    }

    private void updateUserMapLocation() {
        if (isServiceBound) {
            Location userLocation = mLocationService.getUserLocation();
            LatLng latLng = getLatLng(userLocation);
            zoomIn(latLng);
            initializeLocationMarker(latLng);
            mLocationService.startBroadcasting();
        }
    }

    private LatLng getLatLng(Location userLocation) {
        return new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
    }

    private void initializeLocationMarker(LatLng latLngMarker) {
        MarkerOptions options = new MarkerOptions()
                .position(latLngMarker)
                .title(getString(R.string.my_location));
        mLocationMarker = mGoogleMap.addMarker(options);
    }

    private void zoomIn(LatLng latLngZoom) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngZoom, 16));
    }

    private void updateUI() {
        if (isServiceBound) {
            mDistanceTextView.setText(getString(R.string.daily_dist_data, mLocationService.distanceCovered()));
            mTimerTextView.setText(Helper.secondToHHMMSS(mLocationService.elapsedTime()));
        }
    }


    private void goToDispatchActivity() {
        startActivity(Helper.getIntent(getContext(), DispatchActivity.class));
    }

    private static class UIUpdateHandler extends Handler {

        private final static int UPDATE_RATE_MS = 1000;
        private final WeakReference<WalkFragment> fragment;

        UIUpdateHandler(WalkFragment fragment) {
            this.fragment = new WeakReference<WalkFragment>(fragment);
        }

        @Override
        public void handleMessage(Message message) {
            if (MSG_UPDATE_TIME == message.what) {
                fragment.get().updateUI();
                sendEmptyMessageDelayed(MSG_UPDATE_TIME, UPDATE_RATE_MS);
            }
        }
    }


}

package ar.davinci.edu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import ar.davinci.edu.R;
import ar.davinci.edu.activities.MapsActivity;
import ar.davinci.edu.infraestructure.api.ApiClient;
import ar.davinci.edu.infraestructure.api.OnSuccessCallback;
import ar.davinci.edu.infraestructure.dto.exercise_session.ExerciseDTO;
import ar.davinci.edu.infraestructure.model.RunningSession;
import ar.davinci.edu.infraestructure.tracker.AnalyzerKML;
import ar.davinci.edu.infraestructure.tracker.GPSTracker;
import ar.davinci.edu.infraestructure.tracker.TrackerKML;


public class RunningFragment extends Fragment {
    public static ToggleButton btnGPS; // TODO: Quitar la variable estática y hacerle un get.
    private GPSTracker gps;
    private TrackerKML tracker;
    private Button btnMap;
    private TextView textView;

    final ApiClient apiClient = new ApiClient(getContext());

    public RunningFragment() {
    }

    private void bootstraping() {
        btnGPS = getView().findViewById(R.id.btnGPS);
        btnMap = getView().findViewById(R.id.btnMaps);
        textView = getView().findViewById(R.id.txtKml);
        tracker = new TrackerKML(getContext());
        gps = new GPSTracker(getActivity(), tracker);

        btnGPS.setOnClickListener(v -> alternateAction(btnGPS.isChecked()));

        btnMap.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplication(), MapsActivity.class);
            startActivity(intent);
        });
    }

    private void alternateAction(boolean activated) {
        if (activated) {
            btnMap.setVisibility(Button.INVISIBLE);
            textView.setVisibility(TextView.INVISIBLE);

            tracker.openFile();
            gps.toggleLocationUpdates(true);
        } else {
            btnMap.setVisibility(Button.VISIBLE);
            textView.setVisibility(TextView.VISIBLE);

            gps.toggleLocationUpdates(false);
            tracker.closeFile();

            setupCoordinates();

        }
    }

    private void setupCoordinates() {
        RunningSession runningSession = AnalyzerKML.getSessionRunning(getContext());

        apiClient.addExerciseSession(
                new ExerciseDTO(runningSession),
                new OnSuccessCallback() {
                    @Override
                    public void execute(Object body) {
                        //TODO Se posteo, hacer algo
                        Toast.makeText(getContext(), "Se posteó exitosamente!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void error(Object body) {
                        Toast.makeText(getContext(), "Error!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bootstraping();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_running, container, false);
    }

}

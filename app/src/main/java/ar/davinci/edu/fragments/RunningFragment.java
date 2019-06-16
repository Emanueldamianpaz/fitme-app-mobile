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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ar.davinci.edu.R;
import ar.davinci.edu.activities.MapsActivity;
import ar.davinci.edu.infraestructure.tracker.AnalyzerKML;
import ar.davinci.edu.infraestructure.tracker.GPSTracker;
import ar.davinci.edu.infraestructure.tracker.TrackerKML;


public class RunningFragment extends Fragment {
    private GPSTracker gps;
    private TrackerKML tracker;

    public static ToggleButton btnGPS; // TODO: Quitar la variable estática y hacerle un get.
    private Button btnMap;
    private TextView textView;

    public RunningFragment() {
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Inicialización de variables.
        btnGPS = getView().findViewById(R.id.botonGPS);
        btnMap = getView().findViewById(R.id.botonMaps);
        textView = getView().findViewById(R.id.texto);
        tracker = new TrackerKML(getContext());
        gps = new GPSTracker(getActivity(), tracker);

        // Función botón GPS.
        btnGPS.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gestiona las acciones a tomar por el programa.
                alternarAccion(btnGPS.isChecked());
            }
        });

        // Función botón Maps.
        btnMap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se lanza el intent de maps.
                Intent intent = new Intent(getActivity().getApplication(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_running, container, false);
    }

    private void alternarAccion(boolean activado) {
        if (activado) { // En el caso de encender el botón.
            // Con esto evitamos que el usuario abra maps antes de guardar el fichero.
            btnMap.setVisibility(Button.INVISIBLE);
            textView.setVisibility(TextView.INVISIBLE);

            // Abrimos el fichero (sobreescribe) y comienza los updates.
            tracker.openFile();
            gps.toggleLocationUpdates(true);
        } else { // En el caso de parar el botón.
            btnMap.setVisibility(Button.VISIBLE);
            textView.setVisibility(TextView.VISIBLE);

            gps.toggleLocationUpdates(false);
            tracker.closeFile();

            setupCoordinates();

            // TODO Hacer post al backend
        }
    }

    public void setupCoordinates() {

        // TODO Mostrar mensaje
        AnalyzerKML.getKilometersRunned(getContext());

        // TODO Realizar post AnalyzerKML.getLocations();

    }
}

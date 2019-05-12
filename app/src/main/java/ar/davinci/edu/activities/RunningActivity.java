package ar.davinci.edu.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import ar.davinci.edu.infraestructure.tracker.GPSTracker;
import ar.davinci.edu.infraestructure.tracker.TrackerKML;

public class RunningActivity extends AppCompatActivity {
    private GPSTracker gps;
    private TrackerKML tracker;

    public static ToggleButton btnGPS; // TODO: Quitar la variable estática y hacerle un get.
    private Button btnMap;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);


        // Inicialización de variables.
        btnGPS = findViewById(R.id.botonGPS);
        btnMap = findViewById(R.id.botonMaps);
        textView = findViewById(R.id.texto);
        tracker = new TrackerKML(this);
        gps = new GPSTracker(this, tracker);

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
                Intent intent = new Intent(getApplication(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    //==============================================================================================
    // MÉTODOS
    //==============================================================================================
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

            showFile();

            // TODO Hacer post al backend
        }
    }

    public void showFile() {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;

        try {
            inputStreamReader = new FileReader(new File(this.getFilesDir(), TrackerKML.KML_FILENAME));
            bufferedReader = new BufferedReader(inputStreamReader);
            textView.setText("\n");

            line = bufferedReader.readLine();

            while (line != null) {
                textView.append(line + "\n");
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStreamReader.close();

        } catch (IOException e) {
            Toast.makeText(RunningActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

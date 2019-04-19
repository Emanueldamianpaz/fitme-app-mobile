package ar.davinci.edu.activities;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.tracker.RegistradorKML;
import ar.davinci.edu.infraestructure.tracker.SaxHandler;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mapa;

    // Esto lo hemos creado para poder usarlo en el AsyncTask.
    private SAXParser parser;
    private SaxHandler handler;

    //======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Aquí se lee el KML y se ponen los puntos.

        mapa = googleMap;

        // Lo que hacemos aquí es leer el KML con SAX y llenar el mapa de puntos.
        // Hay que hacerlo con AsyncTask porque si hay muchos puntos peta.
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            parser = factory.newSAXParser();

            // Manejador SAX programado por nosotros. Le pasamos nuestro mapa para que ponga los puntos.
            handler = new SaxHandler(mapa);

            // AsyncTask. Le pasamos el directorio de ficheros como string.
            ProcesarKML procesador = new ProcesarKML();
            procesador.execute(this.getFilesDir().getAbsolutePath());

        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    //==============================================================================================
    // ASYNCTASK - TAREA ASÍNCRONA
    //==============================================================================================
    private class ProcesarKML extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {

                parser.parse(new FileInputStream(new File(strings[0], RegistradorKML.KML_NOMBRE_FICHERO)), handler);

            } catch (FileNotFoundException e) {
                Toast.makeText(MapsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

            } catch (SAXException e) {
                Toast.makeText(MapsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                Toast.makeText(MapsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            mapa.addPolyline(handler.getRuta()); // Se añade una ruta.

            // Se añade un punto en el mapa.
            //mapa.addMarker(new MarkerOptions().position(handler.coordenadas).title("hola"));

            // Se mueve la cámara a la última posición.
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(handler.getLastCoordenadas(), 15));
        }
    }
}
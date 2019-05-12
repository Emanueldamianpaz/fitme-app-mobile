package ar.davinci.edu.activities;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.tracker.TrackerKML;
import ar.davinci.edu.infraestructure.tracker.SaxHandler;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

    private GoogleMap map;
    private SAXParser parser;
    private SaxHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            parser = factory.newSAXParser();
            handler = new SaxHandler(map);

            ProcessKML processor = new ProcessKML();
            processor.execute(this.getFilesDir().getAbsolutePath());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    private class ProcessKML extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                parser.parse(new FileInputStream(new File(strings[0], TrackerKML.KML_FILENAME)), handler);
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
            map.addPolyline(handler.getRouteTracked());
            map.addMarker(new MarkerOptions().position(handler.getFirstCoordinates()).title("Inicio"));
            map.addMarker(new MarkerOptions().position(handler.getLastCoordinates()).title("Final"));

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(handler.getLastCoordinates(), 15));
        }
    }
}
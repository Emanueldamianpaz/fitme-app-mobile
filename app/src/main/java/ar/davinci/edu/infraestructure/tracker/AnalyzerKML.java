package ar.davinci.edu.infraestructure.tracker;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class AnalyzerKML {

    private static List<Location> locationList = new ArrayList<>();


    private static List<Location> analyzeKML(Context context) {
        SAXParser parser;
        SaxHandler handler = new SaxHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            parser = factory.newSAXParser();
            parser.parse(new FileInputStream(new File(context.getFilesDir(), TrackerKML.KML_FILENAME)), handler);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }


        for (LatLng latLng : handler.getPoints()) {
            Location location = new Location(latLng.toString());
            location.setLongitude(latLng.longitude);
            location.setLatitude(latLng.latitude);

            locationList.add(location);
        }

        return locationList;

    }

    public static double getKilometersRunned(Context context) {

        locationList = AnalyzerKML.analyzeKML(context);

        double distance = 0;
        for (int i = 1; i < locationList.size(); i++) {
            distance = distance + locationList.get(i).distanceTo(locationList.get(i - 1));
        }

        distance = Math.round((distance / 1000) * 100.0) / 100.0; // Kilometers


        return distance;
    }

    public static List<Location> getLocations() {
        return locationList;
    }

}

package ar.davinci.edu.infraestructure.tracker;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ar.davinci.edu.infraestructure.model.dto.ExerciseDTO;


public class AnalyzerKML {

    private static List<Location> locationList = new ArrayList<>();
    private static List<SimpleDateFormat> timestampList = new ArrayList<>();

    public static ExerciseDTO getSessionRunning(Context context) {
        return new ExerciseDTO(getKilometersRunned(context), getLocations(), getTimestamps());
    }

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

            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

            timestampList.add(dateFormatGmt);
            locationList.add(location);
        }

        return locationList;

    }

    private static double getKilometersRunned(Context context) {

        locationList = AnalyzerKML.analyzeKML(context);

        double distance = 0;
        for (int i = 1; i < locationList.size(); i++) {
            distance = distance + locationList.get(i).distanceTo(locationList.get(i - 1));
        }

        distance = Math.round((distance / 1000) * 100.0) / 100.0; // Kilometers

        return distance;
    }

    private static List<Location> getLocations() {
        return locationList;
    }

    private static List<SimpleDateFormat> getTimestamps() {
        return timestampList;
    }

}

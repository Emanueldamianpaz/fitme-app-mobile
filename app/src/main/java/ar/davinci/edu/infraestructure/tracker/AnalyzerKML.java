package ar.davinci.edu.infraestructure.tracker;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ar.davinci.edu.infraestructure.model.RunningSession;


public class AnalyzerKML {

    public static RunningSession getSessionRunning(Context context) {
        return analyzeKML(context);
    }

    private static RunningSession analyzeKML(Context context) {
        SAXParser parser;
        SaxHandler handler = new SaxHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        RunningSession runningSession = new RunningSession();

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

            runningSession.trackLocation(location);
        }

        return runningSession;

    }


}

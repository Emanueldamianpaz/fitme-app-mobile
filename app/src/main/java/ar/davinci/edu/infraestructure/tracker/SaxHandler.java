package ar.davinci.edu.infraestructure.tracker;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private GoogleMap map;
    private LatLng pointTracked;
    private List<LatLng> listPoints;
    private PolylineOptions routeTracked;

    private boolean inTag;
    private String textReaded;

    public SaxHandler(GoogleMap map) {
        this.map = map;
        this.inTag = false;
        this.listPoints = new ArrayList<>();
        this.routeTracked = new PolylineOptions();
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (inTag) {
            textReaded = new String(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        inTag = true;
    }


    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        super.endElement(uri, localName, name);

        if (inTag) {
            if (localName.equals("coordinates")) {

                try {
                    double latitude = Double.parseDouble(textReaded.substring(0, textReaded.indexOf(',')));
                    double longitude = Double.parseDouble(textReaded.substring(textReaded.indexOf(',') + 1, textReaded.lastIndexOf(',')));
                    double altitude = Double.parseDouble(textReaded.substring(textReaded.lastIndexOf(',') + 1, textReaded.length()));

                    pointTracked = new LatLng(latitude, longitude);

                    listPoints.add(pointTracked);

                } catch (Exception e) {
                    Log.e("SaxHandler", "Skipping pointTracked incorrect: " + textReaded);
                }
            }

            textReaded = "";
            inTag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
    }


    @Override
    public void endDocument() throws SAXException {
        if (!listPoints.isEmpty()) {
            routeTracked.addAll(listPoints).color(Color.BLUE);
        } else
            Log.e("SaxHandler", "No points or no file");
    }

    public PolylineOptions getRouteTracked() {
        return routeTracked;
    }

    public LatLng getLastCoordinates() {
        return pointTracked;
    }

    public LatLng getFirstCoordinates() {
        return listPoints.get(0);
    }
}
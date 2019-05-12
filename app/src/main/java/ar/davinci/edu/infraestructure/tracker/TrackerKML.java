package ar.davinci.edu.infraestructure.tracker;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TrackerKML {

    public static final String KML_FILENAME = "routeFitme.kml";
    private static final String KML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
            "  <Placemark>";
    private static final String KML_FOOTER = "\n  </Placemark>\n</kml>";

    private File fileFitme;
    private static FileWriter fileWriter;
    private static PrintWriter printWriter;
    private Context context;

    public TrackerKML(Context context) {
        this.context = context;
        this.fileFitme = new File(context.getFilesDir(), KML_FILENAME);
    }

    public void openFile() {
        try {
            fileWriter = new FileWriter(fileFitme);
            printWriter = new PrintWriter(fileWriter);

            printWriter.write(KML_HEADER);

            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addPoint(double latitude, double longitude, double altitude) {
        try {
            fileWriter = new FileWriter(fileFitme, true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.append("\n    <point>\n      <coordinates> ");
            printWriter.append(latitude + "," + longitude + "," + altitude);
            printWriter.append(" </coordinates> \n    </point>");

            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void closeFile() {
        try {
            fileWriter = new FileWriter(fileFitme, true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.append(KML_FOOTER);

            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
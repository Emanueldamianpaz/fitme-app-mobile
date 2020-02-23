package ar.davinci.edu.model;

import android.location.Location;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class RunningSession {

    private List<Location> locationList = new ArrayList<>();
    private List<SimpleDateFormat> timestampList = new ArrayList<>();
    private Double distance = 0.0;

    public void trackLocation(Location location) {

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        locationList.add(location);
        timestampList.add(dateFormatGmt);
    }

    public double getKilometersRunned() {

        if (locationList.size() > 0) {
            for (int i = 1; i < locationList.size(); i++) {
                distance = distance + locationList.get(i).distanceTo(locationList.get(i - 1));
            }
        } else {
            Log.i("running_session", "no location - no running session");
        }

        return Math.round((distance / 1000) * 100.0) / 100.0; // Kilometers;
    }

}

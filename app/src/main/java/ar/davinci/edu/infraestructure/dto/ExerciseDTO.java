package ar.davinci.edu.infraestructure.dto;


import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.List;

import ar.davinci.edu.infraestructure.model.RunningSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class ExerciseDTO {

    private long id;
    private double kilometersRunned;
    private List<Location> locationsRunned;
    private List<SimpleDateFormat> timestamptRunned;

    public ExerciseDTO(RunningSession runningSession) {
        this.id = runningSession.getTimestampList().get(0).hashCode();
        this.kilometersRunned = runningSession.getKilometersRunned();
        this.locationsRunned = runningSession.getLocationList();
        this.timestamptRunned = runningSession.getTimestampList();
    }
}

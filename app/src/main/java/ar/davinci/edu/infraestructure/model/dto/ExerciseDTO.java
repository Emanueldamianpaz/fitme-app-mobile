package ar.davinci.edu.infraestructure.model.dto;


import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.List;

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

    public ExerciseDTO(double kilometersRunned,
                       List<Location> locationsRunned,
                       List<SimpleDateFormat> timestamptRunned) {
        this.kilometersRunned = kilometersRunned;
        this.locationsRunned = locationsRunned;
        this.timestamptRunned = timestamptRunned;
    }
}

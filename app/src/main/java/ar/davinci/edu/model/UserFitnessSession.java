package ar.davinci.edu.model;

import io.realm.RealmObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserFitnessSession extends RealmObject {
    private String id;
    private String firstName;
    private String username;
    private String password;
    private long totalTimeWalk;
    private float distanceCovered;
    private float pace;

    public void updateDistanceCovered(float distanceWalked) {
        this.distanceCovered = this.distanceCovered + distanceWalked;
    }

    public void updateTotalTimeWalk(long timeWalked) {
        this.totalTimeWalk = this.totalTimeWalk + timeWalked;
    }
}

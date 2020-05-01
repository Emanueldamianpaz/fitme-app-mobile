package ar.davinci.edu.views.activities.userRoutine.workout_exercise;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.routine.detail.WorkoutExercise;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.userRoutine.WorkoutExerciseAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutExerciseFragment extends Fragment {

    private List<WorkoutExercise> workoutExerciseList = new ArrayList<>();
    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    public WorkoutExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_workout_exercise, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        Set<WorkoutExercise> weList = Helper.gson.fromJson(
                args.getString("workout_exercise", ""),
                new TypeToken<Set<WorkoutExercise>>() {
                }.getType());

        workoutExerciseList.addAll(weList);

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView listWorkoutExercise = container.findViewById(R.id.listWorkoutExercise);
        if (workoutExerciseList.size() > 0) {
            listWorkoutExercise.setAdapter(new WorkoutExerciseAdapter(container.getContext(), workoutExerciseList));
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.default_no_result, fragmentNoResult);
        }


    }
}

package ar.davinci.edu.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.routine.detail.WorkoutExercise;
import ar.davinci.edu.views.adapters.WorkoutExerciseAdapter;

public class WorkoutExerciseFragment extends Fragment {

    private List<WorkoutExercise> exerciseList;
    private Gson gson;

    public WorkoutExerciseFragment() {
        gson = new GsonBuilder().create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_workout_exercise, container, false);
        Bundle args = getArguments();

        Set<WorkoutExercise> exercises = gson.fromJson(
                args.getString("exercise", ""),
                new TypeToken<Set<WorkoutExercise>>() {
                }.getType());


        List<WorkoutExercise> exerciseList = new ArrayList<>();
        exerciseList.addAll(exercises);

        this.exerciseList = exerciseList;

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView listWorkoutExercise = container.findViewById(R.id.listWorkoutExercise);
        listWorkoutExercise.setAdapter(new WorkoutExerciseAdapter(container.getContext(), exerciseList));


    }
}

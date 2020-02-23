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
import ar.davinci.edu.model.Exercise;
import ar.davinci.edu.views.adapters.ExerciseAdapter;

public class ExerciseFragment extends Fragment {

    private List<Exercise> exerciseList;
    private Gson gson;

    public ExerciseFragment() {
        gson = new GsonBuilder().create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        Bundle args = getArguments();

        Set<Exercise> exercises = gson.fromJson(
                args.getString("exercise", ""),
                new TypeToken<Set<Exercise>>() {
                }.getType());


        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.addAll(exercises);

        this.exerciseList = exerciseList;

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView routineList = container.findViewById(R.id.listExercise);
        routineList.setAdapter(new ExerciseAdapter(container.getContext(), exerciseList));


    }
}

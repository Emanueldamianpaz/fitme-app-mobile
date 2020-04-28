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
import ar.davinci.edu.domain.model.routine.detail.MealNutrition;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.views.adapters.NutritionAdapter;

public class UserRoutineFragment extends Fragment {


    private UserRoutine userRoutine;
    private Gson gson;

    public UserRoutineFragment() {
        gson = new GsonBuilder().create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        Bundle args = getArguments();


        Set<MealNutrition> nutrition = gson.fromJson(
                args.getString("nutrition", ""),
                new TypeToken<Set<MealNutrition>>() {
                }.getType());


        List<MealNutrition> nutritions = new ArrayList<>();
        nutritions.addAll(nutrition);

        this.userRoutine = nutritions;


        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView routineList = container.findViewById(R.id.listExercise);
        routineList.setAdapter(new NutritionAdapter(container.getContext(), userRoutine));


    }
}

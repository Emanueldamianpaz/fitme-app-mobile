package ar.davinci.edu.views.adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.model.Routine;
import ar.davinci.edu.views.fragments.ExerciseFragment;
import ar.davinci.edu.views.fragments.NutritionFragment;

public class RoutineAdapter extends BaseAdapter {

    private AppCompatActivity context;
    private List<Routine> routineList;
    private Gson gson;

    public RoutineAdapter(AppCompatActivity context, Set<Routine> setRoutines) {
        List<Routine> routineList = new ArrayList<>();
        routineList.addAll(setRoutines);

        gson = new GsonBuilder().create();

        this.routineList = routineList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return routineList.size();
    }

    @Override
    public Object getItem(int i) {
        return routineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (routineList.get(i).getId());
    }

    @Override
    public View getView(int i, View routineView, ViewGroup viewGroup) {
        routineView = LayoutInflater.from(context).inflate(R.layout.fragment_item_routine, viewGroup, false);
        TextView lblName = routineView.findViewById(R.id.lblName);
        TextView lblDescription = routineView.findViewById(R.id.lblDescription);
        Button btnExercise = routineView.findViewById(R.id.btnExercise);
        Button btnNutrition = routineView.findViewById(R.id.btnNutrition);

        Routine routine = routineList.get(i);

        lblName.setText(routine.getName());
        lblDescription.setText(routine.getDescription());

        Bundle args = new Bundle();
        ExerciseFragment exerciseFragment = new ExerciseFragment();
        NutritionFragment nutritionFragment = new NutritionFragment();

        args.putString("exercise", gson.toJson(routine.getRoutineTemplate().getExercises()));
        args.putString("nutrition", gson.toJson(routine.getRoutineTemplate().getNutritions()));

        exerciseFragment.setArguments(args);
        nutritionFragment.setArguments(args);

        btnExercise.setOnClickListener(v -> Helper.changeFragments(context, exerciseFragment));
        btnNutrition.setOnClickListener(v -> Helper.changeFragments(context, nutritionFragment));

        return routineView;
    }
}
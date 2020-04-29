package ar.davinci.edu.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserRoutineFragment extends Fragment {


    @BindView(R.id.lblName)
    TextView lblName;
    @BindView(R.id.lblDescription)
    TextView lblDescription;
    @BindView(R.id.lblGoalType)
    TextView lblGoalType;

    private Bundle args = getArguments();
    private UserRoutine userRoutine;
    private Gson gson;

    public UserRoutineFragment() {
        gson = new GsonBuilder().create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_routine, container, false);
        ButterKnife.bind(this, v);

        args = getArguments();
        userRoutine = gson.fromJson(args.getString("userRoutine", ""),
                new TypeToken<UserRoutine>() {
                }.getType());

        bootstraping();

        return v;
    }

    private void bootstraping() {
        lblName.setText(userRoutine.getRoutineTemplate().getName());
        lblDescription.setText(userRoutine.getRoutineTemplate().getDescription());
        lblGoalType.setText(userRoutine.getRoutineTemplate().getGoalType().toString());
    }

    @OnClick(R.id.btnExercise)
    public void viewExercises() {

        WorkoutExerciseFragment workoutExerciseFragment = new WorkoutExerciseFragment();
        args.putString("exercise", gson.toJson(userRoutine.getRoutineTemplate().getWorkoutExercises()));
        workoutExerciseFragment.setArguments(args);
        // Helper.changeFragments(, workoutExerciseFragment);

    }

    @OnClick(R.id.btnNutrition)
    public void viewNutrition() {
        NutritionFragment nutritionFragment = new NutritionFragment();
        args.putString("nutrition", gson.toJson(userRoutine.getRoutineTemplate().getMealNutritions()));
        nutritionFragment.setArguments(args);

    }


}

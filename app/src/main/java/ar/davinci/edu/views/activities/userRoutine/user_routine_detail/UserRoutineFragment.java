package ar.davinci.edu.views.activities.userRoutine.user_routine_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.userRoutine.meal_nutrition.MealNutritionActivity;
import ar.davinci.edu.views.activities.userRoutine.user_experience.UserExperienceActivity;
import ar.davinci.edu.views.activities.userRoutine.workout_exercise.WorkoutExerciseActivity;
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

    private UserRoutine userRoutine;

    public UserRoutineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_routine, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();
        userRoutine = Helper.gson.fromJson(args.getString("user_routine", ""),
                new TypeToken<UserRoutine>() {
                }.getType());

        bootstraping();

        return v;
    }

    private void bootstraping() {
        lblName.setText(userRoutine.getRoutineTemplate().getName());
        lblDescription.setText(userRoutine.getRoutineTemplate().getDescription());
        lblGoalType.setText(getResources().getString(userRoutine.getRoutineTemplate().getGoalType().getLabel()));
    }


    @OnClick(R.id.lytUserExperience)
    public void viewUserExperiences() {
        Intent userExperienceActivity = Helper.getIntent(getActivity(), UserExperienceActivity.class);

        userExperienceActivity.putExtra("user_routine", Helper.gson.toJson(userRoutine));
        startActivity(userExperienceActivity);
    }


    @OnClick(R.id.btnWorkoutExercise)
    public void viewExercises() {
        Intent workoutExerciseActivity = Helper.getIntent(getActivity(), WorkoutExerciseActivity.class);

        workoutExerciseActivity.putExtra("workout_exercise", Helper.gson.toJson(userRoutine.getRoutineTemplate().getWorkoutExercises()));
        startActivity(workoutExerciseActivity);
    }

    @OnClick(R.id.btnMealNutrition)
    public void viewNutrition() {
        Intent mealNutritionActivity = Helper.getIntent(getActivity(), MealNutritionActivity.class);

        mealNutritionActivity.putExtra("meal_nutrition", Helper.gson.toJson(userRoutine.getRoutineTemplate().getMealNutritions()));
        startActivity(mealNutritionActivity);
    }


}

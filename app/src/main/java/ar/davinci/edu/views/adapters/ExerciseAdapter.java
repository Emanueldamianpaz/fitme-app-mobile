package ar.davinci.edu.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.routine.detail.WorkoutExercise;

public class ExerciseAdapter extends BaseAdapter {

    private Context context;
    private List<WorkoutExercise> exerciseList;

    private ExerciseAdapter() {
    }

    public ExerciseAdapter(Context context, List<WorkoutExercise> exerciseList) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int i) {
        return exerciseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (exerciseList.get(i).getId());
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View exerciseView, ViewGroup viewGroup) {
        exerciseView = LayoutInflater.from(context).inflate(R.layout.fragment_item_exercise, viewGroup, false);
        TextView txtName = exerciseView.findViewById(R.id.lblName);
        TextView txtDescription = exerciseView.findViewById(R.id.lblDescription);
        TextView txtDifficulty = exerciseView.findViewById(R.id.lblDifficulty);
        TextView txtType = exerciseView.findViewById(R.id.lblType);

        WorkoutExercise exercise = exerciseList.get(i);

        txtName.setText(exercise.getName());
        txtDescription.setText(exercise.getDescription());
        txtDifficulty.setText(exercise.getDifficulty().toString());
        txtType.setText("(" + exercise.getType() + ")");

        return exerciseView;
    }
}
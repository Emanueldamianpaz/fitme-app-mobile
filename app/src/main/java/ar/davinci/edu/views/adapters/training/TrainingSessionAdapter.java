package ar.davinci.edu.views.adapters.training;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.training.TrainingSession;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.training.nutrition_session.NutritionSessionActivity;
import ar.davinci.edu.views.activities.training.running_session.RunningSessionActivity;

public class TrainingSessionAdapter extends BaseAdapter {

    private Context context;
    private List<TrainingSession> trainingSession;

    public TrainingSessionAdapter(Context context, List<TrainingSession> trainingSessions) {
        this.trainingSession = trainingSessions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trainingSession.size();
    }

    @Override
    public Object getItem(int i) {
        return trainingSession.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (trainingSession.get(i).getId());
    }

    @Override
    public View getView(int i, View trainingView, ViewGroup viewGroup) {
        trainingView = LayoutInflater.from(context).inflate(R.layout.item_training_session, viewGroup, false);

        TextView lblDate = trainingView.findViewById(R.id.lblDateTrainingSession);
        Button btnShowRunningSession = trainingView.findViewById(R.id.btnShowRunningSession);
        Button btnShowNutritionSession = trainingView.findViewById(R.id.btnShowNutritionSession);

        TrainingSession trainingSession = this.trainingSession.get(i);
        lblDate.setText(trainingSession.getDate());

        Intent nutritionSessionActivity = Helper.getIntent(context, NutritionSessionActivity.class);
        nutritionSessionActivity.putExtra("nutrition_session",
                Helper.gson.toJson(trainingSession.getNutritionSessions())
        );

        Intent runningSessionActivity = Helper.getIntent(context, RunningSessionActivity.class);
        runningSessionActivity.putExtra("running_session",
                Helper.gson.toJson(trainingSession.getRunningSessions())
        );


        btnShowNutritionSession.setOnClickListener(v -> context.startActivity(nutritionSessionActivity));
        btnShowRunningSession.setOnClickListener(v -> context.startActivity(runningSessionActivity));

        return trainingView;
    }
}
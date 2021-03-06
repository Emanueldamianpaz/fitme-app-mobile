package ar.davinci.edu.views.activities.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.TrainingSessionApi;
import ar.davinci.edu.domain.model.training.TrainingSession;
import ar.davinci.edu.domain.model.training.detail.NutritionSession;
import ar.davinci.edu.domain.types.MealNutritionType;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.fitness.walk.WalkActivity;
import ar.davinci.edu.views.adapters.training.TrainingSessionAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrainingSessionFragment extends Fragment {


    @BindView(R.id.lblMessage)
    TextView mMessage;
    @BindView(R.id.listItemTrainingSession)
    ListView trainingSessionList;
    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    private View v;

    public TrainingSessionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_training_session, container, false);
        ButterKnife.bind(this, v);

        mMessage.setText(getString(R.string.message_label, SharedJWT.getUserFromSharedP().getName()));
        renderTrainingSessions();

        return v;
    }

    private void renderTrainingSessions() {

        TrainingSessionApi.getTrainingSession(body -> {
            List<TrainingSession> trainningSessionList = (List<TrainingSession>) body;

            if (trainningSessionList.size() > 0) {
                trainingSessionList.setAdapter(new TrainingSessionAdapter(v.getContext(), trainningSessionList));
            } else {
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.default_no_result, fragmentNoResult);
            }
        }, v.getContext());


    }

    @OnClick(R.id.btnWalk)
    public void goToWalkEvent() {
        startActivity(Helper.getIntent(getContext(), WalkActivity.class));
    }


    @OnClick(R.id.btnNutrition)
    public void addNutritionSession() {
        AlertDialog.Builder saveBuilder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_add_nutrition_session, null);

        Spinner editMealNutritionType = customLayout.findViewById(R.id.editMealNutritionType);
        EditText editName = customLayout.findViewById(R.id.editName);
        EditText editCalories = customLayout.findViewById(R.id.editCalories);

        ArrayAdapter<MealNutritionType> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                MealNutritionType.values()
        );
        editMealNutritionType.setAdapter(adapter);
        saveBuilder.setView(customLayout);

        saveBuilder.setNegativeButton(getString(R.string.close), (dialogInterface, i) -> dialogInterface.dismiss());
        saveBuilder.setPositiveButton(getString(R.string.save_nutrition),
                (dialog, which) -> {

                    MealNutritionType mealNutritionTypeSelected = (MealNutritionType) editMealNutritionType.getSelectedItem();

                    NutritionSession nutritionSession = new NutritionSession(
                            editName.getText().toString(),
                            mealNutritionTypeSelected,
                            Double.parseDouble(editCalories.getText().toString()));


                    TrainingSessionApi.addNutritionSession(body -> renderTrainingSessions(),
                            getContext(),
                            nutritionSession
                    );

                    dialog.dismiss();

                }
        );

        saveBuilder.setCancelable(false);
        saveBuilder.create().show();
    }


}

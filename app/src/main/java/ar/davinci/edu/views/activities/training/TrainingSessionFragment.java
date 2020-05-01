package ar.davinci.edu.views.activities.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.TrainingSessionApi;
import ar.davinci.edu.domain.model.training.TrainingSession;
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

    public TrainingSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_training_session, container, false);
        ButterKnife.bind(this, v);

        mMessage.setText(getString(R.string.message_label, SharedJWT.getUserFromSharedP().getName()));
        renderTrainingSessions(v);

        return v;
    }

    private void renderTrainingSessions(View container) {

        TrainingSessionApi.getTrainingSession(body -> {
            List<TrainingSession> trainningSessionList = (List<TrainingSession>) body;

            if (trainningSessionList.size() > 0) {
                trainingSessionList.setAdapter(new TrainingSessionAdapter(container.getContext(), trainningSessionList));
            } else {
                LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.default_no_result, fragmentNoResult);
            }
        }, container.getContext());


    }

    @OnClick(R.id.btnWalk)
    public void goToWalkEvent() {
        startActivity(Helper.getIntent(getContext(), WalkActivity.class));
    }


}

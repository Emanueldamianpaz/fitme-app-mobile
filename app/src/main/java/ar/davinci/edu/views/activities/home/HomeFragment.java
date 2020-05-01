package ar.davinci.edu.views.activities.home;

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
import ar.davinci.edu.clients.apis.UserInfoApi;
import ar.davinci.edu.clients.apis.UserRoutineApi;
import ar.davinci.edu.domain.dto.fitme.training.TrainingTotalStadistDTO;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.training.TrainingSessionActivity;
import ar.davinci.edu.views.adapters.userRoutine.UserRoutineAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {


    @BindView(R.id.kilometersRunned)
    TextView lblMetersRunned;
    @BindView(R.id.lblCalories)
    TextView lblCalories;
    @BindView(R.id.listUserRoutine)
    ListView routineList;
    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        getUserTotalTrainingStadist();
        getMyRoutines(v);
        return v;
    }


    private void getMyRoutines(View container) {
        UserRoutineApi.getListUserRoutines(
                body -> {
                    List<UserRoutine> userRoutine = (List<UserRoutine>) body;

                    if (userRoutine.size() > 0) {
                        routineList.setAdapter(new UserRoutineAdapter(container.getContext(), userRoutine));
                    } else {
                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        inflater.inflate(R.layout.default_no_result, fragmentNoResult);
                    }

                }, container.getContext()
        );
    }

    public void getUserTotalTrainingStadist() {
        UserInfoApi.getUserStadist(body -> {
            List<TrainingTotalStadistDTO> userStadist = (List<TrainingTotalStadistDTO>) body;

            Double totalCalories = 0.0;
            Double totalMeters = 0.0;

            for (TrainingTotalStadistDTO Training : userStadist) {
                totalCalories += Training.getCalories();
                totalMeters += Training.getMeters();
            }

            lblCalories.setText(String.valueOf((Math.round(totalCalories) * 100) / 100).concat("cal"));
            lblMetersRunned.setText(String.valueOf((Math.round(totalMeters) * 100) / 100).concat("m."));
        }, getContext());

    }

    @OnClick(R.id.cardMetersRunned)
    public void startRunning() {
        startActivity(Helper.getIntent(getContext(), TrainingSessionActivity.class));
    }

}

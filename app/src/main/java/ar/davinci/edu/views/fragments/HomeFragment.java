package ar.davinci.edu.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.UserInfoApi;
import ar.davinci.edu.domain.dto.fitme.training.TrainningTotalStadistDTO;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.fitness.RunningSessionActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {


    @BindView(R.id.kilometersRunned)
    TextView lblMetersRunned;

    @BindView(R.id.lblCalories)
    TextView lblCalories;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);


        UserInfoApi.getUserStadist(body -> {

            List<TrainningTotalStadistDTO> userStadist = (List<TrainningTotalStadistDTO>) body;

            Double totalCalories = 0.0;
            Double totalMeters = 0.0;

            for (TrainningTotalStadistDTO trainning : userStadist) {
                totalCalories += trainning.getCalories();
                totalMeters += trainning.getMeters();
            }

            lblCalories.setText(String.valueOf((Math.round(totalCalories) * 100) / 100).concat("cal"));
            lblMetersRunned.setText(String.valueOf((Math.round(totalMeters) * 100) / 100).concat("m."));
        }, getContext());


        return v;
    }


    @OnClick(R.id.cardMetersRunned)
    public void startRunning() {
        startActivity(Helper.getIntent(getContext(), RunningSessionActivity.class));
    }

    @OnClick(R.id.cardCalories)
    public void showCalories() {
        // startActivity(Helper.getIntent(getContext(), RunningSessionActivity.class));
    }


}

package ar.davinci.edu.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.fitness.RunningSessionActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {


    @BindView(R.id.kilometersRunned)
    TextView kilometersRunned;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        return v;
    }


    @OnClick(R.id.cardKilometersRunned)
    public void startRunning() {
        startActivity(Helper.getIntent(getContext(), RunningSessionActivity.class));
    }

    @OnClick(R.id.cardCalories)
    public void showCalories() {
        // startActivity(Helper.getIntent(getContext(), RunningSessionActivity.class));
    }


}

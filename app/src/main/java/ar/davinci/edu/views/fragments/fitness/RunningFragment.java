package ar.davinci.edu.views.fragments.fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.fitness.WalkActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RunningFragment extends Fragment {


    @BindView(R.id.lblMessage)
    TextView mMessage;
    @BindView(R.id.dailyDistanceData)
    TextView mTotalDist;
    @BindView(R.id.dailyTimeData)
    TextView mTotalTime;
    @BindView(R.id.dailyPaceData)
    TextView mCurrentPace;

    public RunningFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_running, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.btnWalk)
    public void goToWalkEvent() {
        startActivity(Helper.getIntent(getContext(), WalkActivity.class));
    }


}

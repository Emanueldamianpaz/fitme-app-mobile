package ar.davinci.edu.views.fragments.fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.model.UserFitnessSession;
import ar.davinci.edu.views.activities.fitness.WalkActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class RunningFragment extends Fragment {


    @BindView(R.id.messageLabel)
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


        Realm realm = Realm.getDefaultInstance();
        String id = PrefManager.getID(PrefManager.USER_ID);
        UserFitnessSession user = realm.where(UserFitnessSession.class).equalTo("id", id).findFirst();
        if (user != null) {
            setDailyStat(user);
            showAchieveMilestone(user.getDistanceCovered());
        }


        return v;
    }

    private void setDailyStat(UserFitnessSession user) {
        String message = String.format(getString(R.string.message_label), user.getFirstName());
        String dailyDist = String.format(getString(R.string.daily_dist_data), user.getDistanceCovered());
        String dailyTime = String.format(getString(R.string.daily_time_data), Helper.secondToMinuteConverter(user.getTotalTimeWalk()));
        String dailyPace = String.format(getString(R.string.daily_pace_data), user.getPace());

        mMessage.setText(message);
        mTotalDist.setText(dailyDist);
        mTotalTime.setText(dailyTime);
        mCurrentPace.setText(dailyPace);
    }

    @OnClick(R.id.walkBtn)
    public void goToWalkEvent(Button button) {
        startActivity(Helper.getIntent(getContext(), WalkActivity.class));
    }

    private void showAchieveMilestone(float distanceCovered) {
        int numberOfMilestones = Helper.getNumberOfMilestones(distanceCovered);
        if (numberOfMilestones > 0) {
            String title = getString(R.string.achievement_title);
            String message = String.format(getString(R.string.achievement_message), numberOfMilestones);
            Helper.displayMessageToUser(getContext(), title, message).show();
        }
    }

}

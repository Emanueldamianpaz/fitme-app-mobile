package ar.davinci.edu.views.activities.fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import ar.davinci.edu.R;
import ar.davinci.edu.api.clients.ApiClient;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.model.UserFitnessSession;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class RunningActivity extends AppCompatActivity {

    @BindView(R.id.messageLabel)
    TextView mMessage;
    @BindView(R.id.dailyDistanceData)
    TextView mTotalDist;
    @BindView(R.id.dailyTimeData)
    TextView mTotalTime;
    @BindView(R.id.dailyPaceData)
    TextView mCurrentPace;

    final ApiClient apiClient = new ApiClient();

    public RunningActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        String id = PrefManager.getID(PrefManager.USER_ID);
        UserFitnessSession user = realm.where(UserFitnessSession.class).equalTo("id", id).findFirst();
        if (user != null) {
            setDailyStat(user);
            showAchieveMilestone(user.getDistanceCovered());
        }
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
        startActivity(Helper.getIntent(this, WalkActivity.class));
    }

    private void showAchieveMilestone(float distanceCovered) {
        int numberOfMilestones = Helper.getNumberOfMilestones(distanceCovered);
        if (numberOfMilestones > 0) {
            String title = getString(R.string.achievement_title);
            String message = String.format(getString(R.string.achievement_message), numberOfMilestones);
            Helper.displayMessageToUser(this, title, message).show();
        }
    }


}

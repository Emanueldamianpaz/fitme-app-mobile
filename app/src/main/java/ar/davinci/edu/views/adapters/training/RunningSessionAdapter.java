package ar.davinci.edu.views.adapters.training;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.training.detail.ExerciseRunningSession;

public class RunningSessionAdapter extends BaseAdapter {

    private Context context;
    private List<ExerciseRunningSession> runningSessionList;

    public RunningSessionAdapter(Context context, List<ExerciseRunningSession> runningSessionList) {
        this.runningSessionList = runningSessionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return runningSessionList.size();
    }

    @Override
    public Object getItem(int i) {
        return runningSessionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return UUID.fromString(runningSessionList.get(i).getId()).getMostSignificantBits();
    }

    @Override
    public View getView(int i, View runningSessionView, ViewGroup viewGroup) {
        runningSessionView = LayoutInflater.from(context).inflate(R.layout.item_running_session, viewGroup, false);
        TextView lblTimeStamp = runningSessionView.findViewById(R.id.lblTimestamp);
        TextView lblScoring = runningSessionView.findViewById(R.id.lblScoring);
        TextView lblTotalTimeWalk = runningSessionView.findViewById(R.id.lblTotalTimeWalk);
        TextView lblDistanceCovered = runningSessionView.findViewById(R.id.lblDistanceCovered);
        TextView lblPace = runningSessionView.findViewById(R.id.lblPace);
        TextView lblSpeedAvg = runningSessionView.findViewById(R.id.lblSpeedAvg);

        ExerciseRunningSession runningSession = runningSessionList.get(i);

        lblTimeStamp.setText(runningSession.getTimestamp().toString());
        lblScoring.setText(runningSession.getScoring().getLabel());
        lblTotalTimeWalk.setText(runningSession.getRunningSession().getTotalTimeWalk() + "s");
        lblDistanceCovered.setText(runningSession.getRunningSession().getDistanceCovered() + "mts");
        lblPace.setText(runningSession.getRunningSession().getPace() + " pasos");
        lblSpeedAvg.setText(runningSession.getRunningSession().getSpeedAvg() + " m/s");

        return runningSessionView;
    }
}
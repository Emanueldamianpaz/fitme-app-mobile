package ar.davinci.edu.views.activities.training.running_session;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.training.detail.ExerciseRunningSession;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.training.RunningSessionAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RunningSessionFragment extends Fragment {


    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;
    List<ExerciseRunningSession> exerciseRunningSessionsList = new ArrayList<>();

    public RunningSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_running_session, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        exerciseRunningSessionsList = Helper.gson.fromJson(
                args.getString("running_session"),
                new TypeToken<List<ExerciseRunningSession>>() {
                }.getType());


        bootstraping(v);

        return v;
    }


    private void bootstraping(View container) {
        ListView runningSessionListView = container.findViewById(R.id.listRunningSession);

        if (this.exerciseRunningSessionsList.size() > 0) {
            runningSessionListView.setAdapter(new RunningSessionAdapter(container.getContext(), exerciseRunningSessionsList));
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.default_no_result, fragmentNoResult);
        }

    }

}

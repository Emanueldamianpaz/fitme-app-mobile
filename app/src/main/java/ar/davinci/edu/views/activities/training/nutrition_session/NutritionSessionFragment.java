package ar.davinci.edu.views.activities.training.nutrition_session;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.training.detail.NutritionSession;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.training.NutritionSessionAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NutritionSessionFragment extends Fragment {

    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;
    private List<NutritionSession> nutritionSessionList = new ArrayList<>();

    public NutritionSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_nutrition_session, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        nutritionSessionList = Helper.gson.fromJson(
                args.getString("nutrition_session", ""),
                new TypeToken<List<NutritionSession>>() {
                }.getType());


        bootstraping(v);

        return v;
    }


    @OnClick(R.id.btnAddNutritionSession)
    public void goToWalkEvent() {
        Log.d("NutritionSession", "agregué una nutrition session");
    }

    private void bootstraping(View container) {
        ListView nutritionSessionListView = container.findViewById(R.id.listNutritionSession);

        if (this.nutritionSessionList.size() > 0) {
            nutritionSessionListView.setAdapter(new NutritionSessionAdapter(container.getContext(), nutritionSessionList));
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.default_no_result, fragmentNoResult);
        }

    }
}

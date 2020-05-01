package ar.davinci.edu.views.activities.userRoutine.meal_nutrition;

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
import java.util.Set;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.routine.detail.MealNutrition;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.userRoutine.MealNutritionAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MealNutritionFragment extends Fragment {

    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    private List<MealNutrition> mealNutritionList = new ArrayList<>();

    public MealNutritionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_meal_nutrition, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        Set<MealNutrition> mnList = Helper.gson.fromJson(
                args.getString("meal_nutrition", ""),
                new TypeToken<Set<MealNutrition>>() {
                }.getType());

        mealNutritionList.addAll(mnList);

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView routineList = container.findViewById(R.id.listMealNutrition);


        if (this.mealNutritionList.size() > 0) {
            routineList.setAdapter(new MealNutritionAdapter(container.getContext(), mealNutritionList));
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.default_no_result, fragmentNoResult);
        }

    }
}

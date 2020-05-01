package ar.davinci.edu.views.adapters.userRoutine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.routine.detail.MealNutrition;

public class MealNutritionAdapter extends BaseAdapter {

    private Context context;
    private List<MealNutrition> mealNutritionList;

    public MealNutritionAdapter(Context context, List<MealNutrition> mealNutritionList) {
        this.mealNutritionList = mealNutritionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mealNutritionList.size();
    }

    @Override
    public Object getItem(int i) {
        return mealNutritionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (mealNutritionList.get(i).getId());
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View nutritionView, ViewGroup viewGroup) {
        nutritionView = LayoutInflater.from(context).inflate(R.layout.item_meal_nutrition, viewGroup, false);
        TextView txtName = nutritionView.findViewById(R.id.lblName);
        TextView txtCalories = nutritionView.findViewById(R.id.lblCalories);
        TextView txtType = nutritionView.findViewById(R.id.lblType);

        MealNutrition nutrition = mealNutritionList.get(i);

        txtName.setText(nutrition.getName());
        txtCalories.setText("(" + nutrition.getCalories().toString() + " calorias)");
        txtType.setText(nutrition.getType().toString());

        return nutritionView;
    }
}
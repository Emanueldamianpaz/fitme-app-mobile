package ar.davinci.edu.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.model.Nutrition;

public class NutritionAdapter extends BaseAdapter {

    private Context context;
    private List<Nutrition> nutritionList;

    private NutritionAdapter() {
    }

    public NutritionAdapter(Context context, List<Nutrition> nutritionList) {
        this.nutritionList = nutritionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nutritionList.size();
    }

    @Override
    public Object getItem(int i) {
        return nutritionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (nutritionList.get(i).getId());
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View nutritionView, ViewGroup viewGroup) {
        nutritionView = LayoutInflater.from(context).inflate(R.layout.fragment_item_nutrition, viewGroup, false);
        TextView txtName = nutritionView.findViewById(R.id.lblName);
        TextView txtCalories = nutritionView.findViewById(R.id.lblCalories);
        TextView txtType = nutritionView.findViewById(R.id.lblType);

        Nutrition nutrition = nutritionList.get(i);

        txtName.setText(nutrition.getName());
        txtCalories.setText("(" + nutrition.getCalories().toString() + " calorias)");
        txtType.setText(nutrition.getType());

        return nutritionView;
    }
}
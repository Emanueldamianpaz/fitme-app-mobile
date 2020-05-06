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
import ar.davinci.edu.domain.model.training.detail.NutritionSession;

public class NutritionSessionAdapter extends BaseAdapter {

    private Context context;
    private List<NutritionSession> nutritionSessionList;

    public NutritionSessionAdapter(Context context, List<NutritionSession> nutritionSessionList) {
        this.nutritionSessionList = nutritionSessionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nutritionSessionList.size();
    }

    @Override
    public Object getItem(int i) {
        return nutritionSessionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return UUID.fromString(nutritionSessionList.get(i).getId()).getMostSignificantBits();
    }

    @Override
    public View getView(int i, View nutritionSessionView, ViewGroup viewGroup) {
        nutritionSessionView = LayoutInflater.from(context).inflate(R.layout.item_nutrition_session, viewGroup, false);
        TextView lblTimestampt = nutritionSessionView.findViewById(R.id.lblTimestamp);
        TextView lblType = nutritionSessionView.findViewById(R.id.lblType);
        TextView lblName = nutritionSessionView.findViewById(R.id.lblName);
        TextView lblCalories = nutritionSessionView.findViewById(R.id.lblCalories);

        NutritionSession nutritionSession = nutritionSessionList.get(i);

        lblTimestampt.setText(nutritionSession.getTimestamp().toString());
        lblType.setText(nutritionSession.getType().getLabel());
        lblName.setText(nutritionSession.getName());
        lblCalories.setText(nutritionSession.getCalories().toString());

        return nutritionSessionView;
    }
}
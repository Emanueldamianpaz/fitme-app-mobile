package ar.davinci.edu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.model.dto.ExerciseDTO;

public class ExerciseAdapter extends BaseAdapter {

    private Context context;
    private List<ExerciseDTO> exerciseList;

    private ExerciseAdapter() {
    }

    public ExerciseAdapter(Context context, List<ExerciseDTO> exerciseList) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int i) {
        return exerciseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (exerciseList.get(i).getId());
    }

    @Override
    public View getView(int i, View exerciseView, ViewGroup viewGroup) {
        exerciseView = LayoutInflater.from(context).inflate(R.layout.fragment_item_exercise, viewGroup, false);
        TextView itemTitle = (TextView) exerciseView.findViewById(R.id.kilometersRunnedLbl);

        LinearLayout showMap = (LinearLayout) exerciseView.findViewById(R.id.lylShowMap);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Abrir mapa // o no... es complicado ya que lee el kml local... habría que generar uno nuevo según listCoordinates
            }
        });

        ExerciseDTO exercise = exerciseList.get(i);
        itemTitle.setText(exercise.getId() + ". " + exercise.getKilometersRunned() + "km. - " + exercise.getTimestamptRunned().get(0));


        return exerciseView;
    }
}
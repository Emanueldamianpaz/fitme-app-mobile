package ar.davinci.edu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.model.Routine;

public class RoutineAdapter extends BaseAdapter {

    private Context context;
    private List<Routine> routineList;

    private RoutineAdapter() {
    }

    public RoutineAdapter(Context context, Set<Routine> setRoutines) {
        List<Routine> routineList = new ArrayList<>();
        routineList.addAll(setRoutines);

        this.routineList = routineList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return routineList.size();
    }

    @Override
    public Object getItem(int i) {
        return routineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (routineList.get(i).getId());
    }

    @Override
    public View getView(int i, View routineView, ViewGroup viewGroup) {

        routineView = LayoutInflater.from(context).inflate(R.layout.fragment_item_routine, viewGroup, false);

        TextView itemTitle = (TextView) routineView.findViewById(R.id.kilometersRunnedLbl);

        Routine routine = routineList.get(i);

        itemTitle.setText(routine.getName());


        return routineView;
    }
}
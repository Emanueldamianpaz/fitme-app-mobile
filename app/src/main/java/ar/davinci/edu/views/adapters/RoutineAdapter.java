package ar.davinci.edu.views.adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.fragments.UserRoutineFragment;

public class RoutineAdapter extends BaseAdapter {

    private AppCompatActivity context;
    private List<UserRoutine> routineList;
    private Gson gson;

    public RoutineAdapter(AppCompatActivity context, List<UserRoutine> setRoutines) {
        gson = new GsonBuilder().create();
        this.routineList = setRoutines;
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
        routineView = LayoutInflater.from(context).inflate(R.layout.fragment_item_user_routine, viewGroup, false);
        TextView lblName = routineView.findViewById(R.id.lblName);
        TextView lblDescription = routineView.findViewById(R.id.lblDescription);
        Button btnShowDetail = routineView.findViewById(R.id.btnShowDetail);

        UserRoutine routine = routineList.get(i);

        lblName.setText(routine.getRoutineTemplate().getName());
        lblDescription.setText(routine.getRoutineTemplate().getDescription());

        Bundle args = new Bundle();

        UserRoutineFragment userRoutineFragment = new UserRoutineFragment();

        args.putString("userRoutine", gson.toJson(routine));
        userRoutineFragment.setArguments(args);


        btnShowDetail.setOnClickListener(v -> Helper.changeFragments(context, userRoutineFragment));

        return routineView;
    }
}
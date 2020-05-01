package ar.davinci.edu.views.adapters.userRoutine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.userRoutine.userRoutine.UserRoutineActivity;

public class UserRoutineAdapter extends BaseAdapter {

    private Context context;
    private List<UserRoutine> userRoutineList;

    public UserRoutineAdapter(Context context, List<UserRoutine> userRoutineList) {
        this.userRoutineList = userRoutineList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userRoutineList.size();
    }

    @Override
    public Object getItem(int i) {
        return userRoutineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (userRoutineList.get(i).getId());
    }

    @Override
    public View getView(int i, View routineView, ViewGroup viewGroup) {
        routineView = LayoutInflater.from(context).inflate(R.layout.item_user_routine, viewGroup, false);
        TextView lblName = routineView.findViewById(R.id.lblName);
        TextView lblDescription = routineView.findViewById(R.id.lblDescription);
        Button btnShowDetail = routineView.findViewById(R.id.btnShowDetail);

        UserRoutine routine = userRoutineList.get(i);

        lblName.setText(routine.getRoutineTemplate().getName());
        lblDescription.setText(routine.getRoutineTemplate().getDescription());

        btnShowDetail.setOnClickListener(v -> {
            Intent userRoutineActivity = Helper.getIntent(context, UserRoutineActivity.class);
            userRoutineActivity.putExtra("user_routine", Helper.gson.toJson(routine));
            context.startActivity(userRoutineActivity);
        });

        return routineView;
    }
}
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
import ar.davinci.edu.domain.model.user.detail.UserExperience;

public class UserExperienceAdapter extends BaseAdapter {

    private Context context;
    private List<UserExperience> userExperienceList;

    public UserExperienceAdapter(Context context, List<UserExperience> userExperienceList) {
        this.userExperienceList = userExperienceList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userExperienceList.size();
    }

    @Override
    public Object getItem(int i) {
        return userExperienceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (userExperienceList.get(i).getId());
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View userExperienceView, ViewGroup viewGroup) {
        userExperienceView = LayoutInflater.from(context).inflate(R.layout.item_user_experience, viewGroup, false);
        TextView txtScoring = userExperienceView.findViewById(R.id.lblScoring);
        TextView txtCoachTip = userExperienceView.findViewById(R.id.lblCoachTip);
        TextView txtUserMessage = userExperienceView.findViewById(R.id.lblUserMessage);

        UserExperience userExperience = userExperienceList.get(i);

        txtScoring.setText("Tu puntuación :" + userExperience.getScoring().getLabel());
        if (userExperience.getCoachTip() == null) {
            txtCoachTip.setText("-No hay tip del coach-");
        } else {
            txtCoachTip.setText("Tip coach: " + userExperience.getCoachTip());

        }
        if (userExperience.getUserMessage() == null) {
            txtUserMessage.setText("-No has escrito ningun mensaje para coach-");
        } else {
            txtUserMessage.setText("Tu mensaje: " + userExperience.getUserMessage());
        }

        return userExperienceView;
    }
}
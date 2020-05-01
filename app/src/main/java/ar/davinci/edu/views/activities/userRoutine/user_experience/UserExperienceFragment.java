package ar.davinci.edu.views.activities.userRoutine.user_experience;

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
import ar.davinci.edu.domain.model.user.detail.UserExperience;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.userRoutine.UserExperienceAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserExperienceFragment extends Fragment {

    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    private List<UserExperience> userExperienceList = new ArrayList<>();

    public UserExperienceFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_user_experience, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        Set<UserExperience> uxList = Helper.gson.fromJson(
                args.getString("user_experience", ""),
                new TypeToken<Set<UserExperience>>() {
                }.getType());

        userExperienceList.addAll(uxList);

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        ListView userExperienceList = container.findViewById(R.id.listUserExperience);


        if (this.userExperienceList.size() > 0) {
            userExperienceList.setAdapter(new UserExperienceAdapter(container.getContext(), this.userExperienceList));
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.default_no_result, fragmentNoResult);
        }

    }
}

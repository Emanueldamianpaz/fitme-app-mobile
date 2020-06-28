package ar.davinci.edu.views.activities.userRoutine.user_experience;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.UserExperienceApi;
import ar.davinci.edu.domain.model.user.detail.UserExperience;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import ar.davinci.edu.domain.types.ScoringType;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.adapters.userRoutine.UserExperienceAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserExperienceFragment extends Fragment {

    @BindView(R.id.fragmentNoResult)
    ViewGroup fragmentNoResult;

    private List<UserExperience> userExperienceList = new ArrayList<>();
    private UserRoutine userRoutineSelected;
    private View v;

    public UserExperienceFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_list_user_experience, container, false);
        ButterKnife.bind(this, v);

        Bundle args = getArguments();

        userRoutineSelected = Helper.gson.fromJson(
                args.getString("user_routine", ""),
                new TypeToken<UserRoutine>() {
                }.getType());


        userExperienceList.addAll(userRoutineSelected.getUserExperiences());

        bootstraping();
        return v;
    }

    private void bootstraping() {

        ListView userExperienceList = v.findViewById(R.id.listUserExperience);
        LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.default_no_result, fragmentNoResult);

        if (this.userExperienceList.size() > 0) {
            userExperienceList.setAdapter(new UserExperienceAdapter(v.getContext(), this.userExperienceList));
            fragmentNoResult.setVisibility(View.INVISIBLE);
        } else {
            fragmentNoResult.setVisibility(View.VISIBLE);
        }

    }


    @OnClick(R.id.btnAddUserExperience)
    public void addUserExperience() {
        AlertDialog.Builder saveBuilder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_set_user_experience, null);

        Spinner editScoringUserExperience = customLayout.findViewById(R.id.editScoringUserExpereience);

        ArrayAdapter<ScoringType> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, ScoringType.values());
        editScoringUserExperience.setAdapter(adapter);
        saveBuilder.setView(customLayout);

        saveBuilder.setNegativeButton(getString(R.string.close), (dialogInterface, i) -> dialogInterface.dismiss());
        saveBuilder.setPositiveButton(getString(R.string.save_user_experience),
                (dialog, which) -> {

                    ScoringType scoringType = (ScoringType) editScoringUserExperience.getSelectedItem();

                    UserExperience userExperience = new UserExperience(scoringType);


                    UserExperienceApi.createUserExperienceForUserRoutine(body ->
                                    UserExperienceApi.getUserExperiencesFromUserRoutine(body1 -> {
                                                userExperienceList = (List<UserExperience>) body1;
                                                bootstraping();
                                            },
                                            getContext(),
                                            userRoutineSelected.getId().toString())
                            ,
                            getContext(),
                            userRoutineSelected.getId().toString(),
                            userExperience
                    );

                    dialog.dismiss();
                }
        );

        saveBuilder.setCancelable(false);
        saveBuilder.create().show();
    }
}

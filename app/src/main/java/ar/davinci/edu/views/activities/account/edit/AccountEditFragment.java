package ar.davinci.edu.views.activities.account.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.UserInfoApi;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoLightRequestDTO;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoRequestDTO;
import ar.davinci.edu.domain.model.user.detail.UserGoal;
import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.domain.types.GoalType;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.account.view.AccountViewActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountEditFragment extends Fragment {

    @BindView(R.id.editInitialWeight)
    EditText editInitialWeight;
    @BindView(R.id.editCurrentFat)
    EditText editCurrentFat;
    @BindView(R.id.editFrecuencyExercise)
    EditText editFrecuencyExercise;
    @BindView(R.id.editHeight)
    EditText editHeight;
    @BindView(R.id.editGoalFat)
    EditText editGoalFat;
    @BindView(R.id.editGoalType)
    Spinner editGoalType;

    private UserInfoLightRequestDTO userInfoLight;


    public AccountEditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account_edit, container, false);
        ButterKnife.bind(this, v);


        bootstrapping();


        return v;
    }


    private void bootstrapping() {
        Bundle args = getArguments();

        userInfoLight = Helper.gson.fromJson(
                args.getString("user_info", ""),
                new TypeToken<UserInfoLightRequestDTO>() {
                }.getType());

        ArrayAdapter<GoalType> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                GoalType.values());

        editGoalType.setAdapter(adapter);

        if (userInfoLight.getUserInfo().getInitialWeight() != null) {
            editInitialWeight.setText(userInfoLight.getUserInfo().getInitialWeight().toString());
        }
        if (userInfoLight.getUserInfo().getCurrentFat() != null) {
            editCurrentFat.setText(userInfoLight.getUserInfo().getCurrentFat().toString());
        }
        if (userInfoLight.getUserInfo().getFrecuencyExercise() != null) {
            editFrecuencyExercise.setText(userInfoLight.getUserInfo().getFrecuencyExercise());
        }
        if (userInfoLight.getUserInfo().getHeight() != null) {
            editHeight.setText(userInfoLight.getUserInfo().getHeight());
        }
        if (userInfoLight.getUserInfo().getGoal().getGoalFat() != null) {
            editGoalFat.setText(userInfoLight.getUserInfo().getGoal().getGoalFat().toString());
        }
        if (userInfoLight.getUserInfo().getGoal().getType() != null) {
            editGoalType.setSelection(adapter.getPosition(userInfoLight.getUserInfo().getGoal().getType()));
        }
    }

    @OnClick(R.id.btnSave)
    public void saveEdit() {
        Double initialWeight = Double.parseDouble(editInitialWeight.getText().toString());
        String height = editHeight.getText().toString();
        Double currentFat = Double.parseDouble(editCurrentFat.getText().toString());
        String frecuencyExercise = editFrecuencyExercise.getText().toString();
        Double goalFat = Double.parseDouble(editGoalFat.getText().toString());
        GoalType goalType = (GoalType) editGoalType.getSelectedItem();

        UserInfo userInfoReq = new UserInfo(new UserInfoRequestDTO(initialWeight,
                height,
                currentFat,
                frecuencyExercise,
                new UserGoal(SharedJWT.getJWT().toString(), goalType, goalFat)
        ));

        userInfoReq.setId(SharedJWT.getUserFromSharedP().getId());

        UserInfoApi.updateUserInfo(
                body -> Log.i("", ""),
                getContext(),
                userInfoReq
        );

        startActivity(Helper.getIntent(getContext(), AccountViewActivity.class));
    }
}

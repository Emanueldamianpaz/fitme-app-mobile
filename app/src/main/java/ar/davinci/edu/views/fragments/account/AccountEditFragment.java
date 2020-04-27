package ar.davinci.edu.views.fragments.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.userInfo.UserInfoApi;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoRequestDTO;
import ar.davinci.edu.domain.model.user.detail.UserGoal;
import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.account.AccountViewActivity;
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

    public AccountEditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account_edit, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.btnSave)
    public void saveEdit() {
        Double initialWeight = Double.parseDouble(editInitialWeight.getText().toString());
        String height = editHeight.getText().toString();
        Double currentFat = Double.parseDouble(editCurrentFat.getText().toString());
        String frecuencyExercise = editFrecuencyExercise.getText().toString();

        // TODO Agregar campos de goalType en el form

        UserInfoRequestDTO userInfoReq = new UserInfoRequestDTO(initialWeight,
                height,
                currentFat,
                frecuencyExercise,
                new UserGoal(SharedJWT.getJWT().toString())
        );

        UserInfoApi.updateUserInfo(
                body -> Log.i("", ""),
                getContext(),
                new UserInfo(userInfoReq)
        );

        startActivity(Helper.getIntent(getContext(), AccountViewActivity.class));
    }
}

package ar.davinci.edu.views.activities.account.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ar.davinci.edu.R;
import ar.davinci.edu.clients.apis.UserApi;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoLightRequestDTO;
import ar.davinci.edu.domain.dto.fitme.user.UserInfoRequestDTO;
import ar.davinci.edu.infraestructure.security.FitmeUser;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.account.edit.AccountEditActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountViewFragment extends Fragment {

    @BindView(R.id.imgUser)
    ImageView imgPortrait;
    @BindView(R.id.lblUsername)
    TextView lblUsername;
    @BindView(R.id.lblInitialWeight)
    TextView lblInitialWeight;
    @BindView(R.id.lblCurrentFat)
    TextView lblCurrentFat;
    @BindView(R.id.lblFrecuencyExercise)
    TextView lblFrecuencyExercise;
    @BindView(R.id.lblHeight)
    TextView lblHeight;
    @BindView(R.id.lblGoal)
    TextView lblGoal;

    private UserInfoLightRequestDTO userInfoLight;
    private FitmeUser user;

    public AccountViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account_view, container, false);
        ButterKnife.bind(this, v);

        user = SharedJWT.getUserFromSharedP();
        bootstraping();

        return v;
    }

    private void bootstraping() {

        UserApi.getUserLight(body -> {
                    userInfoLight = (UserInfoLightRequestDTO) body;

                    UserInfoRequestDTO userInfo = userInfoLight.getUserInfo();
                    String message_label = String.format(getString(R.string.message_label), user.getName());

                    String initial_weight_label = String.format(getString(R.string.initial_weight_label_interpolation), getString(R.string.no_info));
                    if (userInfo.getInitialWeight() != null) {
                        initial_weight_label = String.format(getString(R.string.initial_weight_label_interpolation), userInfo.getInitialWeight().toString());
                    }

                    String current_fat_label = String.format(getString(R.string.current_fat_label_interpolation), getString(R.string.no_info));
                    if (userInfo.getCurrentFat() != null) {
                        current_fat_label = String.format(getString(R.string.current_fat_label_interpolation), userInfo.getCurrentFat().toString());
                    }

                    String frecuency_exercise_label = String.format(getString(R.string.frecuency_exercise_label_interpolation), getString(R.string.no_info));
                    if (userInfo.getFrecuencyExercise() != null) {
                        frecuency_exercise_label = String.format(getString(R.string.frecuency_exercise_label_interpolation), userInfo.getFrecuencyExercise());
                    }

                    String height_label = String.format(getString(R.string.height_label_interpolation), getString(R.string.no_info));
                    if (userInfo.getHeight() != null) {
                        height_label = String.format(getString(R.string.height_label_interpolation), userInfo.getHeight());
                    }

                    String goal_label = String.format(getString(R.string.goal_label_interpolation), userInfo.getGoal()
                            .getGoalFat().toString()
                            .concat("kg ")
                            .concat(userInfo.getGoal().getType().getLabel()
                            ));


                    // r2c1a = getString(getResources().getIdentifier("com.myApp:string/" + tempText, null, null));

                    Glide.with(getContext())
                            .load(user.getPicture())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imgPortrait);

                    lblUsername.setText(message_label);
                    lblInitialWeight.setText(initial_weight_label);
                    lblCurrentFat.setText(current_fat_label);
                    lblFrecuencyExercise.setText(frecuency_exercise_label);
                    lblHeight.setText(height_label);
                    lblGoal.setText(goal_label);
                },
                getContext());


    }

    @OnClick(R.id.btnEdit)
    public void editUserInfo() {


        Intent accountEditActivity = Helper.getIntent(getContext(), AccountEditActivity.class);

        accountEditActivity.putExtra("user_info", Helper.gson.toJson(userInfoLight));
        startActivity(accountEditActivity);

    }
}

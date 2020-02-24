package ar.davinci.edu.views.activities.fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;


public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PrefManager.isUserWalking()) {
            startActivity(Helper.getIntent(this, WalkActivity.class));
        } else {
            startActivity(Helper.getIntent(this, RunningSessionActivity.class));
        }


    }

}

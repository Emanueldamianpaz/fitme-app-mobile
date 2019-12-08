package ar.davinci.edu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;

import ar.davinci.edu.R;
import ar.davinci.edu.adapters.RoutineAdapter;
import ar.davinci.edu.fragments.HomeFragment;
import ar.davinci.edu.fragments.RunningFragment;
import ar.davinci.edu.infraestructure.FragmentMng;
import ar.davinci.edu.infraestructure.api.ApiClient;
import ar.davinci.edu.infraestructure.api.OnSuccessCallback;
import ar.davinci.edu.infraestructure.model.User;
import ar.davinci.edu.infraestructure.security.FitmeUser;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.storage.SharedPreferencesManager;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FitmeUser user;
    final ApiClient apiClient = new ApiClient(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        JWT payload = SharedJWT.getJWT();

        user = FitmeUser.builder()
                .id(payload.getSubject())
                .name(payload.getClaim("given_name").asString())
                .last_name(payload.getClaim("family_name").asString())
                .picture(payload.getClaim("picture").asString())
                .gender(payload.getClaim("gender").asString())
                .nickname(payload.getClaim("nickname").asString())
                .email(payload.getClaim("email").asString())
                .build();


        bootstrapping();

        //    getMyRoutines();


    }

    private void getMyRoutines() {
        apiClient.getMyRoutines(
                new OnSuccessCallback() {
                    @Override
                    public void execute(Object body) {
                        User userInfo = (User) body;
                        ListView routineList = (ListView) findViewById(R.id.listItemRoutine);
                        routineList.setAdapter(new RoutineAdapter(getBaseContext(), userInfo.getUserRoutine().getRoutines()));
                    }

                    @Override
                    public void error(Object body) {
                        Toast.makeText(getBaseContext(), "Error!", Toast.LENGTH_LONG).show();

                    }
                }, new Long(1));
    }

    private void bootstrapping() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView lblUsername = headerView.findViewById(R.id.lblUsername);
        lblUsername.setText(user.getNickname());

        TextView lblEmail = headerView.findViewById(R.id.lblEmail);
        lblEmail.setText(user.getEmail());

        FragmentMng.changeFragments(this, new HomeFragment());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.tip:
                Log.i("navbar", "cliqueó tip");
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home_fitme:
                FragmentMng.changeFragments(this, new HomeFragment());
                break;

            case R.id.begin_run:
                FragmentMng.changeFragments(this, new RunningFragment());
                break;

            case R.id.close_session:
                Intent login = new Intent(this, LoginActivity.class);
                SharedPreferencesManager.write(SharedPreferencesManager.CREDENTIAL_FITME, "");
                startActivity(login);
                finish();
                break;

            case R.id.my_account:
                Log.i("de costado", "cliqueó my_account");
                break;

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

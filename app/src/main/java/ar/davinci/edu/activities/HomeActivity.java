package ar.davinci.edu.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import ar.davinci.edu.R;
import ar.davinci.edu.fragments.HomeFragment;
import ar.davinci.edu.fragments.RoutineAdapter;
import ar.davinci.edu.infraestructure.api.ApiClient;
import ar.davinci.edu.infraestructure.api.OnSuccessCallback;
import ar.davinci.edu.infraestructure.model.RoutineDTO;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.homeFragment, new HomeFragment());
        ft.commit();

        final ApiClient apiClient = new ApiClient(getBaseContext());

        apiClient.getRoutines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {

                ListView itemsFlight = (ListView) findViewById(R.id.listItemView);
                itemsFlight.setAdapter(new RoutineAdapter(getBaseContext(), (List<RoutineDTO>) body));

            }
        });
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

            case R.id.close_session:
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                finish();
                break;
            case R.id.my_account:
                Log.i("de costado", "cliqueó my_account");
                break;


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

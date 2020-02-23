package ar.davinci.edu.views.activities.fitness;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.AccountActivity;
import ar.davinci.edu.views.activities.HomeActivity;
import ar.davinci.edu.views.activities.LoginActivity;
import ar.davinci.edu.views.fragments.fitness.WalkFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WalkActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);
        ButterKnife.bind(this);
        Helper.changeFragments(this, new WalkFragment());
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
                startActivity(Helper.getIntent(this, HomeActivity.class));
                break;

            case R.id.begin_run:
                startActivity(Helper.getIntent(this, RunningActivity.class));
                break;

            case R.id.close_session:
                PrefManager.removeSession();
                startActivity(Helper.getIntent(this, LoginActivity.class));
                finish();
                break;

            case R.id.my_account:
                startActivity(Helper.getIntent(this, AccountActivity.class));
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

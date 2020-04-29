package ar.davinci.edu.views.activities.account;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ar.davinci.edu.R;
import ar.davinci.edu.infraestructure.security.FitmeUser;
import ar.davinci.edu.infraestructure.storage.PrefManager;
import ar.davinci.edu.infraestructure.storage.SharedJWT;
import ar.davinci.edu.infraestructure.util.Helper;
import ar.davinci.edu.views.activities.HomeActivity;
import ar.davinci.edu.views.activities.LoginActivity;
import ar.davinci.edu.views.activities.fitness.RunningSessionActivity;
import ar.davinci.edu.views.fragments.account.AccountEditFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountEditActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private FitmeUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);
        bootstrapping();

    }

    private void bootstrapping() {
        ButterKnife.bind(this);

        user = SharedJWT.getUserFromSharedP();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView lblUsername = headerView.findViewById(R.id.lblUsername);
        lblUsername.setText(user.getName() + " " + user.getLast_name());

        TextView lblEmail = headerView.findViewById(R.id.lblEmail);
        lblEmail.setText(user.getEmail());

        ImageView imgUser = headerView.findViewById(R.id.imgUser);

        Glide.with(this)
                .load(user.getPicture())
                .apply(RequestOptions.circleCropTransform())
                .into(imgUser);

        Helper.changeFragments(this, new AccountEditFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home_fitme:
                startActivity(Helper.getIntent(this, HomeActivity.class));
                break;

            case R.id.begin_run:
                startActivity(Helper.getIntent(this, RunningSessionActivity.class));
                break;

            case R.id.close_session:
                PrefManager.removeSession();
                startActivity(Helper.getIntent(this, LoginActivity.class));
                finish();
                break;

            case R.id.my_account:
                startActivity(Helper.getIntent(this, AccountEditActivity.class));
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

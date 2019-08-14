package sg.edu.nus.lussis.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.fragment.RetrievalListFragment;
import sg.edu.nus.lussis.fragment.StoreDisbursementListFragment;
import sg.edu.nus.lussis.model.LoginDTO;
import sg.edu.nus.lussis.session.SessionManager;

public class StoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionMgr;
    private Toast toast;
    private long lastBackPressTime = 0;
    private static int lastClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        sessionMgr = new SessionManager(getApplicationContext());
        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DrawerLayout drawer = findViewById(R.id.store_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        TextView tvName = navHeader.findViewById(R.id.name);

        LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
        tvName.setText(login.getName());

        //toggle open and close the menu drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //loads the fragment based on roleId
        if (savedInstanceState == null) {

            lastClicked = R.id.nav_retrieval_list;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RetrievalListFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_retrieval_list);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.store_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (this.lastBackPressTime < System.currentTimeMillis() - 2000) {
                toast = Toast.makeText(this, "Press back again to close this app", Toast.LENGTH_LONG);
                toast.show();
                this.lastBackPressTime = System.currentTimeMillis();
            } else {
                if (toast != null) {
                    toast.cancel();
                }
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_retrieval_list && lastClicked != id) {
            lastClicked = id;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RetrievalListFragment()).commit();

        } else if (id == R.id.nav_disbursement && lastClicked != id) {
            lastClicked = id;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new StoreDisbursementListFragment()).commit();

        } else if (id == R.id.nav_logout) {
            //Goes back to login screen
            sessionMgr.logoutUser();
        }

        DrawerLayout drawer = findViewById(R.id.store_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}

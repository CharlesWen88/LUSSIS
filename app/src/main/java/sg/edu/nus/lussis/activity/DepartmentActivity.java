package sg.edu.nus.lussis.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
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
import sg.edu.nus.lussis.session.SessionManager;
import sg.edu.nus.lussis.adapter.MyRequisitionsListViewAdapter;
import sg.edu.nus.lussis.fragment.DisbursementListFragment;
import sg.edu.nus.lussis.fragment.MyRequisitionsFragment;
import sg.edu.nus.lussis.fragment.PendingRequisitionsFragment;
import sg.edu.nus.lussis.model.LoginDTO;

public class DepartmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionMgr;

//    ListView listView;
//    MyRequisitionsListViewAdapter adapter;

    //Navigation menu
    NavigationView navigationView;

    private Toast toast;
    private long lastBackPressTime = 0;

    private static int lastClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

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

        DrawerLayout drawer = findViewById(R.id.department_drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //toggle open and close the menu drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //loads the fragment based on roleId
        if (savedInstanceState == null) {

            LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
            int role = Integer.valueOf(login.getRoleId());

            if(role == 1 || role == 4) {
                lastClicked = R.id.nav_pending_requisitions;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PendingRequisitionsFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_pending_requisitions);
            }
            else {
                lastClicked = R.id.nav_my_requisitions;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyRequisitionsFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_my_requisitions);
            }
        }
    }

    //hides nav drawer items
    private void hideItem(int i)
    {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(i != 1 && i !=4)
            nav_Menu.findItem(R.id.nav_pending_requisitions).setVisible(false);
        if(i != 3)
            nav_Menu.findItem(R.id.nav_disbursement).setVisible(false);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
//        searchView.setQueryHint("Search");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (TextUtils.isEmpty(s)){
//                    adapter.filter("");
//                    listView.clearTextFilter();
//                }
//                else {
//                    adapter.filter(s);
//                }
//                return true;
//            }
//        });
//        return true;
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.department_drawer_layout);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_requisitions && lastClicked != id) {
            lastClicked = id;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MyRequisitionsFragment()).commit();

        } else if (id == R.id.nav_pending_requisitions && lastClicked != id) {
            lastClicked = id;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PendingRequisitionsFragment()).commit();

        } else if (id == R.id.nav_disbursement && lastClicked != id) {
            lastClicked = id;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DisbursementListFragment()).commit();

        } else if (id == R.id.nav_logout) {
            //Goes back to login screen
            sessionMgr.logoutUser();
        }

        DrawerLayout drawer = findViewById(R.id.department_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}

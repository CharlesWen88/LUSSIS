package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DepartmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    MyRequisitionsListViewAdapter adapter;

    //Navigation menu
    NavigationView navigationView;

    final String url_Login = "http://10.0.2.2:56287/api/mobileRequisition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //getSupportActionBar().setTitle("My Requisition");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //toggle open and close the menu drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //makes nav menu item show as selected
        //navigationView.getMenu().findItem(R.id.nav_my_requisitions).setChecked(true);
        //navigationView.getMenu().findItem(R.id.nav_my_requisitions).setActionView(null);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MyRequisitionsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_my_requisitions);
        }



//        try {
//            JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("loginDto"));
//
//            new Requisition().execute(jsonObj.getString("EmployeeId"));
//
//            //hide nav menu items
//            //hideItem(jsonObj.getInt("RoleId"));
//
////            JSONArray ja_Requisition = jsonObj.getJSONArray("Requisition");
////            for (int i = 0; i < ja_Requisition.length(); i++) {
////                JSONObject jsonR = ja_Requisition.getJSONObject(i);
////                requisitions.add(0, new Requisition(jsonR.getString("Id"),
////                        jsonR.getString("DateTime").substring(0,10),
////                        jsonR.getString("Status"),
////                        null));
////           }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        title = new String[]{"Battery", "Cpu", "Display", "Memory", "Sensor", "Memory", "Sensor"};
//        description = new String[]{"Battery detail...", "Cpu detail...", "Display detail...", "Memory detail...", "Sensor detail...", "Memory detail...", "Sensor detail..."};
//        icon = new int[]{R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera};

//
////        for (int i =0; i<title.length; i++){
////            Model model = new Model(title[i], description[i], icon[i]);
////            //bind all strings in an array
////            arrayList.add(model);
////        }
//
//        //pass results to listViewAdapter class
//        adapter = new MyRequisitionsListViewAdapter(this, requisitions);
//
//        //bind the adapter to the listview
//        listView.setAdapter(adapter);
//
//        listView = findViewById(R.id.myRequisitionListView);


    }


    private void hideItem(int i)
    {
        navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(i != 1 && i !=4)
            nav_Menu.findItem(R.id.nav_pending_requisitions).setVisible(false);
        if(i != 3)
            nav_Menu.findItem(R.id.nav_disbursement).setVisible(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // Settings thing that i deleted
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_requisitions) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MyRequisitionsFragment()).commit();

        } else if (id == R.id.nav_pending_requisitions) {

        } else if (id == R.id.nav_disbursement) {

        } else if (id == R.id.nav_logout) {
            //Goes back to login screen
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    public class Requisition extends AsyncTask<String, Void, List<Requisition>> {
//        @Override
//        protected List<Requisition> doInBackground(String... strings) {
//            String empId = strings[0];
//
//            OkHttpClient okHttpClient = new OkHttpClient();
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
//                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
//                    .readTimeout(5, TimeUnit.MINUTES); // read timeout
//
//            okHttpClient = builder.build();
//
//
//            //posts the requests
//            Request request = new Request.Builder()
//                    .url(url_Login+"/"+empId)
//                    .build();
//
//            Response response;
//
//            //executes the response and receives the response creates a JSON object from the
//            //response string and uses the roleId to generate the next Activity page
//            try{
//                response = okHttpClient.newCall(request).execute();
//                if(response.isSuccessful() ){
//                    String result = response.body().string();
//                    if(result != null && !result.equalsIgnoreCase("null")){
//                        RequisitionsDTO requisition = new Gson().fromJson(result, RequisitionsDTO.class);
//
//                        JSONObject jsonObj = new JSONObject(result);
//
//                        JSONArray ja_Requisition = jsonObj.getJSONArray("Requisition");
//                        for (int i = 0; i < ja_Requisition.length(); i++) {
//                            JSONObject jsonR = ja_Requisition.getJSONObject(i);
//                            requisitions.add(0, new Requisition(jsonR.getString("Id"),
//                                    jsonR.getString("DateTime").substring(0, 10),
//                                    jsonR.getString("Status"),
//                                    null));
//                        }
//
//
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//
//            return requisitions;
//        }
//
//
//        protected void onPostExecute(List<Requisition> p) {
//
//            //pass results to listViewAdapter class
//            adapter = new MyRequisitionsListViewAdapter(DepartmentActivity.this, p);
//
//            //bind the adapter to the listview
//            listView.setAdapter(adapter);
//        }
//
//    }

}

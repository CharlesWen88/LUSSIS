package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.adapter.DisbursementItemDetailsListViewAdapter;
import sg.edu.nus.lussis.model.DisbursementDTO;
import sg.edu.nus.lussis.model.RequisitionDetailDTO;

public class DisbursementItemDetailsActivity extends AppCompatActivity {

    TextView tvName;
    List<RequisitionDetailDTO> requisitionDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_item_details);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.disbursement_item_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
        String details = intent.getStringExtra("details");
        String disbursement = intent.getStringExtra("disbursement");
        DisbursementDTO dis = new Gson().fromJson(disbursement, DisbursementDTO.class);
        RequisitionDetailDTO req = new Gson().fromJson(details, RequisitionDetailDTO.class);

        for(RequisitionDetailDTO reqDetail : dis.getRequisitionDetails())
        {
            if(req.getStationeryId().equals(reqDetail.getStationeryId()))
                requisitionDetails.add(reqDetail);
        }



        tvName = findViewById(R.id.name);

        tvName.setText(req.getStationery().getDescription());

        DisbursementItemDetailsListViewAdapter adapter = new DisbursementItemDetailsListViewAdapter(this, requisitionDetails);

        ListView listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==  android.R.id.home) {
                finish(); //this method close current activity and return to previous
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

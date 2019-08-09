package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import sg.edu.nus.lussis.model.RequisitionDTO;
import sg.edu.nus.lussis.adapter.MyRequisitionDetailsListViewAdapter;
import sg.edu.nus.lussis.R;

public class MyRequisitionDetailsActivity extends AppCompatActivity {

    TextView tvId, tvDate, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_details);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.requisition_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
        String details = intent.getStringExtra("details");
        RequisitionDTO req = new Gson().fromJson(details, RequisitionDTO.class);

        tvId = findViewById(R.id.rd_form_id);
        tvDate = findViewById(R.id.rd_date);
        tvStatus = findViewById(R.id.rd_status);

        tvId.setText(req.getId());
        tvDate.setText(req.getDateTime());
        tvStatus.setText(req.getStatus());

        MyRequisitionDetailsListViewAdapter adapter = new MyRequisitionDetailsListViewAdapter(this, req.getRequisitionDetails());

        ListView listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);
    }

}

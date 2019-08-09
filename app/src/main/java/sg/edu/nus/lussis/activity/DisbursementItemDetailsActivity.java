package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.model.RequisitionDTO;

public class DisbursementItemDetailsActivity extends AppCompatActivity {

    TextView tvName;

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
        RequisitionDTO req = new Gson().fromJson(details, RequisitionDTO.class);

        tvName = findViewById(R.id.name);

        tvName.setText(req.getId());

        //Todo: Set the correct listview adapter

//        DisbursementItemDetailsListViewAdapter adapter = new DisbursementItemDetailsListViewAdapter(this, req.getRequisitionDetails());
//
//        ListView listView = findViewById(R.id.listView);
//        //bind the adapter to the listview
//        listView.setAdapter(adapter);
    }
}

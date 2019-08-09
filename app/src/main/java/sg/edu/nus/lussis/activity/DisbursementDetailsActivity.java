package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.adapter.DisbursementDetailsListViewAdapter;
import sg.edu.nus.lussis.model.DisbursementDTO;

public class DisbursementDetailsActivity extends AppCompatActivity {

    TextView tvLocation, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_details);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.disbursement_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
        String details = intent.getStringExtra("details");
        DisbursementDTO disbursement = new Gson().fromJson(details, DisbursementDTO.class);

        tvLocation = findViewById(R.id.dd_location);
        tvDate = findViewById(R.id.dd_date);

        tvLocation.setText(disbursement.getCollectionPoint());
        tvDate.setText(disbursement.getDeliveryDateTime());

        DisbursementDetailsListViewAdapter adapter = new DisbursementDetailsListViewAdapter(this, disbursement.getRequisitionDetails());

        ListView listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long id) {

                Intent i = new Intent(DisbursementDetailsActivity.this, DisbursementItemDetailsActivity.class);
                //i.putExtra("details", (new Gson()).toJson(disbursements.get(position)));
                String login = getIntent().getStringExtra("loginDto");
                i.putExtra("loginDto", login);
                startActivity(i);
            }
        });
    }


}

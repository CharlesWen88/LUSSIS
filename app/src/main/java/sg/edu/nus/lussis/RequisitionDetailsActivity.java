package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import sg.edu.nus.lussis.Model.Requisition;

public class RequisitionDetailsActivity extends AppCompatActivity {

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


//        TextView mDetailTv = findViewById(R.id.textView);

        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
        String details = intent.getStringExtra("details");
        Requisition req = new Gson().fromJson(details, Requisition.class);

        tvId = findViewById(R.id.rd_form_id);
        tvDate = findViewById(R.id.rd_date);
        tvStatus = findViewById(R.id.rd_status);

        tvId.setText(req.getId());
        tvDate.setText(req.getDateTime());
        tvStatus.setText(req.getStatus());



//        String mActionBarTitle = intent.getStringExtra("actionBarTitle");
//        String mContent = intent.getStringExtra("contentTv");

        //set actionbar title
//        actionBar.setTitle(mActionBarTitle);
        //set text in textview
//        mDetailTv.setText(mContent);


        RequisitionDetailsListViewAdapter adapter = new RequisitionDetailsListViewAdapter(this, req.getRequisitionDetails());
//
        ListView listView = findViewById(R.id.listView);
//        //bind the adapter to the listview
        listView.setAdapter(adapter);

    }

}

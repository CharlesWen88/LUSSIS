package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import sg.edu.nus.lussis.Model.Requisition;

public class PendingRequisitionDetailsActivity extends AppCompatActivity {

    TextView tvId, tvDate, tvName, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_requisition_details);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.pending_requisition_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
        String details = intent.getStringExtra("details");
        Requisition req = new Gson().fromJson(details, Requisition.class);

        tvId = findViewById(R.id.form_id);
        tvDate = findViewById(R.id.date);
        tvName = findViewById(R.id.name);

        tvId.setText(req.getId());
        tvDate.setText(req.getDateTime());
        tvName.setText(req.getEmployee().getName());

        PendingRequisitionDetailsListViewAdapter adapter = new PendingRequisitionDetailsListViewAdapter(this, req.getRequisitionDetails());

        ListView listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup footerView = (ViewGroup) inflater.inflate(R.layout.pending_requisition_detail_footer, listView, false);
        listView.addFooterView(footerView, null, false);
    }

}

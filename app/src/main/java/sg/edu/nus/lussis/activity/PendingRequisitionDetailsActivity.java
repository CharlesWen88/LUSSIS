package sg.edu.nus.lussis.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sg.edu.nus.lussis.model.LoginDTO;
import sg.edu.nus.lussis.model.Requisition;
import sg.edu.nus.lussis.adapter.PendingRequisitionDetailsListViewAdapter;
import sg.edu.nus.lussis.R;

import static sg.edu.nus.lussis.util.Constants.URL;

public class PendingRequisitionDetailsActivity extends AppCompatActivity {

    final String url_Pending = URL + "mobileRequisition";

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

        final String id = req.getId();

        TextView tvId = findViewById(R.id.form_id);
        TextView tvDate = findViewById(R.id.date);
        TextView tvName = findViewById(R.id.name);

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

        Button btnApprove = findViewById(R.id.approve);
        Button btnReject = findViewById(R.id.reject);
        final EditText etComment = findViewById(R.id.comment);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PendingRequisitionDetailsActivity.this);
                builder.setMessage("Comfirm Approval?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id1) {
                                String comment = etComment.getText().toString();

                                new RequisitionApproval().execute(id, "approve", comment);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id1) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PendingRequisitionDetailsActivity.this);
                builder.setMessage("Comfirm Rejection?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id1) {
                                String comment = etComment.getText().toString();

                                new RequisitionApproval().execute(id, "reject", comment);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id1) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public class RequisitionApproval extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0];
            String button = strings[1];
            String comment = strings[2];

            OkHttpClient okHttpClient = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("Id", id)
                    .add("Button", button)
                    .add("Comment", comment)
                    .build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Pending)
                    .post(formBody)
                    .build();

            Response response;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
                    Intent i = new Intent(PendingRequisitionDetailsActivity.this, DepartmentActivity.class);
                    i.putExtra("loginDto", (new Gson()).toJson(login));
                    startActivity(i);

                    finish();

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}

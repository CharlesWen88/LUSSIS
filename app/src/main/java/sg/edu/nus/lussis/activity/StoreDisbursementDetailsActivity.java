package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.kyanogen.signatureview.SignatureView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.adapter.StoreDisbursementDetailsListViewAdapter;
import sg.edu.nus.lussis.model.DisbursementDTO;
import sg.edu.nus.lussis.model.LoginDTO;
import sg.edu.nus.lussis.model.RequisitionDetailDTO;

import static sg.edu.nus.lussis.util.Constants.URL;

public class StoreDisbursementDetailsActivity extends AppCompatActivity {

    private ListView listView;
    private StoreDisbursementDetailsListViewAdapter adapter;

    private final String url_Disbursement = URL + "mobileDisbursement/";
    private List<RequisitionDetailDTO> requisitionList = new ArrayList<>();

    TextView tvDepartment, tvRep;

    Bitmap bitmapImg;
    Button clearBtn;
    SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_disbursement_details);
        setTitle("Confirm Delivery");

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.store_disbursement_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
        final DisbursementDTO disbursement = new Gson().fromJson(getIntent().getStringExtra("details"), DisbursementDTO.class);

        tvDepartment = findViewById(R.id.department);
        tvRep = findViewById(R.id.name);

        tvDepartment.setText(disbursement.getDepartmentName());
        tvRep.setText(disbursement.getReceivedEmployeeName());

        requisitionList = disbursement.getRequisitionDetails();

        List<RequisitionDetailDTO> requisitionDetails = new ArrayList<>();
        boolean added = false;

        for (RequisitionDetailDTO rd : disbursement.getRequisitionDetails()) {
            for(RequisitionDetailDTO rd1 : requisitionDetails){
                if(rd.getStationeryId().equals(rd1.getStationeryId())){
                    rd1.setQuantityDelivered(String.valueOf(Integer.parseInt(rd1.getQuantityDelivered()) + Integer.parseInt(rd.getQuantityDelivered())));
                    added = true;
                }

            }
            if(!added) {
                requisitionDetails.add(rd);
            }
            added = false;
        }


        //pass results to listViewAdapter class
        adapter = new StoreDisbursementDetailsListViewAdapter(StoreDisbursementDetailsActivity.this, requisitionDetails);

        listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);

        signatureView =  (SignatureView) findViewById(R.id.signature_view);
        clearBtn = (Button) findViewById(R.id.button_signatureClear);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas();
            }
        });

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmapImg = signatureView.getSignatureBitmap();
                String s = new Gson().toJson(disbursement);
                new PostDisbursement().execute(s);
            }
        });
    }


    public class PostDisbursement extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String disbursement = strings[0];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            Response response;

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, disbursement);

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Disbursement)
                    .post(body)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    Intent i = new Intent(StoreDisbursementDetailsActivity.this, StoreActivity.class);
                    String login = getIntent().getStringExtra("loginDto");
                    i.putExtra("loginDto", login);
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
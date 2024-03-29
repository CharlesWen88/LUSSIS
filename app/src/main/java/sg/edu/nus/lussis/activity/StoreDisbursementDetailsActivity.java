package sg.edu.nus.lussis.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
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
    Button clearBtn, beginDeliveryBtn;
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
            rd.setQuantityRetrieved(rd.getQuantityDelivered());
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

        for (RequisitionDetailDTO rd : disbursement.getRequisitionDetails()) {
            rd.setTempQty(rd.getQuantityDelivered());
        }


        //pass results to listViewAdapter class
        adapter = new StoreDisbursementDetailsListViewAdapter(StoreDisbursementDetailsActivity.this, requisitionDetails);

        listView = findViewById(R.id.listView);
        //bind the adapter to the listview
        listView.setAdapter(adapter);

        beginDeliveryBtn = findViewById(R.id.begin_delivery);

        if(disbursement.getOnRoute()) {
            beginDeliveryBtn.setOnClickListener(null);
            beginDeliveryBtn.setTextColor(Color.RED);
            beginDeliveryBtn.setText("In transit...");
        }

        beginDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostOnRoute().execute(disbursement.getId());
            }
        });

        signatureView = findViewById(R.id.signature_view);

        clearBtn = findViewById(R.id.button_signatureClear);

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
                boolean checker = false;

                for(int i=0; i< bitmapImg.getWidth(); i+=15){
                    for(int j=0; j< bitmapImg.getHeight(); j+=15){
                        if(bitmapImg.getPixel(i,j) != -1) {
                            checker = true;
                            break;
                        }
                    }
                }

                if (!checker) {
                    Toast.makeText(StoreDisbursementDetailsActivity.this, "Signature cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StoreDisbursementDetailsActivity.this);
                    builder.setMessage("Confirm Disbursement?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id1) {

                                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

                                    List<String> arr = new ArrayList<>();
                                    int temp;
                                    for (RequisitionDetailDTO rd : disbursement.getRequisitionDetails()) {
                                        arr.add(rd.getStationeryId());
                                        for (RequisitionDetailDTO rd1 : disbursement.getRequisitionDetails()) {
                                            if (Integer.parseInt(rd.getId()) < Integer.parseInt(rd1.getId()) && rd.getStationeryId().equals(rd1.getStationeryId())
                                                    && Integer.parseInt(rd.getQuantityDelivered()) > Integer.parseInt(rd.getQuantityRetrieved())) {
                                                temp = Integer.parseInt(rd.getQuantityDelivered()) - Integer.parseInt(rd.getQuantityRetrieved());
                                                rd.setQuantityDelivered(rd.getQuantityRetrieved());
                                                rd1.setQuantityDelivered(String.valueOf(temp));
                                            }
                                        }
                                    }
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmapImg.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    byte[] byteArray = stream.toByteArray();
                                    bitmapImg.recycle();

                                    String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                    disbursement.setSignature(encodedImage);
                                    String s = new Gson().toJson(disbursement);
                                    new PostDisbursement().execute(s);
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
            RequestBody body = RequestBody.create(disbursement, JSON);

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

    public class PostOnRoute extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String s = strings[0];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            Response response;

            //posts the requests
            RequestBody formBody = new FormBody.Builder()
                    .add("s", s)
                    .build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Disbursement + "/track/" + s)
                    .post(formBody)
                    .build();

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    beginDeliveryBtn.setOnClickListener(null);
                    beginDeliveryBtn.setTextColor(Color.RED);
                    beginDeliveryBtn.setText("In transit...");

                    Intent i = new Intent(StoreDisbursementDetailsActivity.this, TrackerActivity.class);
                    String login = getIntent().getStringExtra("loginDto");
                    i.putExtra("loginDto", login);
                    startActivity(i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
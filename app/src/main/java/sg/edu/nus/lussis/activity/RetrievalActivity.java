package sg.edu.nus.lussis.activity;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.adapter.RetrievalListViewAdapter;
import sg.edu.nus.lussis.model.LoginDTO;
import sg.edu.nus.lussis.model.RetrievalDTO;
import sg.edu.nus.lussis.model.RetrievalItemDTO;

import static sg.edu.nus.lussis.util.Constants.URL;

public class RetrievalActivity extends AppCompatActivity {

    private ListView listView;
    private RetrievalListViewAdapter adapter;

    private final String url_Retrieval = URL + "mobileRetrieval/";
    private List<RetrievalItemDTO> retrievalItemList = new ArrayList<>();

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);
        setTitle("Retrieval List");

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.retrieval_list_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
        new GetRetrievalList().execute(login.getEmployeeId());
    }

    public class GetRetrievalList extends AsyncTask<String, Void, RetrievalDTO> {
        @Override
        protected RetrievalDTO doInBackground(String... strings) {
            String empId = strings[0];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Retrieval + empId)
                    .build();

            Response response;
            RetrievalDTO retrieval = null;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    String result = response.body().string();
                    if(!result.equalsIgnoreCase("null")){
                        retrieval = new Gson().fromJson(result, RetrievalDTO.class);

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return retrieval;
        }

        protected void onPostExecute(final RetrievalDTO retrieval) {

            tvDate = findViewById(R.id.date);
            tvDate.setText(retrieval.getRetrievalDate());

            retrievalItemList = retrieval.getRetrievalItem();

            //pass results to listViewAdapter class
            adapter = new RetrievalListViewAdapter(RetrievalActivity.this, retrievalItemList);

            listView = findViewById(R.id.listView);
            //bind the adapter to the listview
            listView.setAdapter(adapter);

            Button submit = findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LoginDTO login = new Gson().fromJson(getIntent().getStringExtra("loginDto"), LoginDTO.class);
                    String s = new Gson().toJson(retrieval);
                    new PostRetrievalList().execute(login.getEmployeeId(), s);
                }
            });
        }

    }

    public class PostRetrievalList extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String empId = strings[0];
            String retrieval = strings[1];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            Response response;

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, retrieval);

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Retrieval + empId)
                    .post(body)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    Intent i = new Intent(RetrievalActivity.this, StoreActivity.class);
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

package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sg.edu.nus.lussis.Model.Requisition;
import sg.edu.nus.lussis.Model.RequisitionsDTO;

import static sg.edu.nus.lussis.Util.Constants.URL;

public class PendingRequisitionsFragment extends Fragment {

    Button btnApprove, btnReject;

    private ListView listView;
    private PendingRequisitionsListViewAdapter adapter;

    private final String url_Pending_Requisitions = URL + "mobileRequisition/pending/";
    private List<Requisition> requisitions = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        if(getActivity() != null)
            getActivity().setTitle("My Requisitions");

        if(getView() != null) {
            btnApprove = getView().findViewById(R.id.approve);
            btnApprove = getView().findViewById(R.id.reject);
        }

        try {
            //retrieves the intent from previous activity to get the employeeId
            JSONObject jsonObj = new JSONObject(getActivity().getIntent().getStringExtra("loginDto"));

            new GetPendingRequisitions().execute(jsonObj.getString("EmployeeId"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public class GetPendingRequisitions extends AsyncTask<String, Void, List<Requisition>> {
        @Override
        protected List<Requisition> doInBackground(String... strings) {
            String empId = strings[0];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Pending_Requisitions + empId)
                    .build();

            Response response;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (!result.equalsIgnoreCase("null")) {
                        RequisitionsDTO req = new Gson().fromJson(result, RequisitionsDTO.class);

                        requisitions = req.getRequisitions();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return requisitions;
        }

        protected void onPostExecute(final List<Requisition> reqList) {

            //pass results to listViewAdapter class
            adapter = new PendingRequisitionsListViewAdapter(getActivity(), reqList);

            if(getView() != null) {
                listView = getView().findViewById(R.id.listView);
                //bind the adapter to the listview
                listView.setAdapter(adapter);

                //move to PendingRequisitionsDetailsActivity later
                LayoutInflater inflater = getLayoutInflater();
                ViewGroup footerView = (ViewGroup) inflater.inflate(R.layout.pending_requisition_detail_footer, listView, false);
                listView.addFooterView(footerView, null, false);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long id) {

//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            new RequisitionDetailsActivity()).addToBackStack(null).commit();


                        // Toast.makeText(getApplicationContext(),"Title => "+items.get(position), Toast.LENGTH_SHORT).show();

//                    System.out.println("=========== Click");
//                    bean = (ActivitiesBean) adapter.getItem(position);
//
                        Intent i = new Intent(getActivity(), RequisitionDetailsActivity.class);
                        i.putExtra("details", (new Gson()).toJson(reqList.get(position)));
                        String login;
                        if(getActivity() != null) {
                            login = getActivity().getIntent().getStringExtra("loginDto");
                            i.putExtra("loginDto", login);
                        }
                        startActivity(i);
                    }
                });
            }

        }

    }
}

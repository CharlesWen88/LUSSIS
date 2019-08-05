package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sg.edu.nus.lussis.Model.Requisition;
import sg.edu.nus.lussis.Model.RequisitionsDTO;

public class MyRequisitionsFragment extends Fragment {

    private ListView listView;
    private MyRequisitionsListViewAdapter adapter;

    private final String url_Login = "http://10.0.2.2:56287/api/mobileRequisition";
    private List<Requisition> requisitions = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        getActivity().setTitle("My Requisitions");

        try {
            //retrieves the intent from previous activity to get the employeeId
            JSONObject jsonObj = new JSONObject(getActivity().getIntent().getStringExtra("loginDto"));

            new GetRequisitions().execute(jsonObj.getString("EmployeeId"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }

    public class GetRequisitions extends AsyncTask<String, Void, List<Requisition>> {
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
                    .url(url_Login+"/"+empId)
                    .build();

            Response response;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    String result = response.body().string();
                    if(result != null && !result.equalsIgnoreCase("null")){
                        RequisitionsDTO req = new Gson().fromJson(result, RequisitionsDTO.class);

                        requisitions = req.getRequisitions();
                        Collections.reverse(requisitions);

//                        JSONObject jsonObj = new JSONObject(result);
//
//                        JSONArray ja_Requisition = jsonObj.getJSONArray("Requisition");
//                        for (int i = 0; i < ja_Requisition.length(); i++) {
//                            JSONObject jsonR = ja_Requisition.getJSONObject(i);
//                            requisitions.add(0, new Requisition(jsonR.getString("Id"),
//                                    jsonR.getString("DateTime").substring(0, 10),
//                                    jsonR.getString("Status"),
//                                    null));
//                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return requisitions;
        }

        protected void onPostExecute(final List<Requisition> reqList) {

            //pass results to listViewAdapter class
            adapter = new MyRequisitionsListViewAdapter(getActivity(), reqList);

            listView = getView().findViewById(R.id.listView);
            //bind the adapter to the listview
            listView.setAdapter(adapter);
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
                    String login = getActivity().getIntent().getStringExtra("loginDto");
                    i.putExtra("loginDto", login);
                    startActivity(i);
                }
            });

        }

    }


}

package sg.edu.nus.lussis.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

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
import sg.edu.nus.lussis.activity.DisbursementDetailsActivity;
import sg.edu.nus.lussis.adapter.DisbursementListViewAdapter;
import sg.edu.nus.lussis.model.Disbursement;
import sg.edu.nus.lussis.R;

import static sg.edu.nus.lussis.util.Constants.URL;

public class DisbursementListFragment extends Fragment {

    private ListView listView;
    private DisbursementListViewAdapter adapter;

    private final String url_Login = URL + "mobileDisbursement/";
    private List<Disbursement> disbursements = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        if(getActivity() != null)
            getActivity().setTitle("Disbursement List");
        setHasOptionsMenu(true);

        try {
            //retrieves the intent from previous activity to get the employeeId
            JSONObject jsonObj = new JSONObject(getActivity().getIntent().getStringExtra("loginDto"));

            new GetDisbursements().execute(jsonObj.getString("EmployeeId"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public class GetDisbursements extends AsyncTask<String, Void, List<Disbursement>> {
        @Override
        protected List<Disbursement> doInBackground(String... strings) {
            String empId = strings[0];

            OkHttpClient okHttpClient;

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES); // read timeout

            okHttpClient = builder.build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Login + "/" + empId)
                    .build();

            Response response;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (!result.equalsIgnoreCase("null")) {
//                        disbursements = new Gson().fromJson(result, RequisitionsDTO.class);
//
//                        disbursements = req.getRequisitions();
                        Collections.reverse(disbursements);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return disbursements;
        }

        protected void onPostExecute(final List<Disbursement> disbursements) {

            //pass results to listViewAdapter class
            adapter = new DisbursementListViewAdapter(getActivity(), disbursements);

            if(getView() != null) {
                listView = getView().findViewById(R.id.listView);
                //bind the adapter to the listview
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long id) {

                        Intent i = new Intent(getActivity(), DisbursementDetailsActivity.class);
                        i.putExtra("details", (new Gson()).toJson(disbursements.get(position)));
                        String login = getActivity().getIntent().getStringExtra("loginDto");
                        i.putExtra("loginDto", login);
                        startActivity(i);
                    }
                });
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }

        });
    }
}

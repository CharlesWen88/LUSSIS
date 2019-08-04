package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class RequisitionDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.fragment_requisition_details, container, false);


        //getActivity().searchView.clearFocus();
    }



    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_requisition_details);
//
////        Toolbar toolbar = findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////        getSupportActionBar().setTitle("Requisition Details");
//
////        ActionBar actionBar = getSupportActionBar();
//        TextView mDetailTv = findViewById(R.id.textView);
//
//        //get data from previous activity when item of listview is clicked using intent
//        Intent intent = getIntent();
//        String mActionBarTitle = intent.getStringExtra("actionBarTitle");
//        String mContent = intent.getStringExtra("contentTv");
//
//        //set actionbar title
////        actionBar.setTitle(mActionBarTitle);
//        //set text in textview
//        mDetailTv.setText(mContent);
//
//    }
}

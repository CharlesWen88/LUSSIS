package sg.edu.nus.lussis.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.activity.AdHocRetrievalChooser;
import sg.edu.nus.lussis.activity.RetrievalActivity;

public class RetrievalListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrieval_list, container, false);

        if (getActivity() != null)
            getActivity().setTitle("Retrieval");
        setHasOptionsMenu(true);

        Button btn1 = view.findViewById(R.id.submit1);
        Button btn2 = view.findViewById(R.id.submit2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), RetrievalActivity.class);
                String login;
                if(getActivity() != null) {
                    login = getActivity().getIntent().getStringExtra("loginDto");
                    i.putExtra("loginDto", login);
                }
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), AdHocRetrievalChooser.class);
                String login;
                if(getActivity() != null) {
                    login = getActivity().getIntent().getStringExtra("loginDto");
                    i.putExtra("loginDto", login);
                }
                startActivity(i);
            }
        });

        return view;
    }
}

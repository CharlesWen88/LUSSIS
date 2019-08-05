package sg.edu.nus.lussis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RequisitionDetailsActivity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_details);

        //shows status bar at the top of the screen
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.requisition_details_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


//        TextView mDetailTv = findViewById(R.id.textView);

        //get data from previous activity when item of listview is clicked using intent
        Intent intent = getIntent();
//        String mActionBarTitle = intent.getStringExtra("actionBarTitle");
//        String mContent = intent.getStringExtra("contentTv");

        //set actionbar title
//        actionBar.setTitle(mActionBarTitle);
        //set text in textview
//        mDetailTv.setText(mContent);

    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This ID represents the Home or Up button. In the case of this
//            // activity, the Up button is shown. For
//            // more details, see the Navigation pattern on Android Design:
//            //
//            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
//            //
//            Intent intent = new Intent(this, DepartmentActivity.class);
//            String login = this.getIntent().getStringExtra("loginDto");
//            intent.putExtra("loginDto", login);
//
//            navigateUpTo(intent);
//
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}

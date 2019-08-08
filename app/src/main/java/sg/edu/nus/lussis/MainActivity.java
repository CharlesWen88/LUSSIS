package sg.edu.nus.lussis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

import sg.edu.nus.lussis.Session.SessionManager;

public class MainActivity extends LoginActivity {

    SessionManager sessionMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionMgr = new SessionManager(getApplicationContext());
        boolean isLoggedIn = sessionMgr.checkLoggedIn();

        if(isLoggedIn){
            HashMap<String, String> empLogin = sessionMgr.getEmployeeDetails();
            String username = empLogin.get(SessionManager.KEY_USERNAME);
            String password = empLogin.get(SessionManager.KEY_PASSWORD);
            new LoginUser().execute(username, password);
        }
        else{
            // user is not logged in redirect to Login Activity
            Intent i = new Intent(this, LoginActivity.class);
            // Close all the Activities
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            this.startActivity(i);
        }
    }
}

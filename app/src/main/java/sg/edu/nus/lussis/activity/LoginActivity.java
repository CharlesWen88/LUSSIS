package sg.edu.nus.lussis.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sg.edu.nus.lussis.util.LoginErrorDialog;
import sg.edu.nus.lussis.model.LoginDTO;
import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.Session.SessionManager;

import static sg.edu.nus.lussis.util.Constants.URL;


public class LoginActivity extends AppCompatActivity {
//class written by Charles

    EditText etUsername, etPassword;
    Button btnLogin;

    final String url_Login = URL + "mobilelogin";

    SessionManager sessionMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        sessionMgr = new SessionManager(getApplicationContext());

        boolean isLoggedIn = sessionMgr.checkLoggedIn();

        if(isLoggedIn){
            HashMap<String, String> empLogin = sessionMgr.getEmployeeDetails();
            String username = empLogin.get(SessionManager.KEY_USERNAME);
            String password = empLogin.get(SessionManager.KEY_PASSWORD);
            new LoginUser().execute(username, password);
        }
        else {

            setContentView(R.layout.activity_login);

            etUsername = findViewById(R.id.username);
            etPassword = findViewById(R.id.password);
            btnLogin = findViewById(R.id.login);

            etUsername.addTextChangedListener(loginTextWatcher);
            etPassword.addTextChangedListener(loginTextWatcher);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    new LoginUser().execute(username, password);
                }
            });

        }

    }

    public class LoginUser extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();

            //adds username and password to the formbody
            RequestBody formBody = new FormBody.Builder()
                    .add("Username", username)
                    .add("Password", password)
                    .build();

            //posts the requests
            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();

            Response response;

            //executes the response and receives the response creates a JSON object from the
            //response string and uses the roleId to generate the next Activity page
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful() ){
                    String result = response.body().string();
                    if(!result.equalsIgnoreCase("null")){

                        LoginDTO login = new Gson().fromJson(result, LoginDTO.class);

                            sessionMgr.createLoginSession(username, password);

                            Intent i;

                        if(Integer.valueOf(login.getRoleId())<5)
                            i = new Intent(LoginActivity.this, DepartmentActivity.class);
                        else
                            i = new Intent(LoginActivity.this, DepartmentActivity.class);
                        i.putExtra("loginDto", (new Gson()).toJson(login));
                        startActivity(i);

                        finish();

                    }else{
                        openLoginErrorDialog();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = etUsername.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            if(!usernameInput.isEmpty() && !passwordInput.isEmpty())
            {
                btnLogin.setEnabled(true);
                btnLogin.setBackgroundResource(R.drawable.button_login);
            }
            else
            {
                btnLogin.setEnabled(false);
                btnLogin.setBackgroundResource(R.drawable.button_login_default);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void openLoginErrorDialog() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        LoginErrorDialog loginErrorDialog = new LoginErrorDialog();
        loginErrorDialog.show(getSupportFragmentManager(), "dialog");
    }
}

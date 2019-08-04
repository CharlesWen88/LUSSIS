package sg.edu.nus.lussis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sg.edu.nus.lussis.Model.LoginDTO;

public class LoginActivity extends AppCompatActivity {
//class written by Charles

    EditText etUsername, etPassword;
    Button btnLogin;

    final String url_Login = "http://10.0.2.2:56287/api/mobilelogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
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
//                        String result = response.body().string().replace("\\", "");
//                        result = result.substring(1,result.length()-1);
                        if(result != null && !result.equalsIgnoreCase("null")){

                            LoginDTO login = new Gson().fromJson(result, LoginDTO.class);

                            JSONObject jsonObj = new JSONObject(result);
                            int role = jsonObj.getInt("RoleId");
                            Intent i;
                            switch (role){
                                case 1:
                                case 4:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    i.putExtra("loginDto", jsonObj.toString());
                                    startActivity(i);
                                    break;
                                case 2:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    startActivity(i);
                                    break;
                                case 3:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    startActivity(i);
                                    break;
                                case 5:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    startActivity(i);
                                    break;
                                case 6:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    startActivity(i);
                                    break;
                                default:
                                    i = new Intent(LoginActivity.this, DepartmentActivity.class);
                                    startActivity(i);
                                    break;
                            }
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

    private void openLoginErrorDialog() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        LoginErrorDialog loginErrorDialog = new LoginErrorDialog();
        loginErrorDialog.show(getSupportFragmentManager(), "dialog");
    }
}

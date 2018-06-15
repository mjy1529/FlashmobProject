package com.flashmob_team.usr.flashmob_project.Login;

import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Main.MainViewActivity;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;
import com.flashmob_team.usr.flashmob_project.SignUp.SignUpActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText login_email_edit;
    EditText login_pw_edit;
    Button login_button;
    Button login_signup_button;

    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startActivity(new Intent(this, SplashActivity.class));

        service = ApplicationController.getInstance().getNetworkService();

        login_email_edit = (EditText) findViewById(R.id.login_email_editText);
        login_pw_edit = (EditText) findViewById(R.id.login_pw_editText);
        login_button = (Button) findViewById(R.id.login_button);
        login_signup_button = (Button) findViewById(R.id.login_signup_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login_email_edit.getText().toString().equals("") && login_pw_edit.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if (login_pw_edit.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    // login 통신
                    Log.d("Log", "LogIn : button_click");

                    LoginPost loginPost = new LoginPost();
                    loginPost.user_email = login_email_edit.getText().toString();
                    loginPost.user_pw = login_pw_edit.getText().toString();

                    retrofit2.Call<LoginResult> loginResultCall = service.getLoginInResult(loginPost);
                    loginResultCall.enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                            if (response.isSuccessful()) {
                                if (response.body().message.equals("Login Failed")) {
                                    Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.body().message.equals("Login Success")) {
                                    Log.d("Log", "LogIn : success");

                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putInt("user_id", response.body().user_id);
                                    editor.commit();

                                    Log.d("Log", String.valueOf(pref.getInt("user_id", 0)));

                                    Intent intent = new Intent(LoginActivity.this, MainViewActivity.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                    //finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResult> call, Throwable t) {
                            Log.d("Log", "LogIn : fail");
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

        login_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}

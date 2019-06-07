package com.example.petbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView registerButton;
    private Button loginbtn;

    private EditText editTextID;
    private EditText editTextPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = (TextView) findViewById(R.id.registerButton);//회원가입버튼
        loginbtn = (Button) findViewById(R.id.loginButton);

        editTextID = (EditText) findViewById(R.id.idText);
        editTextPW = (EditText) findViewById(R.id.passwordText);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override//회원가입클릭했을때 registeractivity로 넘어가기
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObject logindata = new JsonObject();
                logindata.addProperty("userID", editTextID.getText().toString());
                logindata.addProperty("pw", editTextPW.getText().toString());

                RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
                Call<JsonObject> login = retrofitCommnunication.userLogin(logindata);

                login.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().get("code").getAsInt() == 200) {

                            Log.d("Get", response.body().toString());

                            JsonArray jsonArray = response.body().getAsJsonArray("result");
                            LoginData.getInstance().setUserID(jsonArray.get(0).getAsJsonObject().get("userID").getAsString());
                            LoginData.getInstance().setPw(jsonArray.get(0).getAsJsonObject().get("pw").getAsString());
                            LoginData.getInstance().setName(jsonArray.get(0).getAsJsonObject().get("name").getAsString());
                            LoginData.getInstance().setPhone(jsonArray.get(0).getAsJsonObject().get("phone").getAsString());
                            LoginData.getInstance().setGender(jsonArray.get(0).getAsJsonObject().get("gender").getAsString());

                            Intent intent = new Intent(LoginActivity.this, FirstpageActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, response.body().get("result").getAsString(), Toast.LENGTH_SHORT)
                            .show();
                        }

                    }


                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                                .show();
                        Log.e("TAG", "onFailure: " + t.getMessage() );
                    }
                });


            }
        });
    }
}

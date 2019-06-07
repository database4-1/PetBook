package com.example.petbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyMemInfoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextView idText;
    private TextView passwordText;
    private TextView nameText;
    private TextView phoneNumberText;

    private RadioGroup genderGroup;
    private RadioButton genderWomen;
    private RadioButton genderMen;

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_mem_info);

        // Toolbar 설정
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        idText = (TextView) findViewById(R.id.idText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        nameText = (TextView) findViewById(R.id.nameText);
        phoneNumberText = (TextView) findViewById(R.id.phoneNumberText);

        genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        genderMen = (RadioButton) findViewById(R.id.genderMen);
        genderWomen = (RadioButton) findViewById(R.id.genderWomen);

        registerButton = (Button) findViewById(R.id.registerButton);

        idText.setText(LoginData.getInstance().getUserID());
        passwordText.setText(LoginData.getInstance().getPw());
        nameText.setText(LoginData.getInstance().getName());
        phoneNumberText.setText(LoginData.getInstance().getPhone());
        if(LoginData.getInstance().getGender().equals("여성")) {
            genderWomen.setChecked(true);
        }
        else {
            genderMen.setChecked(true);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObject registerdata = new JsonObject();
                registerdata.addProperty("newUserID", idText.getText().toString());
                registerdata.addProperty("pw", passwordText.getText().toString());
                registerdata.addProperty("name", nameText.getText().toString());
                registerdata.addProperty("phone", phoneNumberText.getText().toString());

                RadioButton selectedRdo = (RadioButton) findViewById(genderGroup.getCheckedRadioButtonId());
                registerdata.addProperty("gender", selectedRdo.getText().toString());
                registerdata.addProperty("oldUserID", LoginData.getInstance().getUserID());

                RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
                Call<JsonObject> modify = retrofitCommnunication.modifyInfo(registerdata);

                modify.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.body().get("code").getAsInt() == 200) {
                            Toast.makeText(ModifyMemInfoActivity.this, "회원 정보 수정 성공", Toast.LENGTH_SHORT)
                                    .show();

                            LoginData.getInstance().setUserID(idText.getText().toString());
                            LoginData.getInstance().setPw(passwordText.getText().toString());
                            LoginData.getInstance().setName(nameText.getText().toString());
                            LoginData.getInstance().setPhone(phoneNumberText.getText().toString());
                            LoginData.getInstance().setGender(selectedRdo.getText().toString());

                            finish();
                        }
                        else {
                            Toast.makeText(ModifyMemInfoActivity.this, "회원정보 수정 오류, 아이디 중복", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(ModifyMemInfoActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                                .show();
                        Log.e("TAG", "onFailure: " + t.getMessage() );
                    }
                });


            }
        });

    }

}

package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalInfoPopupActivity extends AppCompatActivity {

    private TextView hospital_name;
    private TextView address;
    private TextView phone;
    private TextView operating_time;
    private TextView hospitalInfo;

    private Button cancel_btn;
    private Button ok_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_hospital_info);

        cancel_btn = findViewById(R.id.cancel_btn);
        ok_btn = findViewById(R.id.ok_btn);

        hospital_name = (TextView) findViewById(R.id.hospital_name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        operating_time = (TextView) findViewById(R.id.operating_time);
        hospitalInfo = (TextView) findViewById(R.id.hospitalInfo);

        Intent intent = getIntent();
        hospital_name.setText(intent.getStringExtra("name"));
        address.setText(intent.getStringExtra("addressDo")+" "+intent.getStringExtra("addressSi")+" "+intent.getStringExtra("addressGu")+" "+intent.getStringExtra("addressDetail"));
        phone.setText(intent.getStringExtra("phone"));
        operating_time.setText(intent.getStringExtra("startTime")+"시 ~ "+intent.getStringExtra("endTime")+"시");


        RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
        Call<JsonObject> avgCost = retrofitCommnunication.hospitalCount(LoginData.getInstance().getHospitalID());
        avgCost.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Gson gson = new Gson();
                JsonObject res = response.body();

                Log.d("Received", res.toString());

                List<HospitalStatics> hospitalStatics = gson.fromJson(res.get("result"), new TypeToken<List<HospitalStatics>>(){}.getType());

                String info = "";
                for(int i=0; i<hospitalStatics.size(); i++){
                    info = info + hospitalStatics.get(i).getSpecies()+"는 "+hospitalStatics.get(i).getCount()+"번의 예약이 있습니다.\n";
                }

                hospitalInfo.setText(info);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(HospitalInfoPopupActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });



        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginData.getInstance().setTmp(2);

                Intent intent = new Intent(HospitalInfoPopupActivity.this, PetListActivity.class);
                intent.putExtra("hospitalID",intent.getStringExtra("hospitalID"));
                startActivity(intent);
                finish();
            }
        });
    }
}

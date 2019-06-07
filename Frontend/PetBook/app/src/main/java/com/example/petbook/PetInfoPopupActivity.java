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

public class PetInfoPopupActivity extends AppCompatActivity {

    private TextView speciesTv;
    private TextView avgCostTv;

    private Button ok_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_pet_info);

        ok_btn = findViewById(R.id.ok_btn);

        speciesTv = (TextView) findViewById(R.id.species);
        avgCostTv = (TextView) findViewById(R.id.avgCost);


        String species = LoginData.getInstance().getSpecies();
        speciesTv.setText(species);

        RetrofitCommnunication retrofitCommnunication = new ServerComm().init();

        Call<JsonObject> avgCost = retrofitCommnunication.petAvgCost(species);

        avgCost.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Gson gson = new Gson();
                JsonObject res = response.body();

                Log.d("Received", res.toString());

                List<CostStatics> costStatics = gson.fromJson(res.get("result"), new TypeToken<List<CostStatics>>(){}.getType());

                String info = "";
                for(int i=0; i<costStatics.size(); i++){
                    info = info + costStatics.get(i).getAge()+"살 "+costStatics.get(i).getWeight()+"kg "+costStatics.get(i).getAvgCost()+"만 원\n";
                }

                avgCostTv.setText(info);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(PetInfoPopupActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}

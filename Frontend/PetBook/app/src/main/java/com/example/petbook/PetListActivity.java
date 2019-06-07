package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PetListRecyclerAdapter recyclerAdapter;

    private String hopitalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // Toolbar 설정
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        //
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, linearLayoutManager.getOrientation())
        );
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        hopitalID = intent.getStringExtra("hospitalID");


        RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
        Call<JsonObject> petsList = retrofitCommnunication.petsList(LoginData.getInstance().getUserID());

        petsList.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Gson gson = new Gson();
                JsonObject res = response.body();

                Log.d("Received", res.toString());

                List<PetProfile> petProfiles = gson.fromJson(res.get("result"), new TypeToken<List<PetProfile>>(){}.getType());

                recyclerAdapter = new PetListRecyclerAdapter(petProfiles, hopitalID);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(PetListActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

}

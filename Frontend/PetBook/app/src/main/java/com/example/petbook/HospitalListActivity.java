package com.example.petbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalListActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HospitalListRecyclerAdapter recyclerAdapter;

    private EditText search;
    private Button searchBtn;

    private String searchContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_hospital_list);

        // Toolbar 설정
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        search = (EditText) findViewById(R.id.hospital_name);
        searchBtn = (Button) findViewById(R.id.search_btn);

        searchContent = search.getText().toString();

        //
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, linearLayoutManager.getOrientation())
        );
        recyclerView.setLayoutManager(linearLayoutManager);


        // 버튼 리스너
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(search.getText().toString());
            }
        });

    }

    private void search(String searchContent) {

        RetrofitCommnunication retrofitCommnunication = new ServerComm().init();

        Call<JsonObject> searchHospital = retrofitCommnunication.hospitalList(searchContent);

        searchHospital.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Gson gson = new Gson();
                JsonObject res = response.body();

                Log.d("Received", res.toString());

                List<HospitalProfile> hospitalProfiles = gson.fromJson(res.get("result"), new TypeToken<List<HospitalProfile>>(){}.getType());

                recyclerAdapter = new HospitalListRecyclerAdapter(hospitalProfiles);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        search(searchContent);

    }


}

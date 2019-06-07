package com.example.petbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstpageActivity extends AppCompatActivity {

    private Button reserveHospital;
    private Button reserveHaircut;
    private Button etc;
    private Button mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        reserveHospital = (Button) findViewById(R.id.reserveHospital);
        reserveHaircut = (Button) findViewById(R.id.reserveHaircut);
        etc = (Button) findViewById(R.id.etc);
        mypage = (Button) findViewById(R.id.mypage);

        reserveHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstpageActivity.this, HospitalListActivity.class);
                startActivity(intent);
            }
        });
        reserveHaircut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstpageActivity.this, FirstpageActivity.class);
                startActivity(intent);
            }
        });
        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstpageActivity.this, FirstpageActivity.class);
                startActivity(intent);
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstpageActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.petbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class FirstpageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

//        Toast.makeText(FirstpageActivity.this, LoginData.getInstance().getUserID()+" "+LoginData.getInstance().getPw()+" "+LoginData.getInstance().getName()+" "+LoginData.getInstance().getGender()+" "+LoginData.getInstance().getPhone(), Toast.LENGTH_LONG)
//                .show();

    }
}

package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MyPageActivity extends AppCompatActivity {

    private Button appointmentInquiryBtn;
    private Button petInquiryBtn;
    private Button petRegisterBtn;
    private Button userInfoModifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        appointmentInquiryBtn = (Button) findViewById(R.id.appointment_inquiry);
        petInquiryBtn = (Button) findViewById(R.id.pet_inquiry);
        petRegisterBtn = (Button) findViewById(R.id.pet_register);
        userInfoModifyBtn = (Button) findViewById(R.id.user_info_modify);

        appointmentInquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, AppointmentCheckAcitivity.class);
                startActivity(intent);
            }
        });

        petInquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, PetListActivity.class);
                LoginData.getInstance().setTmp(1);
                startActivity(intent);
            }
        });

        petRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, RegisterPetActivity.class);
                startActivity(intent);
            }
        });

        userInfoModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, ModifyMemInfoActivity.class);
                startActivity(intent);
            }
        });


    }


}

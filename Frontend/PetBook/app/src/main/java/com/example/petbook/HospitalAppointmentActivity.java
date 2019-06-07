package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalAppointmentActivity extends AppCompatActivity {

    private EditText reasonText;

    private Button appointmentBtn;

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_appointment);

        reasonText = (EditText) findViewById(R.id.reasonText);


       Intent intent = getIntent();

        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);

        appointmentBtn = (Button) findViewById(R.id.appointmentBtn);

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date;
                int time;

                int month = mDatePicker.getMonth()+1;

                if(mDatePicker.getMonth()<10){
                    if(mDatePicker.getDayOfMonth()<10) {
                        date = mDatePicker.getYear()+"-0"+month+"-0"+mDatePicker.getDayOfMonth();
                        time = mTimePicker.getHour();
                    }
                    else {
                        date = mDatePicker.getYear()+"-0"+month+"-"+mDatePicker.getDayOfMonth();
                        time = mTimePicker.getHour();
                    }
                }
                else {
                    if(mDatePicker.getDayOfMonth()<10) {
                        date = mDatePicker.getYear()+"-"+month+"-0"+mDatePicker.getDayOfMonth();
                        time = mTimePicker.getHour();
                    }
                    else {
                        date = mDatePicker.getYear()+"-"+month+"-"+mDatePicker.getDayOfMonth();
                        time = mTimePicker.getHour();
                    }
                }


                AppointmentData appointmentData = new AppointmentData(LoginData.getInstance().getUserID(), LoginData.getInstance().getPetID(), LoginData.getInstance().getHospitalID(), date,time,reasonText.getText().toString());

                RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
                Call<JsonObject> petsList = retrofitCommnunication.hospitalAppointment(appointmentData);

                petsList.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        JsonObject res = response.body();

                        Log.d("Received", res.toString());

                        Toast.makeText(HospitalAppointmentActivity.this, "예약완료", Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(HospitalAppointmentActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                                .show();
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }
}

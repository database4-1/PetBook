package com.example.petbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPetActivity extends AppCompatActivity {


    ArrayAdapter<CharSequence> adspin1, adspin2;

    String choice_do = "";
    String choice_se = "";

    private EditText name;
    private EditText age;
    private EditText weight;
    private EditText cost;

    private Spinner animalSpinner;
    private Spinner breedSpinner;
    private Spinner yearSpinner;

    private RadioGroup genderGroup;
    private RadioButton genderWomen;
    private RadioButton genderMen;

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        name = (EditText) findViewById(R.id.petnameText);
        age = (EditText) findViewById(R.id.petAge);
        weight = (EditText) findViewById(R.id.petweightText);
        cost = (EditText) findViewById(R.id.petCost);

        genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        genderMen = (RadioButton) findViewById(R.id.genderMen);
        genderWomen = (RadioButton) findViewById(R.id.genderWomen);


        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        animalSpinner = (Spinner) findViewById(R.id.animalSpinner);
        breedSpinner = (Spinner) findViewById(R.id.breedSpinner);

        registerBtn = (Button) findViewById(R.id.registerButton);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalSpinner.setAdapter(adspin1);

        animalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adspin1.getItem(position).equals("강아지")) {
                    choice_do = "강아지";
                    adspin2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinner_do_dog, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    breedSpinner.setAdapter(adspin2);
                    breedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(adspin1.getItem(position).equals("고양이")){
                    choice_do = "고양이";
                    adspin2 = ArrayAdapter.createFromResource(RegisterPetActivity.this, R.array.spinner_do_cat, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    breedSpinner.setAdapter(adspin2);
                    breedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObject registerdata = new JsonObject();
                registerdata.addProperty("userID", LoginData.getInstance().getUserID());
                registerdata.addProperty("name", name.getText().toString());
                registerdata.addProperty("species", choice_do);
                RadioButton selectedRdo = (RadioButton) findViewById(genderGroup.getCheckedRadioButtonId());
                registerdata.addProperty("gender", selectedRdo.getText().toString());
                registerdata.addProperty("age", age.getText().toString());
                registerdata.addProperty("weight", weight.getText().toString());
                registerdata.addProperty("speciesOfSpecies", choice_se);
                registerdata.addProperty("avgCost", cost.getText().toString());


                RetrofitCommnunication retrofitCommnunication = new ServerComm().init();
                Call<JsonObject> register = retrofitCommnunication.petRegister(registerdata);

                register.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.body().get("code").getAsInt() == 200) {
                            Toast.makeText(RegisterPetActivity.this, "애완동물 등록 성공", Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterPetActivity.this, "회원가입 실패, 펫 이름 중복", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(RegisterPetActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                                .show();
                        Log.e("TAG", "onFailure: " + t.getMessage() );
                    }
                });


            }
        });
    }
}

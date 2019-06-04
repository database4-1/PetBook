package com.example.petbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PetActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        spinner = (Spinner) findViewById(R.id.yearSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.animalSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.animal, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.catbreedSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.catbreed, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.dogbreedSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.dogbreed, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

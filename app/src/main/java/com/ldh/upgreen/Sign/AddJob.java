package com.ldh.upgreen.Sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.ldh.upgreen.R;

public class AddJob extends AppCompatActivity {
    private TextInputEditText tietJob,tietWorkPlace,tietDescription;
    private AppCompatButton bBack,bSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String job = tietJob.getText().toString().trim();
                String workPlace = tietWorkPlace.getText().toString().trim();
                String description = tietDescription.getText().toString().trim();
            }
        });
    }
    public void findId(){
        tietJob = findViewById(R.id.tietJob);
        tietWorkPlace = findViewById(R.id.tietWorkPlace);
        tietDescription = findViewById(R.id.tietDescription);
        bBack = findViewById(R.id.bBack);
        bSave = findViewById(R.id.bSave);
    }
}
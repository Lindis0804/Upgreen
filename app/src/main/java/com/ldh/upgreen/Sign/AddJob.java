package com.ldh.upgreen.Sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.ldh.upgreen.Model.Job;
import com.ldh.upgreen.Model.Place;
import com.ldh.upgreen.Model.UserJob;
import com.ldh.upgreen.R;

public class AddJob extends AppCompatActivity {
    private TextInputEditText tietJob,tietWorkPlace,tietDescription,tietAddress;
    private AppCompatButton bBack,bSave;
    private UserJob userJob = new UserJob();
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        findId();
        userJob = (UserJob) getIntent().getSerializableExtra("userJob");
        position = getIntent().getIntExtra("position",0);
        if (userJob!=null){
//            System.out.println(userJob.toString());
            tietJob.setText(userJob.getJob().getTitle());
            tietWorkPlace.setText(userJob.getWorkplace().getPlaceName());
            tietAddress.setText(userJob.getWorkplace().getAddress());
            tietDescription.setText(userJob.getDescription());
        }
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String job = tietJob.getText().toString().trim();
                String workPlace = tietWorkPlace.getText().toString().trim();
                String description = tietDescription.getText().toString().trim();
                String address = tietAddress.getText().toString().trim();
                UserJob userJob = new UserJob(new Job(job),new Place(workPlace,address),description);
                Intent resIntent = new Intent();
                resIntent.putExtra("userJob",userJob);
                resIntent.putExtra("position",position);
                setResult(RESULT_OK,resIntent);
                finish();
            }
        });
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void findId(){
        tietJob = findViewById(R.id.tietJob);
        tietWorkPlace = findViewById(R.id.tietWorkPlace);
        tietDescription = findViewById(R.id.tietDescription);
        tietAddress = findViewById(R.id.tietAddress);
        bBack = findViewById(R.id.bBack);
        bSave = findViewById(R.id.bSave);
    }
}
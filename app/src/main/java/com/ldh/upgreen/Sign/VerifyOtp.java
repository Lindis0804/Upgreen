package com.ldh.upgreen.Sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Otp;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp extends AppCompatActivity {
    private TextView tvGmail;
    private EditText etOtp;
    private AppCompatButton bSignUp;
    private TextView tvSignIn;
    private ImageButton ibBack;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ldh.upgreen.R.layout.activity_verify_otp);
        findId();
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvGmail.setText(getIntent().getStringExtra("gmail"));
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = etOtp.getText().toString().trim();
                if (otp.length() ==0){
                    etOtp.setHintTextColor(getResources().getColor(R.color.pink_red));
                    etOtp.getBackground().setColorFilter(ContextCompat.getColor(getApplication(),R.color.pink_red), PorterDuff.Mode.SRC_ATOP);
                    etOtp.setHint("Otp is empty.");
                }
                else{
                    String gmail = getIntent().getStringExtra("gmail");
                    ApiService.apiService.signUpVerify(new Otp(gmail,otp)).enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                            if (response.code() == 200){
                                DataResponse res = response.body();
                                SharedPreferences.Editor editor = getApplication().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit();
                                editor.putString("token",res.getData().getToken());
                                startActivity(new Intent(VerifyOtp.this,PickRole.class));
                                Toast.makeText(VerifyOtp.this, res.getData().getToken(), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                etOtp.getBackground().setColorFilter(ContextCompat.getColor(getApplication(),R.color.pink_red),PorterDuff.Mode.SRC_ATOP);
                                etOtp.setHintTextColor(getResources().getColor(R.color.deep_green));
                                etOtp.setTextColor(getColor(R.color.pink_red));
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Toast.makeText(VerifyOtp.this, "Verify otp fail.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        etOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etOtp.getBackground().setColorFilter(ContextCompat.getColor(getApplication(),R.color.deep_green),PorterDuff.Mode.SRC_ATOP);
                etOtp.setHintTextColor(getResources().getColor(R.color.deep_green));
                etOtp.setHint("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etOtp.getBackground().setColorFilter(ContextCompat.getColor(getApplication(),R.color.deep_green),PorterDuff.Mode.SRC_ATOP);
                etOtp.setHintTextColor(getResources().getColor(R.color.deep_green));
                etOtp.setHint("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                etOtp.getBackground().setColorFilter(ContextCompat.getColor(getApplication(),R.color.deep_green),PorterDuff.Mode.SRC_ATOP);
                etOtp.setHintTextColor(getResources().getColor(R.color.deep_green));
                etOtp.setHint("");
            }
        });
    }
    public void findId(){
        tvGmail = findViewById(R.id.tvGmail);
        etOtp = findViewById(R.id.etOtp);
        bSignUp = findViewById(R.id.bSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
        ibBack = findViewById(R.id.ibBack);
    }
}
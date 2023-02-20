package com.ldh.upgreen.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSignUp extends Fragment {
    private TextInputEditText tietName, tietUsername, tietPassword, tietPasswordAgain, tietGmail, tietPhone;
    private TextView tvSignIn, tvError;
    private AppCompatButton bSignUp;
    private String name, username, password, passwordAgain, gmail, phone;
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        findId(view);
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = tietPassword.getText().toString().trim();
                passwordAgain = tietPasswordAgain.getText().toString().trim();
                if (password.equals(passwordAgain)) {
                    name = tietName.getText().toString().trim();
                    username = tietUsername.getText().toString().trim();
                    gmail = tietGmail.getText().toString().trim();
                    phone = tietPhone.getText().toString().trim();
                    User user = new User(name, username, password, phone, gmail);
                    ApiService.apiService.signUp(user).enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                            if (response.code() == 200) {
                                Intent intent = new Intent(getActivity(), VerifyOtp.class);
                                intent.putExtra("gmail", gmail);
                                startActivity(intent);
                            } else {
                                try {
                                    DataResponse res = gson.fromJson(response.errorBody().string(), DataResponse.class);
                                    tvError.setText(res.getMessage());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Typing password again incorrectly.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public void findId(View v) {
        tietName = v.findViewById(R.id.tietName);
        tietUsername = v.findViewById(R.id.tietUsername);
        tietPassword = v.findViewById(R.id.tietPassword);
        tietPasswordAgain = v.findViewById(R.id.tietPasswordAgain);
        tietGmail = v.findViewById(R.id.tietGmail);
        tietPhone = v.findViewById(R.id.tietPhone);
        tvSignIn = v.findViewById(R.id.tvSignIn);
        bSignUp = v.findViewById(R.id.bSignUp);
        tvError = v.findViewById(R.id.tvError);
    }
}

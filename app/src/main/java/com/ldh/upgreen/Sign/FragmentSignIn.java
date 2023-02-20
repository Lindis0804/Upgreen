package com.ldh.upgreen.Sign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.View.Homepage.Homepage;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSignIn extends Fragment {
    private TextInputEditText tietUsername,tietPassword;
    private TextView tvForgotPassword;
    private AppCompatButton bSignIn;
    private ImageView ivTwitter,ivFacebook,ivLinkedIn;
    private Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in,container,false);
        findId(view);
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tietUsername.getText().toString().trim();
                String password = tietPassword.getText().toString().trim();
                User user = new User(username,password);
                ApiService.apiService.signIn(user).enqueue(new Callback<DataResponse>() {
                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                        if (response.code() == 200){
                            DataResponse res = response.body();
                            String token = res.getData().getToken();
                            SharedPreferences sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("token",token);
                            editor.commit();
                            startActivity(new Intent(getContext(), Homepage.class));
                        }
                        else{
                            try {
                                DataResponse res = gson.fromJson(response.errorBody().string(),DataResponse.class);
                                Toast.makeText(getContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
    public void findId(View v){
        tietUsername = v.findViewById(R.id.tietUsername);
        tietPassword = v.findViewById(R.id.tietPassword);
        tvForgotPassword = v.findViewById(R.id.tvForgotPassword);
        bSignIn = v.findViewById(R.id.bSignIn);
        ivTwitter = v.findViewById(R.id.ivTwitter);
        ivFacebook = v.findViewById(R.id.ivFacebook);
        ivLinkedIn = v.findViewById(R.id.ivLinkedIn);
    }
}

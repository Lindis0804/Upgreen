package com.ldh.upgreen.View.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.Model.Project;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProject extends AppCompatActivity {
    private TextView tvTitle, tvValidationScore, tvEI, tvERI, tvValidate, tvView, tvComment, tvLike, tvShare, tvIntro, tvNumOfRasingFund, tvSuccess;
    private RecyclerView rvMembers, rvFields, rvInvestor;
    private MemberAdapter memberAdapter;
    private FieldAdapter fieldAdapter;
    private MemberAdapter investorAdapter;
    private String projectId;
    private String token = Default.token;
    private DataResponse res;
    private ArrayList<User> members, investors;
    private ArrayList<Field> fields;
    private TextView tvDetailERI, tvDetailEI;
    private Gson gson = new Gson();
    private ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        token = getSharedPreferences("UserInfo", MODE_PRIVATE).getString("token", "");
        projectId = getIntent().getStringExtra("projectId");
        findId();
        getProjectById(token, projectId);

    }

    public void getProjectById(String token, String projectId) {
        ApiService.apiService.getProjectById(token, projectId).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    res = response.body();
                    Project project = res.getData().getProject();
                    members = project.getMembers();
                    memberAdapter = new MemberAdapter(members, getApplicationContext());
                    investors = project.getInvestors();
                    investorAdapter = new MemberAdapter(investors, getApplicationContext());
                    fields = project.getFields();
                    fieldAdapter = new FieldAdapter(fields);
                    Utils.setRecyclerView(rvMembers, memberAdapter, new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    Utils.setRecyclerView(rvInvestor, investorAdapter, new GridLayoutManager(getApplicationContext(), 2));
                    Utils.setRecyclerView(rvFields, fieldAdapter, new GridLayoutManager(getApplicationContext(), 2));
                    tvTitle.setText(project.getTitle());
                    tvValidationScore.setText(project.getValidationScore() + "");
                    tvEI.setText(project.getEI() + "");
                    tvERI.setText(project.getERI() + "");
                    tvView.setText(project.getViewers().size() + "");
                    tvComment.setText(project.getComments().size() + "");
                    tvLike.setText(project.getLikers().size() + "");
                    tvShare.setText(project.getSharers().size() + "");
                    tvIntro.setText(project.getIntroduction());
                    tvDetailEI.setText("EI (chỉ số môi trường): "+project.getEI() + "");
                    tvDetailERI.setText("ERI (chỉ số giảm phát thải) :"+project.getERI() + "");
                    tvNumOfRasingFund.setText(project.getNumOfRasingFund() + "");
                    tvSuccess.setText(project.getNumOfSuccess() + "");
                    Glide.with(getApplicationContext()).load(project.getPic()).into(ivPic);
                } else {
                    try {
                        res = gson.fromJson(response.errorBody().string(), DataResponse.class);
                        Toast.makeText(ActivityProject.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(ActivityProject.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("TAG", t.getMessage());
            }
        });
    }

    public void findId() {
        tvTitle = findViewById(R.id.tvTitle);
        tvValidate = findViewById(R.id.tvValidate);
        tvEI = findViewById(R.id.tvEI);
        tvERI = findViewById(R.id.tvERI);
        tvValidate = findViewById(R.id.tvValidate);
        tvView = findViewById(R.id.tvView);
        tvComment = findViewById(R.id.tvComment);
        tvLike = findViewById(R.id.tvLike);
        tvShare = findViewById(R.id.tvShare);
        tvIntro = findViewById(R.id.tvIntro);
        tvNumOfRasingFund = findViewById(R.id.tvNumOfRasingFund);
        tvSuccess = findViewById(R.id.tvNumOfSuccess);
        tvDetailEI = findViewById(R.id.tvDetailEI);
        tvDetailERI = findViewById(R.id.tvDetailERI);
        rvFields = findViewById(R.id.rvFields);
        rvInvestor = findViewById(R.id.rvInvestor);
        rvMembers = findViewById(R.id.rvMembers);
        tvValidationScore = findViewById(R.id.tvValidationScore);
        tvValidate = findViewById(R.id.tvValidate);
        ivPic = findViewById(R.id.ivPic);
    }
}
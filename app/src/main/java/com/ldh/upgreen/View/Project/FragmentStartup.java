package com.ldh.upgreen.View.Project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Project;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStartup extends Fragment implements ProjectClickListener {
    private EditText etFindStartUpByName;
    private TextView tvFindByField;
    private RecyclerView rvProjects, rvMyProjects;
    private ProjectAdapter1 projectAdapter;
    private ProjectAdapter2 myProjectAdapter;
    private String token = Default.token;
    private DataResponse res;
    private Gson gson = new Gson();
    private ArrayList<Project> projects, myProjects;
    private BottomSheetProjectOption bottomSheetProjectOption;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_up, container, false);
        token = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("token", "");
        findId(v);
        getMyProjects();
        getProjects();
        return v;
    }

    public void findId(View v) {
        etFindStartUpByName = v.findViewById(R.id.etFindStartUpByName);
        tvFindByField = v.findViewById(R.id.tvFindByField);
        rvProjects = v.findViewById(R.id.rvProjects);
        rvMyProjects = v.findViewById(R.id.rvMyProjects);
    }

    public void getProjects() {
        ApiService.apiService.getAllProject(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    res = response.body();
                    projects = res.getData().getProjects();
                    projectAdapter = new ProjectAdapter1(getContext(), projects, FragmentStartup.this);
                    Utils.setRecyclerView(rvProjects, projectAdapter, new LinearLayoutManager(
                            getContext(),
                            RecyclerView.HORIZONTAL,
                            false
                    ));
                } else {
                    try {
                        res = gson.fromJson(response.errorBody().string(), DataResponse.class);
                        Toast.makeText(getContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("TAG", t.getMessage());
            }
        });
    }

    public void getMyProjects() {
        ApiService.apiService.getMyProjects(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    res = response.body();
                    myProjects = res.getData().getProjects();
                    myProjectAdapter = new ProjectAdapter2(getContext(), myProjects, FragmentStartup.this);
                    Utils.setRecyclerView(rvMyProjects, myProjectAdapter, new LinearLayoutManager(
                            getContext(),
                            RecyclerView.VERTICAL,
                            false
                    ));
                } else {
                    try {
                        res = gson.fromJson(response.errorBody().string(), DataResponse.class);
                        Toast.makeText(getContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("TAG", t.getMessage());
            }
        });
    }

    @Override
    public void onProjectClick(int i) {
    }

    @Override
    public void onMyProjectClick(int i) {
        bottomSheetProjectOption = new BottomSheetProjectOption(FragmentStartup.this,i);
        bottomSheetProjectOption.show(getActivity().getSupportFragmentManager(),"BottomSheetProjectOption");
    }

    @Override
    public void onValidateProject(int i) {
          startActivity(new Intent(getContext(),ChatBot.class));
    }

    @Override
    public void onFixProject(int i) {

    }

    @Override
    public void onDeleteProject(int i) {

    }

    @Override
    public void onProjectDetail(int i) {
      Project project = projects.get(i);
        Intent intent = new Intent(getContext(),ActivityProject.class);
        intent.putExtra("projectId",project.get_id());
        startActivity(intent);
        bottomSheetProjectOption.dismiss();
    }
}

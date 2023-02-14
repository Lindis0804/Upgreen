package com.ldh.upgreen.Sign;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Homepage.Homepage;
import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.Model.Job;
import com.ldh.upgreen.Model.Place;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.Model.UserJob;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPickStartup extends Fragment implements FieldClickListener, JobClickListener, EditUserJobClickListener {
    private ImageButton ibAdd;
    private RecyclerView rvJobs, rvCaredFields;
    private TextView tvBirthday, tvLinkedInLink;
    private EditText etTakingPartInSomeProjects;
    private ImageView ivLinkedInLink;
    private FieldAdapter fieldAdapter;
    private UserJobAdapter userJobAdapter;
    private Gson gson = new Gson();
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Field> caredFields = new ArrayList<>();
    private UserJob userJob;
    private String token = Default.token;
    private ActivityResultLauncher<Intent> resLauncher, editUserJobResLauncher;
    private BottomSheetEditUserJob bottomSheetEditUserJob;
    private AppCompatButton bSave;
    private ArrayList<UserJob> userJobs = new ArrayList<>(Arrays.asList(
            new UserJob(new Job("Mobile Dev"), new Place("MDC Software", "Cau Giay"), "Wonderful")
    ));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pick_start_up, container, false);
        token = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("token", "");
        findId(v);
        getAllFields();
        initializeAddJobResLauncher();
        initializeEditUserJobLauncher();
        Utils.setRecyclerView(rvJobs, userJobAdapter, new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBirthday();
            }
        });
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddJob.class);
                resLauncher.launch(intent);
            }
        });
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String birthday = tvBirthday.getText().toString();
                String takingPartInSomeProjects = etTakingPartInSomeProjects.getText().toString();
                String linkedInLink = tvLinkedInLink.getText().toString();
                ApiService.apiService.updateUser(
                        token,
                        new User(
                                0,//start up
                                userJobs,
                                birthday,
                                caredFields,
                                linkedInLink,
                                takingPartInSomeProjects
                        )
                ).enqueue(new Callback<DataResponse>() {
                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                       if (response.code()==200){
                           DataResponse res = response.body();
                           startActivity(new Intent(getActivity(), Homepage.class));
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
                        Toast.makeText(getContext(),"Update user fail.", Toast.LENGTH_SHORT).show();
                        Log.v("TAG",t.getMessage());
                    }
                });
            }
        });
        return v;
    }

    public void findId(View v) {
        ibAdd = v.findViewById(R.id.ibAdd);
        rvJobs = v.findViewById(R.id.rvJobs);
        rvCaredFields = v.findViewById(R.id.rvCaredFields);
        tvBirthday = v.findViewById(R.id.tvBirthday);
        tvLinkedInLink = v.findViewById(R.id.tvLinkedInLink);
        etTakingPartInSomeProjects = v.findViewById(R.id.etTakingPartInSomeProjects);
        ivLinkedInLink = v.findViewById(R.id.ivLinkedIn);
        userJobAdapter = new UserJobAdapter(userJobs, FragmentPickStartup.this);
        bSave = v.findViewById(R.id.bSave);
    }

    public void getAllFields() {
        ApiService.apiService.getAllField(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    DataResponse res = response.body();
                    fields = res.getData().getFields();
                    Utils.setRecyclerView(
                            rvCaredFields,
                            new FieldAdapter(
                                    fields,
                                    FragmentPickStartup.this
                            ),
                            new GridLayoutManager(
                                    getContext(),
                                    2
                            )
                    );
                } else {
                    try {
                        DataResponse res = gson.fromJson(response.errorBody().string(), DataResponse.class);
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

    public void setBirthday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        tvBirthday.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },
                year,
                month,
                day
        );
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void addJob() {
    }


    @Override
    public void onJobClick(int i) {

    }

    @Override
    public void onFixJob(int i) {
        bottomSheetEditUserJob = (new BottomSheetEditUserJob(i, FragmentPickStartup.this));
        bottomSheetEditUserJob.show(getActivity().getSupportFragmentManager(), "BottomSheetEditUserJob");
    }

    @Override
    public void onEditUserJob(int i) {
        Intent intent = new Intent(getContext(), AddJob.class);
        intent.putExtra("userJob", userJobs.get(i));
        intent.putExtra("position", i);
        editUserJobResLauncher.launch(new Intent(intent));
        bottomSheetEditUserJob.dismiss();
    }

    @Override
    public void onDeleteUserJob(int i) {
        userJobs.remove(i);
        userJobAdapter.notifyDataSetChanged();
        bottomSheetEditUserJob.dismiss();
    }

    public void initializeAddJobResLauncher() {
        resLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent intent = result.getData();
                        if (resultCode == getActivity().RESULT_OK) {
                            userJob = (UserJob) intent.getSerializableExtra("userJob");
                            userJobs.add(userJob);
                            userJobAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    public void initializeEditUserJobLauncher() {
        editUserJobResLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent intent = result.getData();
                        if (resultCode == getActivity().RESULT_OK) {
                            userJob = (UserJob) intent.getSerializableExtra("userJob");
                            int pos = intent.getIntExtra("position", 0);
                            userJobs.remove(pos);
                            userJobs.add(pos, userJob);
                            userJobAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    @Override
    public void onSelectedField(int i) {
        Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();
        int pos = caredFields.indexOf(fields.get(i));
        if (pos < 0) {
            caredFields.add(fields.get(i));
        }
    }

    @Override
    public void onUnSelectedField(int i) {
        Toast.makeText(getContext(), "UnSelected", Toast.LENGTH_SHORT).show();
        caredFields.remove(fields.get(i));
    }
}

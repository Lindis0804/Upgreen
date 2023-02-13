package com.ldh.upgreen.Sign;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPickInvestor extends Fragment implements FieldClickListener {
    private ImageButton ibAdd;
    private RecyclerView rvJobs, rvCaredFields;
    private TextView tvBirthday, tvLinkedInLink;
    private EditText etTakingPartInSomeProjects;
    private ImageView ivLinkedInLink;
    private FieldAdapter fieldAdapter;
    private Gson gson = new Gson();
    private ArrayList<Field> fields = new ArrayList<>();
    //private String token = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("token","");
    private String token = Default.token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pick_investor, container, false);
        findId(v);
        getAllFields();
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBirthday();
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
                                    FragmentPickInvestor.this::onFieldClick
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

    @Override
    public void onFieldClick(int i) {

    }
}

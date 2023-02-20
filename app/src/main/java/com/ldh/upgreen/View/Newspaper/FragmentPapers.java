package com.ldh.upgreen.View.Newspaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.ldh.upgreen.Model.*;
import com.ldh.upgreen.Utils.Default;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPapers extends Fragment implements PapersClickListener {
    private ViewPager vpHotPapers, vpPapers;
    private CircleIndicator ciHotPapers;
    private TabLayout tlPapers;
    private SlideHotPapersAdapter slideHotPapersAdapter;
    private ArrayList<Paper> hotPapers = new ArrayList<>();
    private Timer timer;
    private PaperViewPagerAdapter paperViewPagerAdapter;
    private String token = Default.token;
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_papers, container, false);
        token = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("token", "");
        findId(v);
        paperViewPagerAdapter = new PaperViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpPapers.setAdapter(paperViewPagerAdapter);
        tlPapers.setupWithViewPager(vpPapers);
        getHotPapers();
        autoSlide();
        return v;
    }

    public void findId(View v) {
        vpHotPapers = v.findViewById(R.id.vpHotPapers);
        vpPapers = v.findViewById(R.id.vpPapers);
        ciHotPapers = v.findViewById(R.id.ciHotPapers);
        tlPapers = v.findViewById(R.id.tlPapers);
    }

    public void getHotPapers() {
        ApiService.apiService.getHotPapers(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    DataResponse res = response.body();
                    hotPapers = res.getData().getPapers();
                    slideHotPapersAdapter = new SlideHotPapersAdapter(getContext(), hotPapers, FragmentPapers.this);
                    vpHotPapers.setAdapter(slideHotPapersAdapter);
                    ciHotPapers.setViewPager(vpHotPapers);
                    slideHotPapersAdapter.registerDataSetObserver(ciHotPapers.getDataSetObserver());
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
                Toast.makeText(getContext(), "Get hot papers fail", Toast.LENGTH_SHORT).show();
                Log.v("TAG", t.getMessage());
            }
        });
    }

    public void autoSlide() {
        if (hotPapers == null || hotPapers.isEmpty() || vpHotPapers == null) return;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int cur = vpHotPapers.getCurrentItem();
                        int totalItem = hotPapers.size() - 1;
                        if (cur < totalItem) {
                            cur++;
                            vpHotPapers.setCurrentItem(cur);
                        } else {
                            vpHotPapers.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onPapersClick(int i) {
        Paper paper = hotPapers.get(i);
        Intent intent = new Intent(getContext(),ActivityPaper.class);
        intent.putExtra("paperId",paper.get_id());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}

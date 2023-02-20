package com.ldh.upgreen.View.Newspaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Paper;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStartUp extends Fragment implements PapersClickListener {
    private RecyclerView rvPapers;
    private PaperAdapter paperAdapter;
    private ArrayList<Paper> papers = new ArrayList<>();
    private String token = Default.token;
    private Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tl_paper, container, false);
        rvPapers = v.findViewById(R.id.rvPapers);
        token = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("token","");
        getPapers();
        return v;
    }

    @Override
    public void onPapersClick(int i) {
       Paper paper = papers.get(i);
        Intent intent = new Intent(getContext(),ActivityPaper.class);
        intent.putExtra("paperId",paper.get_id());
        startActivity(intent);
    }
    public void getPapers(){
        ApiService.apiService.getStartUpPapers(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.code()==200){
                    DataResponse res = response.body();
                    papers = res.getData().getPapers();
                    paperAdapter = new PaperAdapter(getContext(), papers, FragmentStartUp.this);
                    Utils.setRecyclerView(rvPapers, paperAdapter, new GridLayoutManager(
                            getContext(),
                            2
                    ));
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
                Log.v("TAG",t.getMessage());
            }
        });
    }
}

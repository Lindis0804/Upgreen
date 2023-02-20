package com.ldh.upgreen.View.Newspaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldh.upgreen.API.ApiService;
import com.ldh.upgreen.Model.Comment;
import com.ldh.upgreen.Model.PaperBody;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.R;
import com.ldh.upgreen.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPaper extends AppCompatActivity {
    private RecyclerView rvPaperBody;
    private TextView tvPaperTitle, tvPaperTime;
    private ArrayList<PaperBody> paperBodies = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private String paperId;
    private String token;
    private PaperBodyAdapter paperBodyAdapter;
    private Gson gson = new Gson();
    private CommentAdapter commentAdapter;
    private LinearLayout llComment, llComments;
    private RecyclerView rvComments;
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://" + Utils.dhnn + ":3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        findId();
        paperId = getIntent().getStringExtra("paperId");
        token = getSharedPreferences("UserInfo", MODE_PRIVATE).getString("token", "");
        ApiService.apiService.getPaperById(token, paperId).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.code() == 200) {
                    DataResponse res = response.body();
                    tvPaperTitle.setText(res.getData().getPaper().getTitle());
                    tvPaperTime.setText(res.getData().getPaper().getCreatedAt());
                    paperBodies = res.getData().getPaper().getBody();
                    paperBodyAdapter = new PaperBodyAdapter(getApplicationContext(), paperBodies);
                    Utils.setRecyclerView(rvPaperBody, paperBodyAdapter, new LinearLayoutManager(
                            getApplicationContext(),
                            RecyclerView.VERTICAL,
                            false
                    ));
                } else {
                    try {
                        DataResponse res = gson.fromJson(response.errorBody().string(), DataResponse.class);
                        Toast.makeText(ActivityPaper.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(ActivityPaper.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("TAG", t.getMessage());
            }
        });
        llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llComments.getVisibility() == View.VISIBLE) {
                    llComments.setVisibility(View.INVISIBLE);
                    mSocket.disconnect();
                } else {
                    mSocket.connect();
                    mSocket.emit("connectServer", paperId);
                    mSocket.on("paperComments", onPaperMessage);
                }
            }
        });
    }

    public void findId() {
        rvPaperBody = findViewById(R.id.rvPaperBody);
        tvPaperTitle = findViewById(R.id.tvPaperTitle);
        tvPaperTime = findViewById(R.id.tvPaperTime);
        llComment = findViewById(R.id.llComment);
        llComments = findViewById(R.id.llComments);
        rvComments = findViewById(R.id.rvComments);
    }

    private Emitter.Listener onPaperMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        comments = gson.fromJson(String.valueOf(data.getJSONObject("data").getJSONArray("comments")), (Type) Comment[].class);
                        commentAdapter = new CommentAdapter(getApplicationContext(), comments);
                        Utils.setRecyclerView(rvComments, commentAdapter, new LinearLayoutManager(
                                getApplicationContext(),
                                RecyclerView.VERTICAL,
                                false
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}
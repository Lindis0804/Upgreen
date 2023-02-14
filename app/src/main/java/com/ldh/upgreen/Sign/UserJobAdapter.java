package com.ldh.upgreen.Sign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.upgreen.Model.UserJob;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class UserJobAdapter extends RecyclerView.Adapter<UserJobAdapter.ViewHolder> {
    private ArrayList<UserJob> userJobs = new ArrayList<>();
    private JobClickListener jobClickListener;
    private ImageButton ibFix;
    public UserJobAdapter(ArrayList<UserJob> userJobs, JobClickListener jobClickListener) {
        this.userJobs = userJobs;
        this.jobClickListener = jobClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_job_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          UserJob userJob = userJobs.get(position);
          holder.tvJob.setText(userJob.getJob().getTitle());
          holder.tvWorkPlace.setText(userJob.getWorkplace().getPlaceName());
    }

    @Override
    public int getItemCount() {
        return userJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView tvJob,tvWorkPlace;
       private JobClickListener _jobClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJob = itemView.findViewById(R.id.tvJob);
            tvWorkPlace = itemView.findViewById(R.id.tvWorkPlace);
            ibFix = itemView.findViewById(R.id.ibFix);
            _jobClickListener = jobClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_jobClickListener!=null){
                        _jobClickListener.onJobClick(getAdapterPosition());
                    }
                }
            });
            ibFix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _jobClickListener.onFixJob(getAdapterPosition());
                }
            });
        }
    }
}

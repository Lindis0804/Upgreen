package com.ldh.upgreen.View.Project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldh.upgreen.Model.Project;
import com.ldh.upgreen.R;
import com.ldh.upgreen.View.Newspaper.PapersClickListener;

import java.util.ArrayList;

public class ProjectAdapter1 extends RecyclerView.Adapter<ProjectAdapter1.ViewHolder> {
    private ArrayList<Project> projects;
    private ProjectClickListener click;
    private Context context;

    public ProjectAdapter1(Context context, ArrayList<Project> projects, ProjectClickListener click) {
        this.projects = projects;
        this.click = click;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = projects.get(position);
        if (project != null) {
            holder.tvTitle.setText(project.getTitle() + "");
            Glide.with(context).load(project.getPic()).into(holder.ivPic);
            holder.tvView.setText(project.getViewers().size() + "");
            holder.tvTitle.setText(project.getTitle());
            holder.tvIntro.setText(project.getIntroduction());
            holder.tvLike.setText(project.getLikers().size() + "");
            holder.tvShare.setText(project.getSharers().size() + "");
            holder.tvComment.setText(project.getComments().size() + "");
            holder.tvEI.setText(project.getEI() + "");
            holder.tvERI.setText(project.getERI() + "");
            holder.tvValidationScore.setText(project.getValidationScore()+"");
            int s = project.getFields().size();
            if (s < 1) {
                holder.tvField1.setVisibility(View.INVISIBLE);
                holder.tvField2.setVisibility(View.INVISIBLE);
            } else if (s < 2) {
                holder.tvField2.setVisibility(View.INVISIBLE);
                holder.tvField1.setText(project.getFields().get(0).getTitle());
            }
            else{
                holder.tvField1.setText(project.getFields().get(0).getTitle());
                holder.tvField2.setText(project.getFields().get(1).getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvView, tvComment, tvLike, tvShare, tvTitle, tvIntro, tvEI, tvERI, tvField1, tvField2,tvValidationScore;
        private ProjectClickListener _click;

        public ViewHolder(@NonNull View v) {
            super(v);
            ivPic = v.findViewById(R.id.ivPic);
            tvView = v.findViewById(R.id.tvView);
            tvComment = v.findViewById(R.id.tvComment);
            tvLike = v.findViewById(R.id.tvLike);
            tvShare = v.findViewById(R.id.tvShare);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvIntro = v.findViewById(R.id.tvIntro);
            tvEI = v.findViewById(R.id.tvEI);
            tvERI = v.findViewById(R.id.tvERI);
            tvValidationScore = v.findViewById(R.id.tvValidationScore);
            tvField1 = v.findViewById(R.id.tvField1);
            tvField2 = v.findViewById(R.id.tvField2);
            _click = click;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _click.onProjectClick(getAdapterPosition());
                }
            });
        }
    }
}

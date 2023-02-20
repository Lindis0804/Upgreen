package com.ldh.upgreen.View.Newspaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldh.upgreen.Model.Paper;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.ViewHolder> {
    private ArrayList<Paper> papers;
    private PapersClickListener click;
    private Context context;
    public PaperAdapter(Context context,ArrayList<Paper> papers, PapersClickListener click) {
        this.papers = papers;
        this.click = click;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Paper paper = papers.get(position);
        if (paper!=null){
            holder.tvTitle.setText(paper.getTitle()+"");
            Glide.with(context).load(paper.getPic()).into(holder.ivPic);
            holder.tvView.setText(paper.getNumOfViewer()+"");
            holder.tvLike.setText(paper.getNumOfLiker()+"");
            holder.tvShare.setText(paper.getNumOfSharer()+"");
            holder.tvComment.setText(paper.getNumOfComment()+"");
        }
    }

    @Override
    public int getItemCount() {
        return papers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPic;
        private TextView tvView,tvComment,tvLike,tvShare,tvTitle;
        private PapersClickListener _click;
        public ViewHolder(@NonNull View v) {
            super(v);
            ivPic = v.findViewById(R.id.ivPic);
            tvView = v.findViewById(R.id.tvView);
            tvComment = v.findViewById(R.id.tvComment);
            tvLike = v.findViewById(R.id.tvLike);
            tvShare = v.findViewById(R.id.tvShare);
            tvTitle = v.findViewById(R.id.tvTitle);
            _click = click;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _click.onPapersClick(getAdapterPosition());
                }
            });
        }
    }
}

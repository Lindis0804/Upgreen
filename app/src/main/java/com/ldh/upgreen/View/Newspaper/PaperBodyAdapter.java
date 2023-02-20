package com.ldh.upgreen.View.Newspaper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldh.upgreen.Model.PaperBody;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class PaperBodyAdapter extends RecyclerView.Adapter {
    private ArrayList<PaperBody> paperBodies;
    private Context context;
    public PaperBodyAdapter(Context context,ArrayList<PaperBody> paperBodies) {
        this.paperBodies = paperBodies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_body_item,parent,false));
        }
        return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.string_body_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       PaperBody paperBody = paperBodies.get(position);
       if (paperBody!=null){
           String label = paperBody.getLabel();
           if (label.equals("pic")){
               ViewHolder2 v = (ViewHolder2) holder;
               Glide.with(context).load(paperBody.getPic().getLink()).into(v.ivBodyPic);
               v.tvBodyDes.setText(paperBody.getPic().getDes());
           }
           else{
               ViewHolder1 v = (ViewHolder1) holder;
               switch (label){
                   case "h1":
                       v.tvBodyString.setText(paperBody.getH1());
                       v.tvBodyString.setTypeface(null, Typeface.BOLD);
                       v.tvBodyString.setTextSize(15);
                       break;
                   case "h2":
                       v.tvBodyString.setText(paperBody.getH2());
                       v.tvBodyString.setTypeface(null, Typeface.BOLD);
                       v.tvBodyString.setTextSize(15);
                       break;
                   case "p":
                       v.tvBodyString.setText(paperBody.getP());
                       v.tvBodyString.setTextSize(12);
                       break;
                   case "intro":
                       v.tvBodyString.setText(paperBody.getIntro());
                       v.tvBodyString.setTextSize(12);
                       v.tvBodyString.setTypeface(null, Typeface.BOLD);
                       break;
               }
           }
       }
    }

    @Override
    public int getItemViewType(int position) {
        PaperBody paperBody = paperBodies.get(position);
        if (paperBody.getLabel().equals("pic")) return 1;
        return 0 ;
    }

    @Override
    public int getItemCount() {
        return paperBodies.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        private TextView tvBodyString;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvBodyString = itemView.findViewById(R.id.tvBodyString);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{
       private ImageView ivBodyPic;
       private TextView tvBodyDes;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ivBodyPic = itemView.findViewById(R.id.ivBodyPic);
            tvBodyDes = itemView.findViewById(R.id.tvBodyDes);
        }
    }
}

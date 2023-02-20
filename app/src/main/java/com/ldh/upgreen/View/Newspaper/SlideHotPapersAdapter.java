package com.ldh.upgreen.View.Newspaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ldh.upgreen.Model.Paper;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class SlideHotPapersAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Paper>papers;
    private PapersClickListener papersClickListener;

    public SlideHotPapersAdapter(Context context, ArrayList<Paper> papers, PapersClickListener papersClickListener) {
        this.context = context;
        this.papers = papers;
        this.papersClickListener = papersClickListener;
    }

    @Override
    public int getCount() {
        if (papers!=null) return papers.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.vp_paper_item,container,false);
        ImageView ivPic;
        TextView tvTitle;
        Paper paper = papers.get(position);
        ivPic = v.findViewById(R.id.ivPic);
        tvTitle = v.findViewById(R.id.tvTitle);
        if (paper!=null){
            Glide.with(context).load(paper.getPic()).into(ivPic);
//            ivPic.setImageResource(R.drawable.house);
            tvTitle.setText(paper.getTitle());
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                papersClickListener.onPapersClick(position);
            }
        });
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.ldh.upgreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NatureAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Nature> natures;

    public NatureAdapter(Context context, ArrayList<Nature> natures) {
        this.context = context;
        this.natures = natures;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.page_item,container,false);
        ImageView ivNature = v.findViewById(R.id.ivNature);
        Nature nature = natures.get(position);
        if (nature != null){
            Glide.with(context).load(nature.getResourceId()).into(ivNature);
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (natures!=null){
            return natures.size();
        }
      return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

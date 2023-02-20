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
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import retrofit2.Callback;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private ArrayList<User> users;
    private Context context;

    public MemberAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        if (user!=null){
            Glide.with(context).load(user.getAvatar()).into(holder.ivAvatar);
            holder.tvName.setText(user.getName());
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivAvatar;
        private TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}

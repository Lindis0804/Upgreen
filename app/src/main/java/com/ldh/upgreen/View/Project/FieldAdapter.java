package com.ldh.upgreen.View.Project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {
    private ArrayList<Field> fields;

    public FieldAdapter(ArrayList<Field> fields) {
        this.fields = fields;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.field_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Field field = fields.get(position);
         if (field!=null){
             if (!field.getTitle().equals(""))
             holder.tvField.setText(field.getTitle());
         }
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvField;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvField = itemView.findViewById(R.id.tvField);
        }
    }
}

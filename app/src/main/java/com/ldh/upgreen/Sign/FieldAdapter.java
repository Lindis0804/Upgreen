package com.ldh.upgreen.Sign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.R;

import java.util.ArrayList;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {
    private ArrayList<Field> fields = new ArrayList<>();
    private FieldClickListener fieldClickListener;

    public FieldAdapter(ArrayList<Field> fields, FieldClickListener fieldClickListener) {
        this.fields = fields;
        this.fieldClickListener = fieldClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pick_field_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Field field = fields.get(position);
        holder.cbField.setText(field.getTitle());
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbField;
        private FieldClickListener _fieldClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cbField = itemView.findViewById(R.id.cbField);
            _fieldClickListener = fieldClickListener;
        }
    }
}

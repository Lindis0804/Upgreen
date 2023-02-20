package com.ldh.upgreen.Sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ldh.upgreen.R;

public class BottomSheetEditUserJob extends BottomSheetDialogFragment {
    private LinearLayout llEditUserJob, llDeleteUserJob;
    private EditUserJobClickListener editUserJobClickListener;
    private int position;

    public BottomSheetEditUserJob(int position, EditUserJobClickListener editUserJobClickListener) {
        this.editUserJobClickListener = editUserJobClickListener;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bs_edit_user_job, container, false);
        findId(v);
        llEditUserJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserJobClickListener.onEditUserJob(position);
            }
        });
        llDeleteUserJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserJobClickListener.onDeleteUserJob(position);
            }
        });
        return v;
    }

    public void findId(View v) {
        llEditUserJob = v.findViewById(R.id.llEditUserJob);
        llDeleteUserJob = v.findViewById(R.id.llDeleteUserJob);
    }
}

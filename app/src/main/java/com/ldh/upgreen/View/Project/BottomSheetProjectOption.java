package com.ldh.upgreen.View.Project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ldh.upgreen.R;

public class BottomSheetProjectOption extends BottomSheetDialogFragment {
    private LinearLayout llValidate,llFix,llDelete,llProjectDetail;
    private ProjectClickListener click;
    private int pos;

    public BottomSheetProjectOption(ProjectClickListener click, int pos) {
        this.click = click;
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bs_project_option,container,false);
        findId(v);
        llValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onValidateProject(pos);
            }
        });
        llFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onFixProject(pos);
            }
        });
        llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onDeleteProject(pos);
            }
        });
        llProjectDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onProjectDetail(pos);
            }
        });
        return v;
    }
    public void findId(View v){
        llValidate = v.findViewById(R.id.llValidate);
        llFix  = v.findViewById(R.id.llFix);
        llDelete = v.findViewById(R.id.llDelete);
        llProjectDetail = v.findViewById(R.id.llProjectDetail);
    }
}

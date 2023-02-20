package com.ldh.upgreen.View.Project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ldh.upgreen.R;

public class FragmentProject extends Fragment {
    private TabLayout tlProjects;
    private ViewPager vpProjects;
    private VpProjectAdapter vpProjectAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_project,container,false);
        findId(v);
        vpProjectAdapter = new VpProjectAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpProjects.setAdapter(vpProjectAdapter);
        tlProjects.setupWithViewPager(vpProjects);
        return v;
    }
    public void findId(View v){
      tlProjects = v.findViewById(R.id.tlProjects);
      vpProjects = v.findViewById(R.id.vpProjects);
    }
}

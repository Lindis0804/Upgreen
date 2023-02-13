package com.ldh.upgreen.Sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ldh.upgreen.R;

public class PickRole extends AppCompatActivity {
    private TabLayout tlPickRole;
    private ViewPager vpPickRole;
    private PickRoleViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_role);
        findId();
        vpPickRole.setAdapter(adapter);
        tlPickRole.setupWithViewPager(vpPickRole);
    }
    public void findId(){
        tlPickRole = findViewById(R.id.tlPickRole);
        vpPickRole = findViewById(R.id.vpPickRole);
        adapter = new PickRoleViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
}
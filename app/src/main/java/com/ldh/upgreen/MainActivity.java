package com.ldh.upgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.CheckBox;

import com.google.android.material.tabs.TabLayout;
import com.ldh.upgreen.Sign.FieldAdapter;
import com.ldh.upgreen.Sign.FieldClickListener;
import com.ldh.upgreen.Sign.SignViewPagerAdapter;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

public class MainActivity extends AppCompatActivity implements FieldClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SignViewPagerAdapter adapter;
    private CheckBox cbField;
    private RecyclerView rvFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        cbField.setText("Hello flutter");
        Utils.setRecyclerView(
                rvFields,
                new FieldAdapter(
                        Default.fields,
                        this
                ),
                new GridLayoutManager(
                        this,
                        2
                )
        );
    }

    public void findId() {
        rvFields = findViewById(R.id.rvFields);
        cbField = findViewById(R.id.cbField);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new SignViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onSelectedField(int i) {

    }

    @Override
    public void onUnSelectedField(int i) {

    }
}
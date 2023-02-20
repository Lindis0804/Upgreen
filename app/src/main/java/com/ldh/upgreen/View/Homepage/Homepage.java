package com.ldh.upgreen.View.Homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ldh.upgreen.View.Newspaper.*;
import com.ldh.upgreen.View.Connect.*;
import com.ldh.upgreen.View.Project.*;
import com.ldh.upgreen.View.Forum.*;
import com.ldh.upgreen.View.Profile.*;
import com.ldh.upgreen.R;

public class Homepage extends AppCompatActivity {
    private FrameLayout fl_page;
    private BottomNavigationView bnv_page_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ldh.upgreen.R.layout.activity_homepage);
        findId();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_page, new FragmentHomepage()).commit();
        bnv_page_option.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_homepage:
                        fragmentTransaction.replace(R.id.fl_page, new FragmentHomepage());
                        fragmentTransaction.commit();
                        break;
                    case R.id.nav_news:
                        fragmentTransaction.replace(R.id.fl_page, new FragmentPapers()).commit();
                        break;
                    case R.id.nav_connect:
                        fragmentTransaction.replace(R.id.fl_page, new FragmentConnect()).commit();
                        break;
                    case R.id.nav_project:
                        fragmentTransaction.replace(R.id.fl_page, new FragmentProject()).commit();
                        break;
                    case R.id.nav_forum:
                        fragmentTransaction.replace(R.id.fl_page, new FragmentForum()).commit();
                        break;
                }
                return true;
            }
        });
    }

    public void findId() {
        fl_page = findViewById(R.id.fl_page);
        bnv_page_option = findViewById(R.id.bnv_page_option);
    }
}
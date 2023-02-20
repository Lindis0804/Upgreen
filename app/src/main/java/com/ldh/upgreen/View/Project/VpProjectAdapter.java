package com.ldh.upgreen.View.Project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ldh.upgreen.Sign.FragmentSignIn;

public class VpProjectAdapter extends FragmentStatePagerAdapter {
    public VpProjectAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentStartup();
            case 1:
                return new FragmentStatistic();
            default:
                return new FragmentStartup();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title="Dự án";
                break;
            case 1:
                title="Thống kê";
                break;
        }
        return title;
    }
}

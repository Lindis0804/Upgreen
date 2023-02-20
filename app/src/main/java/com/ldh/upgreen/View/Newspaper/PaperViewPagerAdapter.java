package com.ldh.upgreen.View.Newspaper;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PaperViewPagerAdapter extends FragmentStatePagerAdapter {
    public PaperViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentStartUp();
            case 1:
                return new FragmentContest();
            default:
                return new FragmentStartUp();
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
                title="Mới nhất";
                break;
            case 1:
                title="Cuộc thi";
                break;
            default:
                title="Mới nhất";
                break;
        }
        return title;
    }
}

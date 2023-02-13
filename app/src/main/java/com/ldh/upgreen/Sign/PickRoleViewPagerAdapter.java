package com.ldh.upgreen.Sign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PickRoleViewPagerAdapter extends FragmentStatePagerAdapter {
    public PickRoleViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentPickStartup();
            case 1:
                return new FragmentPickInvestor();
            default:
                return new FragmentPickStartup();
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
        switch (position) {
            case 0:
                title = "Start up";
                break;
            case 1:
                title = "Nhà đầu tư";
                break;
            default:
                title = "Start up";
                break;
        }
        return title;
    }
}

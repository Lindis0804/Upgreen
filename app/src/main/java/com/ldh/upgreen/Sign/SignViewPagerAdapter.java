package com.ldh.upgreen.Sign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SignViewPagerAdapter extends FragmentStatePagerAdapter {

    public SignViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentSignIn();
            case 1:
                return new FragmentSignUp();
            default:
                return new FragmentSignIn();
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
                title="Đăng nhập";
                break;
            case 1:
                title="Đăng kí";
                break;
            default:
                title = "Đăng nhập";
                break;
        }
        return title;
    }
}

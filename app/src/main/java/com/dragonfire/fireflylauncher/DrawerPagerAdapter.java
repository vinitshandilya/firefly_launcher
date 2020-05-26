package com.dragonfire.fireflylauncher;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DrawerPagerAdapter extends FragmentStateAdapter {

    private ArrayList<String> drawerColors;

    DrawerPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<String> drawerColors) {
        super(fragmentActivity);
        this.drawerColors = drawerColors;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return DrawerFragment.newInstance(drawerColors.get(position));
    }

    @Override
    public int getItemCount() {
        return drawerColors.size();
    }

}

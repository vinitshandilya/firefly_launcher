package com.dragonfire.fireflylauncher;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomePagerAdapter extends FragmentStateAdapter {

    private ArrayList<String> colorArgs;

    HomePagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<String> colorArgs) {
        super(fragmentActivity);
        this.colorArgs = colorArgs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return HomeFragment.newInstance(colorArgs.get(position));
    }

    @Override
    public int getItemCount() {
        return colorArgs.size();
    }

}

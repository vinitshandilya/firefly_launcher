package com.dragonfire.fireflylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 drawerPager;
    ArrayList<String> drawerColorArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerColorArgs = new ArrayList<>();
        drawerColorArgs.add("#00FFFFFF");
        drawerColorArgs.add("#FFFFFF");

        drawerPager = findViewById(R.id.drawer_pager);
        drawerPager.setOffscreenPageLimit(5);
        DrawerPagerAdapter drawerPagerAdapter = new DrawerPagerAdapter(this, drawerColorArgs);

        drawerPager.setAdapter(drawerPagerAdapter);
        DrawerScrollListener drawerScrollListener = new DrawerScrollListener(MainActivity.this);
        drawerPager.registerOnPageChangeCallback(drawerScrollListener);

    }

    @Override
    public void onBackPressed() {
        if (drawerPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            drawerPager.setCurrentItem(drawerPager.getCurrentItem() - 1);
        }
    }
}

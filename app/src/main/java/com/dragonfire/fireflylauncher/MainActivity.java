package com.dragonfire.fireflylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DrawerStateListener {

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
        drawerScrollListener.setDrawerStateListener(this);
        drawerPager.registerOnPageChangeCallback(drawerScrollListener);

    }

    @Override
    public void onBackPressed() {
        if (drawerPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            drawerPager.setCurrentItem(drawerPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onDrawerExpanded(View drawerView) {
        Log.d("COOK", "Drawer expanded");

    }

    @Override
    public void onDrawerCollapsed() {
        Log.d("COOK", "Drawer collapsed");

    }
}

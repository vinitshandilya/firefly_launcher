package com.dragonfire.fireflylauncher;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class DrawerScrollListener extends ViewPager2.OnPageChangeCallback {
    private MainActivity activity;
    private View drawerView;
    private View homeview;
    private DrawerStateListener drawerStateListener;

    DrawerScrollListener(MainActivity activity) {
        super();
        this.activity = activity;
    }

    public void setDrawerStateListener(DrawerStateListener drawerStateListener) {
        this.drawerStateListener = drawerStateListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

        double alpha = 238 * Math.abs(positionOffset);

        if(positionOffset!=0.0f) {
            if(homeview!=null) {
                homeview.setBackgroundColor(Color.argb((int)alpha, 255, 255, 255));
            }
            if(drawerView!=null) {
                drawerView.setBackgroundColor(Color.argb((int)alpha, 255, 255, 255));
            }
            else {
                Fragment drawerfragment = activity.getSupportFragmentManager().getFragments().get(position+1);
                drawerfragment.getView().setBackgroundColor(Color.argb((int)alpha, 255, 255, 255));
            }

        }

        if(positionOffset > 0.96f || (positionOffset == 0.0f && position == 1)) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // DARK STATUS ICONS.
        }
        else {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); // LIGHT STATUS ICONS.
        }
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        Fragment fragment = activity.getSupportFragmentManager().getFragments().get(position);
        if(position ==0) { // Homescreen, add horizontal pages
            homeview = fragment.getView();

            if(drawerStateListener != null) {
                drawerStateListener.onDrawerCollapsed();
            }

            assert homeview != null;
            ViewPager2 homepager = homeview.findViewById(R.id.home_pager);
            ArrayList<String> sidepaneColorArgs = new ArrayList<>();
            sidepaneColorArgs.add("#00000000");
            sidepaneColorArgs.add("#00000000");
            HomePagerAdapter homePagerAdapter = new HomePagerAdapter(activity, sidepaneColorArgs);
            homepager.setAdapter(homePagerAdapter);
        }
        if(position ==1) { // drawer expanded
            drawerView = fragment.getView();
            if(drawerStateListener != null) {
                drawerStateListener.onDrawerExpanded(drawerView);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
    }
}

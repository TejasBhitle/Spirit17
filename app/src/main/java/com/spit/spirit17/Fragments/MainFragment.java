package com.spit.spirit17.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spit.spirit17.HelperClasses.CustomPagerAdapter;
import com.spit.spirit17.HelperClasses.CustomViewPager;
import com.spit.spirit17.R;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Tejas on 14/01/2017.
 */

public class MainFragment extends Fragment {

    CustomPagerAdapter mCustomPagerAdapter;
    CustomViewPager mViewPager;
    private static int NUM_PAGES = 3;


    public MainFragment (){}

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mCustomPagerAdapter = new CustomPagerAdapter(getContext());
        mViewPager = (CustomViewPager)view.findViewById(R.id.viewpager_main);
        CircleIndicator indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        mViewPager.setAdapter(mCustomPagerAdapter);
        indicator.setViewPager(mViewPager);

        final Handler h = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            public void run() {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem()+1)%NUM_PAGES, true);
                h.postDelayed(this, 5000);
            }
        };
        h.postDelayed(r, 5000);
        return view;
    }

}

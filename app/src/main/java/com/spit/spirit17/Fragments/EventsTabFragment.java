package com.spit.spirit17.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spit.spirit17.R;

/**
 * Created by Tejas on 04/01/2017.
 */

public class EventsTabFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    String type;

    public EventsTabFragment(){}

    public static EventsTabFragment newInstance(String type){
        EventsTabFragment eventsTabFragment = new EventsTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        eventsTabFragment.setArguments(bundle);
        return eventsTabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            type = bundle.getString("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_tab,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.fragment_viewPager_tabLayout);
        tabLayout =(TabLayout)view.findViewById(R.id.main_fragment_tabLayout);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        //viewPager.setOffscreenPageLimit(0);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;
    }


    class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            EventListFragment indoorEventPage = EventListFragment.newInstance(getString(R.string.indoor),type);
            EventListFragment outdoorEventPage =  EventListFragment.newInstance(getString(R.string.outdoor),type);
            switch (position) {
                case 0:return indoorEventPage;
                case 1:return outdoorEventPage;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:return getResources().getString(R.string.indoor);
                case 1:return getResources().getString(R.string.outdoor);
            }
            return null;
        }
    }

}

package com.spit.spirit17.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.spit.spirit17.Activities.EventDetails;
import com.spit.spirit17.HelperClasses.Event;
import com.spit.spirit17.HelperClasses.EventListAdapter;
import com.spit.spirit17.HelperClasses.RecyclerItemCLickListener;
import com.spit.spirit17.HelperClasses.SpiritContentProvider;
import com.spit.spirit17.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 04/01/2017.
 */

public class EventListFragment extends Fragment {

    RecyclerView mRecyclerView;
    SpiritContentProvider ContentProvider;
    private String category ="",type="";
    ArrayList<Event> eventList;


    public static EventListFragment newInstance(String category,String type){
        EventListFragment fragment = new EventListFragment();
        Log.i("ViewPagerFragment ",category);
        Bundle bundle = new Bundle();
        bundle.putString("data",category);
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            category = bundle.getString("data");
            type = bundle.getString("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_layout,container,false);
        final SpiritContentProvider.SpiritDBConnectionHelper dbConnectionHelper;
        ContentProvider=new SpiritContentProvider();
        dbConnectionHelper = new SpiritContentProvider().new SpiritDBConnectionHelper(getContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        eventList = dbConnectionHelper.getEventListByType(type,category);
        mRecyclerView.setAdapter(new EventListAdapter(getContext(),eventList));
        mRecyclerView.scrollToPosition(0);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemCLickListener(getContext(), mRecyclerView ,new RecyclerItemCLickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Event event=eventList.get(position);
                        Intent i = new Intent(getContext(), EventDetails.class);

                        i.putExtra("image",event.getImage());
                        i.putExtra("name", event.getName());
                        i.putExtra("type",event.getType());
                        i.putExtra("venue", event.getVenue());
                        i.putExtra("time", event.getTime());
                        i.putExtra("registration", event.getRegistration());
                        i.putExtra("prizes", event.getPrizes());
                        i.putExtra("contact1name", event.getContact1_name());
                        i.putExtra("contact1no", event.getContact1_no());
                        i.putExtra("contact2name", event.getContact2_name());
                        i.putExtra("contact2no", event.getContact2_no());
                        i.putExtra("favorite",event.getFavourite());
                        i.putExtra("reminder", event.getReminder());
                        i.putExtra("color",event.getColor());

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            ImageView poster = (ImageView)view.findViewById(R.id.thumbnail);
                            poster.setTransitionName("poster");
                            Pair pair = new Pair<>(poster, ViewCompat.getTransitionName(poster));

                            ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),pair);
                            ActivityCompat.startActivity(getActivity(),i,optionsCompat.toBundle());
                        }
                        else
                            getContext().startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {}
                })
        );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment attached",category);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment detached",category);
    }
}
package com.spit.spirit17.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spit.spirit17.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Tejas on 21/01/2017.
 */

public class AchievementsFragment extends Fragment {

    ImageView imageview1,imageview2,imageview3,imageview4,imageview5,imageview6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievements,container,false);
        imageview1 = (ImageView) view.findViewById(R.id.imageview1);
        imageview2 = (ImageView) view.findViewById(R.id.imageview2);
        imageview3 = (ImageView) view.findViewById(R.id.imageview3);
        imageview4 = (ImageView) view.findViewById(R.id.imageview4);
        imageview5 = (ImageView) view.findViewById(R.id.imageview5);
        imageview6 = (ImageView) view.findViewById(R.id.imageview6);

        Picasso.with(getContext()).load(R.drawable.img_football).into(imageview1);
        Picasso.with(getContext()).load(R.drawable.img_cricket).into(imageview2);
        Picasso.with(getContext()).load(R.drawable.img_chess).into(imageview3);
        Picasso.with(getContext()).load(R.drawable.img_throwball).into(imageview4);
        Picasso.with(getContext()).load(R.drawable.img_volley).into(imageview5);
        Picasso.with(getContext()).load(R.drawable.img_dsc).into(imageview6);
        return view;
    }
}

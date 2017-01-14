/*
 * *
 *  * This file is part of Matrix2017
 *  * Created for the annual technical festival of Sardar Patel Institute of Technology
 *  *
 *  * The original contributors of the software include:
 *  * - Adnan Ansari (psyclone20)
 *  * - Tejas Bhitle (TejasBhitle)
 *  * - Mithil Gotarne (mithilgotarne)
 *  * - Rohit Nahata (rohitnahata)
 *  * - Akshay Shah (akshah1997)
 *  *
 *  * Matrix2017 is free software: you can redistribute it and/or modify
 *  * it under the terms of the MIT License as published by the Massachusetts Institute of Technology
*/

package com.spit.spirit17.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spit.spirit17.R;
import com.squareup.picasso.Picasso;


public class DevelopersFragment extends Fragment {

    TextView email1,email2,email3;
    Button g1,g2,g3,l1,l2,l3;
    ImageView image1,image2,image3;

    public DevelopersFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_developers,container,false);

        email1 =(TextView)view.findViewById(R.id.emailId_tejas);
        email2 =(TextView)view.findViewById(R.id.emailId_shubham);
        email3 =(TextView)view.findViewById(R.id.emailId_adnan);

        g1 =(Button)view.findViewById(R.id.google_tejas);
        g2 =(Button)view.findViewById(R.id.google_shubham);
        g3 =(Button)view.findViewById(R.id.google_adnan);

        l1=(Button) view.findViewById(R.id.linkedin_tejas);
        l2=(Button) view.findViewById(R.id.linkedin_shubham);
        l3=(Button) view.findViewById(R.id.linkedin_adnan);

        image1 = (ImageView)view.findViewById(R.id.pic_tejas);
        image2 =(ImageView)view.findViewById(R.id.pic_shubham);
        image3 =(ImageView)view.findViewById(R.id.pic_adnan);


        /*Add Your Pics Here And Not In Xml*/
        Picasso.with(getActivity()).load(R.drawable.dev_tejas_bhitle).into(image1);
        Picasso.with(getActivity()).load(R.drawable.dev_shubham_mahajan).into(image2);
        Picasso.with(getActivity()).load(R.drawable.dev_shubham_mahajan).into(image3);


        View.OnClickListener linkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                switch (v.getId()){

                    /*Google+ links*/
                    case R.id.google_tejas:
                        uri= Uri.parse(getResources().getString(R.string.googleplus_tejas));
                        break;
                    case R.id.google_shubham:
                        uri= Uri.parse(getResources().getString(R.string.googleplus_shubham));
                        break;
                    case R.id.google_adnan:
                        uri= Uri.parse(getResources().getString(R.string.googleplus_adnan));
                        break;

                    /*LInkedin Links*/
                    case R.id.linkedin_tejas:
                        uri= Uri.parse(getResources().getString(R.string.linkedin_tejas));
                        break;
                    case R.id.linkedin_shubham:
                        uri= Uri.parse(getResources().getString(R.string.linkedin_shubham));
                        break;
                    case R.id.linkedin_adnan:
                        uri= Uri.parse(getResources().getString(R.string.linkedin_adnan));
                        break;
                    default:uri = Uri.parse(getResources().getString(R.string.linkedin_tejas));
                }
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                try{
                    startActivity(i);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Error Loading Link", Toast.LENGTH_SHORT).show();
                }

            }
        };

    View.OnClickListener emailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String to="";
            switch (v.getId()){
                /*Email Ids*/
                case R.id.emailId_tejas:
                    to = getResources().getString(R.string.email_tejas);
                    break;
                case R.id.emailId_shubham:
                    to = getResources().getString(R.string.email_shubham);
                    break;
                case R.id.emailId_adnan:
                    to = getResources().getString(R.string.email_adnan);
                    break;
            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:"+to));
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            try{
                startActivity(Intent.createChooser(intent,"Send Email"));
            }
            catch(Exception e){
                Toast.makeText(getActivity(),e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
            }
            }
        };
        email1.setOnClickListener(emailListener);
        email2.setOnClickListener(emailListener);
        email3.setOnClickListener(emailListener);
        g1.setOnClickListener(linkListener);
        g2.setOnClickListener(linkListener);
        g3.setOnClickListener(linkListener);
        l1.setOnClickListener(linkListener);
        l2.setOnClickListener(linkListener);
        l3.setOnClickListener(linkListener);


        return view;
    }
}

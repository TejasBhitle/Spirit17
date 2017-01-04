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

/**
 * Created by DELL on 04/01/2017.
 */

public class DevelopersFragment extends Fragment {

    TextView email1,email2;
    Button g1,g2,l1,l2;
    ImageView image1,image2;

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

        g1 =(Button)view.findViewById(R.id.google_tejas);
        g2 =(Button)view.findViewById(R.id.google_shubham);

        l1=(Button) view.findViewById(R.id.linkedin_tejas);
        l2=(Button) view.findViewById(R.id.linkedin_shubham);

        image1 = (ImageView)view.findViewById(R.id.pic_tejas);
        image2 =(ImageView)view.findViewById(R.id.pic_shubham);


        /*Add Your Pics Here And Not In Xml*/
        Picasso.with(getActivity()).load(R.drawable.dev_tejas_bhitle).into(image1);
        Picasso.with(getActivity()).load(R.drawable.dev_shubham_mahajan).into(image2);


        View.OnClickListener linkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                switch (v.getId()){

                    /*Google+ links*/
                    case R.id.google_tejas:
                        uri=Uri.parse(getResources().getString(R.string.googleplus_tejas));
                        break;
                    case R.id.google_shubham:
                        uri=Uri.parse(getResources().getString(R.string.googleplus_shubham));
                        break;

                    /*LInkedin Links*/
                    case R.id.linkedin_tejas:
                        uri=Uri.parse(getResources().getString(R.string.linkedin_tejas));
                        break;
                    case R.id.linkedin_shubham:
                        uri=Uri.parse(getResources().getString(R.string.linkedin_shubham));
                        break;
                    default:uri =Uri.parse(getResources().getString(R.string.linkedin_tejas));
                }
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                try{
                    startActivity(i);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Error Loading Link",Toast.LENGTH_SHORT).show();
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
        g1.setOnClickListener(linkListener);
        g2.setOnClickListener(linkListener);
        l1.setOnClickListener(linkListener);
        l2.setOnClickListener(linkListener);

        return view;
    }

}

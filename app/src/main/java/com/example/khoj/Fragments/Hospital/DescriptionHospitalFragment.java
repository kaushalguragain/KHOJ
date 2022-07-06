package com.example.khoj.Fragments.Hospital;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.khoj.Fragments.DetailFragment;
import com.example.khoj.Fragments.Hotel.InformationFragment;
import com.example.khoj.Fragments.MapFragment;
import com.example.khoj.Fragments.ReviewFragment;
import com.example.khoj.R;

public class DescriptionHospitalFragment extends Fragment {

    ImageView profile;
    TextView backgroundimage;
    TextView details, reviews, information, specializationview;
    String title,description,longDescription,rating,servicenumber,covidhotline,specialization,clinicAV,image;
    String Lat,longi;
    RatingBar ratingBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragmenthospital,container,false);
        title= getArguments().getString("name");
        description = getArguments().getString("slogan");
        longDescription = getArguments().getString("description");
        rating = getArguments().getString("rate");
        servicenumber = getArguments().getString("servicenumber");
        covidhotline = getArguments().getString("covidhotline");
        specialization = getArguments().getString("specialization");
        clinicAV = getArguments().getString("clinic_availability");
        image = getArguments().getString("img");
        Lat = getArguments().getString("lat");
        longi = getArguments().getString("long");
        ratingBar = view.findViewById(R.id.rating);
        ratingBar.setRating(Float.parseFloat(rating));
        profile=view.findViewById(R.id.descriptionimage);
        Glide.with(getContext())
                .load(image)
                .into(profile);
        backgroundimage=view.findViewById(R.id.background);
        details=view.findViewById(R.id.details);
        reviews=view.findViewById(R.id.reviews);
        information=view.findViewById(R.id.information);
        specializationview= view.findViewById(R.id.specializationview);
        backgroundimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new MapFragment();
                Bundle args = new Bundle();
                args.putString("lat",Lat);
                args.putString("long", longi);

                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Fragment  fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("title1",title);
        args.putString("description1", longDescription);

        fragment.setArguments(args);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();


        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new DetailFragment();
                Bundle args = new Bundle();
                args.putString("title1",title);
                args.putString("description1", longDescription);

                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();


            }
        });

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new ReviewFragment();
                Bundle args = new Bundle();
                args.putString("title2",title);
                args.putString("name","hospitals");
                args.putString("superRating",rating);
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();


            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new InformationHospitalFragment();
                Bundle args = new Bundle();

                args.putString("rate1",rating);
                args.putString("open1", servicenumber);
                args.putString("close1", covidhotline);
                args.putString("range1", clinicAV);


                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();

            }
        });


        specializationview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new SpecializationFragment();
                Bundle args = new Bundle();

                args.putString("specialization",specialization);

                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();
            }
        });


        return view;
    }



}

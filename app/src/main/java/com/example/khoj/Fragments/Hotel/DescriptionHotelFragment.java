package com.example.khoj.Fragments.Hotel;

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
import com.example.khoj.Fragments.MapFragment;
import com.example.khoj.Fragments.ReviewFragment;
import com.example.khoj.R;

public class DescriptionHotelFragment extends Fragment {
    ImageView profile;
    TextView backgroundimage;
    String Lat,longi;
    TextView details, reviews, information;
    String title,description,longDescription,rating,openingtime,closingtime,pricerange,image;
    RatingBar ratingBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment,container,false);
        title= getArguments().getString("title");
        description = getArguments().getString("description");
        longDescription = getArguments().getString("ldes");
        rating = getArguments().getString("rate");
        openingtime = getArguments().getString("open");
        closingtime = getArguments().getString("close");
        pricerange = getArguments().getString("range");
        image = getArguments().getString("img");
        Lat = getArguments().getString("lat");
        longi = getArguments().getString("long");
        ratingBar = view.findViewById(R.id.rating);
        profile=view.findViewById(R.id.descriptionimage);
        Glide.with(getContext())
                .load(image)
                .into(profile);
        backgroundimage=view.findViewById(R.id.background);
        details=view.findViewById(R.id.details);
        reviews=view.findViewById(R.id.reviews);
        information=view.findViewById(R.id.information);
        ratingBar.setRating(Float.parseFloat(rating));
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
                //details.setBackgroundColor(Color.BLACK);
                //reviews.setBackgroundColor(Color.TRANSPARENT);
               // information.setBackgroundColor(Color.TRANSPARENT);

            }
        });

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new ReviewFragment();
                Bundle args = new Bundle();
                args.putString("title2",title);
                args.putString("name","hotels");
                args.putString("superRating",rating);
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();
                //reviews.setBackgroundColor(Color.BLACK);
               // details.setBackgroundColor(Color.TRANSPARENT);
              //  information.setBackgroundColor(Color.TRANSPARENT);

            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment  fragment = new InformationFragment();
                Bundle args = new Bundle();

                args.putString("rate1",rating);
                args.putString("open1", openingtime);
                args.putString("close1", closingtime);
                args.putString("range1", pricerange);


                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.description_container, fragment).commit();
                //information.setBackgroundColor(Color.BLACK);
              //  details.setBackgroundColor(Color.TRANSPARENT);
              //  reviews.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        return view;
    }


}

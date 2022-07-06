package com.example.khoj.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.khoj.Fragments.Hospital.TrendingFragmentHospital;
import com.example.khoj.Fragments.Hotel.TrendingFragmentHotel;
import com.example.khoj.Fragments.Landmarks.TrendingFragmentLandmarks;
import com.example.khoj.R;

public class SelectionFragment extends Fragment {

    Button food, hospitals, driving, visiting;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selection_fragment, container, false);

        food= view.findViewById(R.id.hotels);
        hospitals = view.findViewById(R.id.hospitals);
        driving = view.findViewById(R.id.busstops);
        visiting = view.findViewById(R.id.landmarks);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new TrendingFragmentHotel();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();

            }
        });

        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new TrendingFragmentHospital();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();

            }
        });

        visiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new TrendingFragmentLandmarks();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();

            }
        });

        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Please Wait For the Future Update", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



}
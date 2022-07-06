package com.example.khoj.Fragments.Landmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;

public class InformationLandmarkFragment extends Fragment {
    TextView rating, openingTime;
    String rate, openingtime;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infolandmark_layout,container,false);

            rating = view.findViewById(R.id.locationsviewed);
            openingTime = view.findViewById(R.id.feedbacksviewed);

        rate = getArguments().getString("rate1");
        openingtime = getArguments().getString("open1");

        rating.setText(rate);
        openingTime.setText(openingtime);

        return view;
    }


}

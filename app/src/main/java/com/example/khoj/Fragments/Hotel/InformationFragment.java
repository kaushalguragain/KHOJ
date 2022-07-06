package com.example.khoj.Fragments.Hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;

public class InformationFragment extends Fragment {
    TextView rating, openingTime, closingTime, maxServicePrice;
    String rate, openingtime, closingtime,price_range;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_layout,container,false);

            rating = view.findViewById(R.id.locationsviewed);
            openingTime = view.findViewById(R.id.feedbacksviewed);
            closingTime= view.findViewById(R.id.userratingviewed);
            maxServicePrice= view.findViewById(R.id.userloginviewed);
        rate = getArguments().getString("rate1");
        openingtime = getArguments().getString("open1");
        closingtime = getArguments().getString("close1");
        price_range = getArguments().getString("range1");
        rating.setText(rate);
        openingTime.setText(openingtime);
        closingTime.setText(closingtime);
        maxServicePrice.setText(price_range);

        return view;
    }


}

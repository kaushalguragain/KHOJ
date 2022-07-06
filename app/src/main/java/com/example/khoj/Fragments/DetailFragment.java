package com.example.khoj.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;

public class DetailFragment extends Fragment {
TextView title1, body;
String title, description;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detaail_layout,container,false);

        title1 = view.findViewById(R.id.titledetails);
        body = view.findViewById(R.id.descriptiontext);
        title= getArguments().getString("title1");
        description = getArguments().getString("description1");

        title1.setText(title);
        body.setText(description);

        return view;
    }
}

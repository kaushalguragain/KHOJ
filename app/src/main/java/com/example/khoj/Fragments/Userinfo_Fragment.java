package com.example.khoj.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;
import com.example.khoj.Services.FetchLocation;
import com.google.firebase.auth.FirebaseAuth;

public class Userinfo_Fragment extends Fragment {
    Button hotel,hospital,landmark,trend;
    FirebaseAuth auth ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userinfo_fragment,container,false);
        ImageView imageview = view.findViewById(R.id.profilepicture);
        TextView username = view.findViewById(R.id.username);


        auth = FirebaseAuth.getInstance();
        username.setText(auth.getCurrentUser().getEmail());

        TextView hotelstextview = view.findViewById(R.id.location);
        TextView hospitalstextview = view.findViewById(R.id.locationsviewed);
        TextView landmarkstextview = view.findViewById(R.id.userrating);
        TextView trendingtextview = view.findViewById(R.id.userlogin);
        Button addbutton = view.findViewById(R.id.add);
        hotel = view.findViewById(R.id.locationshotels);
        hospital = view.findViewById(R.id.locationhospitals);
        landmark = view.findViewById(R.id.locationlandmarks);
        trend = view.findViewById(R.id.locationtrending);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchLocation.class);
                intent.putExtra("key","hotel");
                startActivity(intent);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchLocation.class);
                intent.putExtra("key","hospital");
                startActivity(intent);
            }
        });

        landmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchLocation.class);
                intent.putExtra("key","landmark");
                startActivity(intent);
            }
        });

        trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Please Wait For the Future Update", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

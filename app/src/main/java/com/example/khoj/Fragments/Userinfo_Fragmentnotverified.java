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

public class Userinfo_Fragmentnotverified extends Fragment {
    Button hotel,hospital,landmark,trend;
    FirebaseAuth auth ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userinfo_fragmentnotverified,container,false);
        ImageView imageview = view.findViewById(R.id.profilepicture);
        TextView username = view.findViewById(R.id.username);


        auth = FirebaseAuth.getInstance();
        username.setText(auth.getCurrentUser().getEmail());



        return view;
    }
}

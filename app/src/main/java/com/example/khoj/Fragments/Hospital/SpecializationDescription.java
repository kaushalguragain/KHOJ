package com.example.khoj.Fragments.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khoj.R;

public class SpecializationDescription extends AppCompatActivity {
    ImageView imageView;
    TextView titletext, desctext;
    String specialization, image, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization_description);

        specialization = getIntent().getStringExtra("specialization");
        image = getIntent().getStringExtra("image");
        description = getIntent().getStringExtra("description");
        titletext = findViewById(R.id.titletext);
        desctext = findViewById(R.id.descriptiontext);
        desctext.setMovementMethod(new ScrollingMovementMethod());
        titletext.setText(specialization);
        desctext.setText(description);



    }
}
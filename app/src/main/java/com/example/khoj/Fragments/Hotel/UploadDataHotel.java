package com.example.khoj.Fragments.Hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khoj.MainActivity;
import com.example.khoj.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class UploadDataHotel extends AppCompatActivity {
    private static final String Key_Name = "name";
    private static final String Key_Description = "description";
    private static final String Key_Rating = "rating";
    private static final String Key_OpeningTime = "openingTime";
    private static final String Key_ClosingTime = "closingTime";
    private static final String Key_LargeDescription = "largeDescription";
    private static final String Key_Lunch = "lunch";
    private static final String Key_Dinner = "dinner";
    private static final String Key_Pricerange = "priceRange";
    private static final String Key_Pricerange1 = "minPrice";
    private static final String Key_Profilepicture = "image";
    private static final String Key_Longitude = "longitude";
    private static final String Key_Latitude = "latitude";


    StorageReference storageReference;
    FirebaseStorage storage;

    EditText name, description, rating, openingtime, closingtime, largeDescription, pricerange, minprice;
    Button image, save;
    Uri fetchdata;
    String longitude, latitude;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        longitude = getIntent().getStringExtra("longitude1");
        latitude = getIntent().getStringExtra("latitude1");
        fetchdata = getIntent().getParcelableExtra("imageurl");
        largeDescription = findViewById(R.id.largedescription);
        name = findViewById(R.id.edit_text_title);
        description = findViewById(R.id.edit_text_description);
        rating = findViewById(R.id.rating);
        openingtime = findViewById(R.id.openingtime);
        closingtime = findViewById(R.id.closingtime);
        pricerange = findViewById(R.id.pricerange);
        minprice = findViewById(R.id.pricerange1);
        save = findViewById(R.id.button);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();




    }
    public void saveNote(View v){

        String name_ = name.getText().toString();
        String description_ = description.getText().toString();
        String rating_ = rating.getText().toString();
        String openingtime_ = openingtime.getText().toString();
        String closingtime_ = closingtime.getText().toString();
        String largeDescription_ = largeDescription.getText().toString();
        //String lunch_ = lunch.getText().toString();
        //String dinner_ = dinner.getText().toString();
        String pricerange_ = pricerange.getText().toString();
        String pricerange1_ = minprice.getText().toString();


        Map<String,Object> note = new HashMap<>();
        note.put(Key_Name, name_);
        note.put(Key_Description, description_);
        note.put(Key_LargeDescription, largeDescription_);
        note.put(Key_Rating, rating_);
        note.put(Key_OpeningTime, openingtime_);
        note.put(Key_ClosingTime, closingtime_);
        //note.put(Key_Breakfast, breakfast_);
        //note.put(Key_Lunch, lunch_);
        //note.put(Key_Dinner, dinner_);
        note.put(Key_Pricerange,pricerange_);
        note.put(Key_Profilepicture,fetchdata.toString());
        note.put(Key_Longitude,longitude);
        note.put(Key_Latitude,latitude);
        note.put(Key_Pricerange1,pricerange1_);

        db.collection("hotels").document(name_).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UploadDataHotel.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadDataHotel.this, MainActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadDataHotel.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
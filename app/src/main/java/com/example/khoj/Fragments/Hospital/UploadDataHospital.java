package com.example.khoj.Fragments.Hospital;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoj.Fragments.Hotel.UploadDataHotel;
import com.example.khoj.MainActivity;
import com.example.khoj.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class UploadDataHospital extends AppCompatActivity {
    private static final String Key_Name = "name";
    private static final String Key_S = "slogan";
    private static final String Key_Rating = "rating";
    private static final String Key_SN = "servicenumber";
    private static final String Key_CH = "covidhotline";
    private static final String Key_LargeDescription = "description";
    private static final String Key_CA = "clinic_availability";
    private static final String Key_Profilepicture = "image";
    private static final String Key_Longitude = "longitude";
    private static final String Key_Latitude = "latitude";
    private static final String Key_Specilization = "specialization";

    StorageReference storageReference;
    FirebaseStorage storage;

    String clinic_, specialization_;
    EditText name, description, rating, servicenumber, covidhotline, largeDescription;
    Spinner selectspecialization,    clinicavailability;
    Button image, save;
    Uri fetchdata;
    TextView hospitalclinic1, specialization1;
    String longitude, latitude;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data_hospital);
        hospitalclinic1 = findViewById(R.id.hospitalclinic);
        specialization1 = findViewById(R.id.specialization);
        longitude = getIntent().getStringExtra("longitude1");
        latitude = getIntent().getStringExtra("latitude1");
        fetchdata = getIntent().getParcelableExtra("imageurl");
        largeDescription = findViewById(R.id.hospitaldiscription);
        name = findViewById(R.id.hospitalName);
        description = findViewById(R.id.slogan);
        rating = findViewById(R.id.rating);
        servicenumber = findViewById(R.id.openingtime);
        covidhotline = findViewById(R.id.closingtime);
        selectspecialization = findViewById(R.id.pricerange1);
        clinicavailability = findViewById(R.id.pricerange);
        save = findViewById(R.id.button);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        ArrayAdapter<CharSequence> dropdownlist = ArrayAdapter.createFromResource(UploadDataHospital.this, R.array.typefilter1, R.layout.spinner_items);
        dropdownlist.setDropDownViewResource(R.layout.spinner_items);
        selectspecialization.setAdapter(dropdownlist);
        selectspecialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                         specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 1:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 2:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 3:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 4:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 5:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 6:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 7:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 8:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 9:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 10:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 11:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 12:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 13:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 14:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 15:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 16:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 17:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 18:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                    case 19:
                        specialization_ = String.valueOf(dropdownlist.getItem(i));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> dropdownlist1 = ArrayAdapter.createFromResource(UploadDataHospital.this, R.array.typefilter2, R.layout.spinner_items);
        dropdownlist1.setDropDownViewResource(R.layout.spinner_items);
        clinicavailability.setAdapter(dropdownlist1);
        clinicavailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        clinic_ = String.valueOf(dropdownlist1.getItem(i));
                        break;
                    case 1:
                        clinic_ = String.valueOf(dropdownlist1.getItem(i));
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


    public void saveNote(View v){

        String name_ = name.getText().toString();
        String description_ = description.getText().toString();
        String rating_ = rating.getText().toString();
        String servicenumber_ = servicenumber.getText().toString();
        String covidhotline_ = covidhotline.getText().toString();
        String largeDescription_ = largeDescription.getText().toString();

        Map<String,Object> note = new HashMap<>();
        note.put(Key_Name, name_);
        note.put(Key_S, description_);
        note.put(Key_LargeDescription, largeDescription_);
        note.put(Key_Rating, rating_);
        note.put(Key_SN, servicenumber_);
        note.put(Key_CH, covidhotline_);
        note.put(Key_Profilepicture,fetchdata.toString());
        note.put(Key_Longitude,longitude);
        note.put(Key_Latitude,latitude);
        note.put(Key_CA,clinic_);
        note.put(Key_Specilization,specialization_);

        db.collection("hospitals").document(name_).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UploadDataHospital.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadDataHospital.this, MainActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadDataHospital.this, "failure", Toast.LENGTH_SHORT).show();
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
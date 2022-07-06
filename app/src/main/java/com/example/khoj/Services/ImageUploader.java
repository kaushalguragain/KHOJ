package com.example.khoj.Services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.khoj.Fragments.Hospital.UploadDataHospital;
import com.example.khoj.Fragments.Hotel.UploadDataHotel;
import com.example.khoj.Fragments.Landmarks.UploadDataLandmarks;
import com.example.khoj.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ImageUploader extends AppCompatActivity {
    Button chooseImg,uploadImage;
    ImageView showImage;
    Uri filelocaton;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    ProgressBar progress;
    String longitude, latitude,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_uploader);
        longitude = getIntent().getStringExtra("longitude");
        latitude = getIntent().getStringExtra("latitude");
        key = getIntent().getStringExtra("key1");
        chooseImg = findViewById(R.id.chooseImage);
        showImage = findViewById(R.id.viewImage);
        uploadImage = findViewById(R.id.uploadimage);
        //firebase Storage reference
        progress = findViewById(R.id.imageprogress);
        storage  = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        SelectImage();
                uploadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        UploadImage();

                    }
                });

            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ImageUploader.this, "Upload An Image First", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void SelectImage(){
        //intent to gallary
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check PICKIMAGE AND IMAGE CODE
        //SET IMAGE IN IMAGE VIEW

        if(requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data!=null
                && data.getData() != null){

                //get Uri
            filelocaton = data.getData();
            try{
                //setimg using bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filelocaton);
                showImage.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    private void UploadImage(){

            progress.setVisibility(View.VISIBLE);
            if(filelocaton!=null){
                final ProgressDialog progressDialog
                        = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                final StorageReference ref = storageReference.child("images/"+
                        UUID.randomUUID().toString());
                ref.putFile(filelocaton).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(ImageUploader.this,
                                        "Image Uploaded!!",
                                        Toast.LENGTH_SHORT)
                                .show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                filelocaton = uri;

                                if(key.equals("hotel")) {
                                    Intent intent = new Intent(ImageUploader.this, UploadDataHotel.class);
                                    intent.putExtra("imageurl", filelocaton);
                                    intent.putExtra("longitude1", longitude);
                                    intent.putExtra("latitude1", latitude);
                                    progress.setVisibility(View.GONE);
                                    startActivity(intent);
                                }
                                else if(key.equals("hospital")) {
                                    Intent intent = new Intent(ImageUploader.this, UploadDataHospital.class);
                                    intent.putExtra("imageurl", filelocaton);
                                    intent.putExtra("longitude1", longitude);
                                    intent.putExtra("latitude1", latitude);
                                    progress.setVisibility(View.GONE);
                                    startActivity(intent);
                                }
                                else if(key.equals("landmark")) {
                                    Intent intent = new Intent(ImageUploader.this, UploadDataLandmarks.class);
                                    intent.putExtra("imageurl", filelocaton);
                                    intent.putExtra("longitude1", longitude);
                                    intent.putExtra("latitude1", latitude);
                                    progress.setVisibility(View.GONE);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(ImageUploader.this, "can process further", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress
                                = (100.0
                                * snapshot.getBytesTransferred()
                                / snapshot.getTotalByteCount());
                        progressDialog.setMessage(
                                "Uploaded "
                                        + (int)progress + "%");
                    }
                });
            }

    }
}
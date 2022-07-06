package com.example.khoj.Fragments.Landmarks;

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
import com.example.khoj.Fragments.SelectionFragment;
import com.example.khoj.MainActivity;
import com.example.khoj.R;
import com.example.khoj.Services.ImageUploader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SiteViewDescription extends AppCompatActivity {
    Button chooseImg,uploadImage;
    ImageView showImage;
    Uri filelocaton;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final String Key_Name = "image";
    ProgressBar progress;
    String name,username;
    FirebaseAuth auth ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_view_description);
        chooseImg = findViewById(R.id.chooseImage);
        name= getIntent().getStringExtra("titlename");
        showImage = findViewById(R.id.viewImage);
        uploadImage = findViewById(R.id.uploadimage);
        //firebase Storage reference
        progress = findViewById(R.id.imageprogress);
        storage  = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getEmail();
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

                Toast.makeText(SiteViewDescription.this, "Upload An Image First", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(SiteViewDescription.this,
                            "Image Uploaded!!",
                            Toast.LENGTH_SHORT)
                            .show();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            filelocaton = uri;

                            Map<String,Object> note = new HashMap<>();
                            note.put(Key_Name, filelocaton.toString());

                            db.collection("landmarks").document(name).collection("siteviews").document(username).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(SiteViewDescription.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SiteViewDescription.this, MainActivity.class);

                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });



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
package com.example.khoj.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.khoj.Fragments.Hospital.TrendingFragmentHospital;
import com.example.khoj.Fragments.Hotel.TrendingFragmentHotel;
import com.example.khoj.Fragments.Landmarks.TrendingFragmentLandmarks;
import com.example.khoj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddComment extends Fragment {

    RatingBar ratingBar;
    EditText comment;
    TextView referenceText;
    Button button;
    String getcomment;

    Float rating;
    FirebaseAuth auth ;
    String username;

    private static final String Key_Name = "username";
    private static final String Key_Comment = "comment";
    private static final String Key_Rating = "rating";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name,collectionname,mainRating;
    Integer compareRatingA, compareRatingB;
    Integer counter;
    Integer A=-4, B=4, C=-3, D=3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addcomment, container, false);
        comment = view.findViewById(R.id.comment1);
        ratingBar = view.findViewById(R.id.rating);
        referenceText = view.findViewById(R.id.referencetext);
        button = view.findViewById(R.id.button);
        name= getArguments().getString("title");
        collectionname= getArguments().getString("name");
        mainRating = getArguments().getString("superRating1");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating = v;
            }
        });

        compareRatingA = Integer.valueOf(mainRating);

        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getEmail();
        countervalue();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compareRatingB = Math.round(rating);

                compareRatingCaltulator(compareRatingA,compareRatingB);
                counter1value();
                if(counter.equals(A)||counter.equals(B)||counter.equals(C)||counter.equals(D)){

                    changeratingDatabase(compareRatingA,counter,collectionname,name);

                }
                getcomment = comment.getText().toString();
                Map<String,Object> note = new HashMap<>();
                note.put(Key_Name, username);
                note.put(Key_Comment, getcomment);
                note.put(Key_Rating, rating.toString());

                db.collection(collectionname+"/").document(name).collection("/review/").document(username).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getActivity(), "Review Updated", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                if(collectionname.equals("hotels")) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment fragment = new TrendingFragmentHotel();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                }
                else if(collectionname.equals("hospitals")) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment fragment = new TrendingFragmentHospital();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                }
                else if(collectionname.equals("landmarks")) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment fragment = new TrendingFragmentLandmarks();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                }
                else{

                }
            }
        });


        return view;
    }


    public void compareRatingCaltulator(Integer A, Integer B){

        if(B.equals(A)){
            //donothing
        }
        else if(B<A){
            counter = counter-1;
            db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {


                }
            });
        }
        else if(B>A){
            counter = counter+1;
            db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });
        }
        else{

         //donothing
        }

    }

    public void changeratingDatabase(Integer Rating, Integer count, String serviceName, String name){
        Integer holdValue;
        if(count.equals(A)){
            holdValue = Rating-1;
            db.collection(serviceName).document(name).update("rating",holdValue.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Rating Updated", Toast.LENGTH_SHORT).show();
                    counter=0;
                    db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "counter Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }
        else if(count.equals(B)){
            holdValue = Rating+1;
            db.collection(serviceName).document(name).update("rating",holdValue.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Rating Updated", Toast.LENGTH_SHORT).show();
                    counter=0;
                    db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "counter Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }
        else if(count.equals(C)){
            holdValue = Rating-1;
            db.collection(serviceName).document(name).update("rating",holdValue.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Rating Updated", Toast.LENGTH_SHORT).show();
                    counter=0;
                    db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "counter Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });



        }
        else if(count.equals(D)){
            holdValue = Rating+1;
            db.collection(serviceName).document(name).update("rating",holdValue.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Rating Updated", Toast.LENGTH_SHORT).show();
                    counter=0;
                    db.collection("counterRecord").document("counterdata").update("counter",String.valueOf(counter)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "counter Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
        else{


        }


    }

    public void countervalue(){

        DocumentReference docRef = db.collection("counterRecord").document("counterdata");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                counter = Integer.valueOf(documentSnapshot.getString("counter"));

            }

        });




        }

    public void counter1value(){

        DocumentReference docRef = db.collection("counterRecord").document("counterdata");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                counter = Integer.valueOf(documentSnapshot.getString("counter"));

            }

        });




    }


}

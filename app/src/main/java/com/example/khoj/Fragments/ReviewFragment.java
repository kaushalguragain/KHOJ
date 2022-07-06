package com.example.khoj.Fragments;

import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.khoj.R;
import com.example.khoj.Services.CommentDTO;
import com.example.khoj.Services.HotelDto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReviewFragment extends Fragment {
    Button addcomment;
    RecyclerView commentRecycleview;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore firebaseFirestore;
    String name,collectioname;
    String mainRating;
    FirestoreRecyclerAdapter<CommentDTO, ReviewFragment.commentHolder> firestoreRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_layout,container,false);
        addcomment = view.findViewById(R.id.addcomment);
        commentRecycleview = view.findViewById(R.id.commentrecycleview);
        layoutManager = new LinearLayoutManager(container.getContext());
        firebaseFirestore = FirebaseFirestore.getInstance();
        name= getArguments().getString("title2");
        collectioname = getArguments().getString("name");
        mainRating = getArguments().getString("superRating");
        //Query
        CollectionReference query = firebaseFirestore.collection(collectioname).document(name).collection("review");
        //Recycleoptions
        FirestoreRecyclerOptions<CommentDTO> options = new FirestoreRecyclerOptions.Builder<CommentDTO>().setQuery(query,
                CommentDTO.class)
                .build();
        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<CommentDTO, commentHolder>(options) {
            @NonNull
            @Override
            public ReviewFragment.commentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewrow_comment, parent, false);
                return new ReviewFragment.commentHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ReviewFragment.commentHolder holder, int position, @NonNull final CommentDTO model) {
                TextView type = holder.username;
                TextView description = holder.comment;
                RatingBar ratingbar = holder.ratingBar;

                try {
                    type.setText(model.getUsername());
                    description.setText(model.getComment());
                    ratingbar.setRating(Float.parseFloat(model.getRating()));
                }
                catch(Exception e){
                    System.out.println("error:"+e);

                }


            }
        };
        firestoreRecyclerAdapter.startListening();
        commentRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        commentRecycleview.setAdapter(firestoreRecyclerAdapter);


        addcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new AddComment();
                Bundle args = new Bundle();
                args.putString("title", name);
                args.putString("name",collectioname);
                args.putString("superRating1",mainRating);
                fragment.setArguments(args);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

            }
        });


        return view;
    }

private static class commentHolder extends  RecyclerView.ViewHolder {
    TextView username,  comment;
   RatingBar ratingBar;


    public commentHolder(@NonNull View itemView) {
        super(itemView);
        this.username = itemView.findViewById(R.id.username);
        this.comment = itemView.findViewById(R.id.comment);
        this.ratingBar = itemView.findViewById(R.id.rating);

    }
}
    //comment: view pager https://www.javatpoint.com/android-image-slider
    //study spinner https://developer.android.com/guide/topics/ui/controls/spinner#java


    @Override
    public void onStop() {
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }
}

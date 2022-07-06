package com.example.khoj.Fragments.Landmarks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.khoj.Fragments.Hospital.SpecializationDescription;
import com.example.khoj.Fragments.ReviewFragment;
import com.example.khoj.R;
import com.example.khoj.Services.CommentDTO;
import com.example.khoj.Services.ImageDTO;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SiteViewsFragment extends Fragment {

    TextView Specialization;
    RecyclerView imageRecycleview;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore firebaseFirestore;
    String name,collectioname;
    FirestoreRecyclerAdapter<ImageDTO, SiteViewsFragment.imageHolder> firestoreRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.siteview_layout,container,false);

        Specialization = view.findViewById(R.id.specializationid);
        name= getArguments().getString("title1");
        imageRecycleview = view.findViewById(R.id.imagerecycleview);
        layoutManager = new LinearLayoutManager(container.getContext());
        firebaseFirestore = FirebaseFirestore.getInstance();

        collectioname= getArguments().getString("name");;
        CollectionReference query = firebaseFirestore.collection(collectioname).document(name).collection("siteviews");
        //Recycleoptions
        FirestoreRecyclerOptions<ImageDTO> options = new FirestoreRecyclerOptions.Builder<ImageDTO>().setQuery(query,
                ImageDTO.class)
                .build();
        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<ImageDTO, SiteViewsFragment.imageHolder>(options) {
            @NonNull
            @Override
            public SiteViewsFragment.imageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_row, parent, false);
                return new SiteViewsFragment.imageHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SiteViewsFragment.imageHolder holder, int position, @NonNull final ImageDTO model) {
                ImageView imgholder = holder.imgview;

                Glide.with(Objects.requireNonNull(getContext()))
                        .load(model.getImage())
                        .into(imgholder);


            }
        };
        firestoreRecyclerAdapter.startListening();
        imageRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        imageRecycleview.setAdapter(firestoreRecyclerAdapter);





        Specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SiteViewDescription.class);
                intent.putExtra("titlename", name);
                startActivity(intent);
            }
        });

        return view;
    }
    private static class imageHolder extends  RecyclerView.ViewHolder {
        ImageView imgview;


        public imageHolder(@NonNull View itemView) {
            super(itemView);
            this.imgview = itemView.findViewById(R.id.recycleimage);

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }

}

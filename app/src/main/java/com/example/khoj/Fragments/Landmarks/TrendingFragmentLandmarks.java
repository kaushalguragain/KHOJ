package com.example.khoj.Fragments.Landmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.khoj.Fragments.Hotel.DescriptionHotelFragment;
import com.example.khoj.R;
import com.example.khoj.Services.HotelDto;
import com.example.khoj.Services.LandmarksDto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TrendingFragmentLandmarks extends Fragment {
RecyclerView trendingrecycleview;
RecyclerView.LayoutManager layoutManager;
FirebaseFirestore firebaseFirestore;
FirestoreRecyclerAdapter<LandmarksDto,landmarkViewHolder> firestoreRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View  view =  inflater.inflate(R.layout.trending_fragment,container,false);
        layoutManager = new LinearLayoutManager(container.getContext());
        trendingrecycleview = view.findViewById(R.id.trending_recycleview);
        firebaseFirestore = FirebaseFirestore.getInstance();
        //Query
        CollectionReference query = firebaseFirestore.collection("landmarks");

        //Recycleoptions
        FirestoreRecyclerOptions<LandmarksDto>  options = new FirestoreRecyclerOptions.Builder<LandmarksDto>().setQuery(query,LandmarksDto
                .class)
                .build();
        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<LandmarksDto, landmarkViewHolder>(options) {
            @NonNull
            @Override
            public landmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewrow_trending, parent, false);
                return new landmarkViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull landmarkViewHolder holder, int position, @NonNull final LandmarksDto model) {
                TextView type = holder.title;
                TextView description = holder.description;
                ImageView profilepicture = holder.profile_Picture;
                Button button = holder.button;

                type.setText(model.getName());
                description.setText(model.getDescription());
                Glide.with(getContext())
                        .load(model.getImage())
                        .into(profilepicture);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Fragment fragment = new DescriptionLandmarksFragment();
                        Bundle args = new Bundle();
                        args.putString("title", model.getName());
                        args.putString("description", model.getDescription());
                        args.putString("ldes", model.getLargeDescription());
                        args.putString("rate", model.getRating());
                        args.putString("open", model.getLocation());
                        args.putString("close", model.getHistoricalImportance());
                        args.putString("img", model.getImage());
                        args.putString("lat", model.getLatitude());
                        args.putString("long",model.getLongitude());
                        fragment.setArguments(args);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();

                    }
                });

            }
        };
        firestoreRecyclerAdapter.startListening();
       trendingrecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
       trendingrecycleview.setAdapter(firestoreRecyclerAdapter);


         return view;
    }

    private static class landmarkViewHolder extends  RecyclerView.ViewHolder {
        TextView title,  description;
        ImageView profile_Picture;
        Button button ;


        public landmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.text_type);
            this.description = itemView.findViewById(R.id.description);
            this.profile_Picture = itemView.findViewById(R.id.imageview);
            this.button = itemView.findViewById(R.id.button);

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

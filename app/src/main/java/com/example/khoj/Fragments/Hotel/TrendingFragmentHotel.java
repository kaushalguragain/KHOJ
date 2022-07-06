package com.example.khoj.Fragments.Hotel;

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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TrendingFragmentHotel extends Fragment {
RecyclerView trendingrecycleview;
RecyclerView.LayoutManager layoutManager;
FirebaseFirestore firebaseFirestore;
FirestoreRecyclerAdapter<HotelDto,hotelViewHolder> firestoreRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View  view =  inflater.inflate(R.layout.trending_fragment,container,false);
        layoutManager = new LinearLayoutManager(container.getContext());
        trendingrecycleview = view.findViewById(R.id.trending_recycleview);
        firebaseFirestore = FirebaseFirestore.getInstance();
        //Query
        CollectionReference query = firebaseFirestore.collection("hotels");

        //Recycleoptions
        FirestoreRecyclerOptions<HotelDto>  options = new FirestoreRecyclerOptions.Builder<HotelDto>().setQuery(query, HotelDto.class)
                .build();
        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<HotelDto, hotelViewHolder>(options) {
            @NonNull
            @Override
            public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewrow_trending, parent, false);
                return new hotelViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull hotelViewHolder holder, int position, @NonNull final HotelDto model) {
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
                        Fragment fragment = new DescriptionHotelFragment();
                        Bundle args = new Bundle();
                        args.putString("title", model.getName());
                        args.putString("description", model.getDescription());
                        args.putString("ldes", model.getLargeDescription());
                        args.putString("rate", model.getRating());
                        args.putString("open", model.getOpeningTime());
                        args.putString("close", model.getClosingTime());
                        args.putString("range", model.getPriceRange());
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

    private static class hotelViewHolder extends  RecyclerView.ViewHolder {
        TextView title,  description;
        ImageView profile_Picture;
        Button button ;


        public hotelViewHolder(@NonNull View itemView) {
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

package com.example.khoj.Fragments.Landmarks;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;

public class HistoryFragment extends Fragment {
    TextView desctext, Info;
    String history,title;
    String ImageURL, description, checkData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragmentlayout,container,false);
        Info = view.findViewById(R.id.infotext);
        title = getArguments().getString("history1");

        Info.setText(title);
        Info.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }


}

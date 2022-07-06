package com.example.khoj.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.khoj.R;

public class RequirementFragment extends Fragment {

    Spinner setType, setOpeningtime, setClosingtime;
    EditText enterMaxprice, enterMinprice;
    Button setRequirement;
    Boolean check;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.requirementfrag_layout,container,false);

        setType = (Spinner) view.findViewById(R.id.select_type);
        //setOpeningtime =(Spinner) view.findViewById(R.id.select_openingtime);
        //setClosingtime =(Spinner) view.findViewById(R.id.select_closingtime);
        setRequirement= (Button) view.findViewById(R.id.search_requirements);
        enterMaxprice = view.findViewById(R.id.entermaxprice);
        enterMinprice = view.findViewById(R.id.enterminprice);


        ArrayAdapter<CharSequence> dropdownlist = ArrayAdapter.createFromResource(container.getContext(), R.array.typefilter, R.layout.spinner_items);
        dropdownlist.setDropDownViewResource(R.layout.spinner_items);
        setType.setAdapter(dropdownlist);
        setType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       /* ArrayAdapter<CharSequence> dropdownlist_openingtime = ArrayAdapter.createFromResource(container.getContext(), R.array.opening_time, R.layout.spinner_items);
        dropdownlist_openingtime.setDropDownViewResource(R.layout.spinner_items);
        setOpeningtime.setAdapter(dropdownlist_openingtime);
       setOpeningtime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        ArrayAdapter<CharSequence> dropdownlist_closingtime = ArrayAdapter.createFromResource(container.getContext(), R.array.closing_time, R.layout.spinner_items);
        dropdownlist_closingtime.setDropDownViewResource(R.layout.spinner_items);
        setClosingtime.setAdapter(dropdownlist_closingtime);
        setClosingtime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        */



        setRequirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price1 = String.valueOf(enterMinprice.getText());
                String price2= String.valueOf(enterMaxprice.getText());

                if(TextUtils.isEmpty(price1)){
                    Toast.makeText(getContext(), "Enter Min Price", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(price2)){
                    Toast.makeText(getContext(), "Enter Max Price", Toast.LENGTH_SHORT).show();
                    return;
                }
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new DashboardcopyFragment();
                Bundle args = new Bundle();
                args.putString("minPrice", price1);
                args.putString("maxPrice", price2);
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


            }
        });

        return view;
    }
}

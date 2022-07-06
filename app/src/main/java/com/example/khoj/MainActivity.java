package com.example.khoj;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.khoj.Authentication.SignInActivity;
import com.example.khoj.Fragments.HistoryFragment;
import com.example.khoj.Fragments.SelectionFragment;
import com.example.khoj.Fragments.Hotel.TrendingFragmentHotel;
import com.example.khoj.Fragments.DashboardFragment;
import com.example.khoj.Fragments.Userinfo_Fragment;
import com.example.khoj.Fragments.Userinfo_Fragmentnotverified;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mapbox.mapboxsdk.Mapbox;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth mAuth;
    DrawerLayout drawer;
    Toolbar toolbar;
    FrameLayout frameLayout;
    private NavigationView navigation;
    TextView mtextView;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(MainActivity.this,getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);
            navigation = findViewById(R.id.sidenavigation);
            View header = navigation.getHeaderView(0);
            mtextView = (TextView) header.findViewById(R.id.txtname);
            String username = getIntent().getStringExtra("emaillist");

            mtextView.setText(username);

        drawer = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.fragment_container);
        //defining toolbar
        toolbar = findViewById(R.id.appbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_Items);

        //settingup side navigation
        navigation= findViewById(R.id.sidenavigation);
        navigation.setNavigationItemSelectedListener(MainActivity.this);
        //settingup bottomnavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.dashboard:
                        navigation.setCheckedItem(R.id.menu_none);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
                        break;

                    case R.id.trending:
                        navigation.setCheckedItem(R.id.menu_none);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SelectionFragment()).commit();
                        break;
                    case R.id.user:
                        navigation.setCheckedItem(R.id.menu_none);
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        String email = auth.getCurrentUser().getEmail();
                        if(email.equals("kaushal4@gmail.com")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Userinfo_Fragmentnotverified()).commit();
                        }
                        else{
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Userinfo_Fragment()).commit();

                        }
                        break;
                }


                return true;
            }
        });



        //built in toggle button for appbar
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toogle);
        toogle.syncState();
        toogle.getDrawerArrowDrawable().setColor(Color.WHITE);

        //loading dashboard as the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.favourites:
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getSelectedItemId());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TrendingFragmentHotel()).commit();
                break;

            case R.id.logout:
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getSelectedItemId());
                FirebaseAuth.getInstance().signOut();
                GoogleSignIn.getClient(
                        this,
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                ).signOut();
                Intent intent = new Intent(getApplication(), SignInActivity.class);
                startActivity(intent);

                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            drawer.closeDrawers();
            super.onBackPressed();
        }
    }

}






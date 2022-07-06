package com.example.khoj.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.khoj.MainActivity;
import com.example.khoj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    EditText signInemail, signInPassword;
    Button signInbutton,signupbutton;
    ProgressBar progressbar1;
    String storeemail;
    private FirebaseAuth loginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        progressbar1 = findViewById(R.id.progressbar1);
        signupbutton = findViewById(R.id.signupbutton);
        signInemail = findViewById(R.id.enteremail);
        signInPassword = findViewById(R.id.enterpassword);
        signInbutton = findViewById(R.id.signInbutton);
        loginAuth = FirebaseAuth.getInstance();
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = signInemail.getText().toString().trim();
                String password = signInPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignInActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignInActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
               progressbar1.setVisibility(View.VISIBLE);
                loginAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressbar1.setVisibility(View.GONE);
                                if(task.isSuccessful()){

                                    Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                                      intent.putExtra("emaillist",email);
                                      storeemail = email;
                                    startActivity(intent);

                                }
                                else{
                                    Toast.makeText(SignInActivity.this, "Login Failed or User Not Available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = loginAuth.getCurrentUser();
        if(currentUser != null){
            storeemail = currentUser.getEmail();
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            intent.putExtra("emaillist",storeemail);
            startActivity(intent);
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
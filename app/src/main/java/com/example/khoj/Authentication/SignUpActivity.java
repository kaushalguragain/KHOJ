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

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    EditText emailaddress, enterpassword, confirmpassword;
    Button registeButton;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailaddress = findViewById(R.id.emailaddress);
        enterpassword= findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        registeButton =  findViewById(R.id.signupbutton);
        progressBar = findViewById(R.id.progressbar);

        firebaseAuth = FirebaseAuth.getInstance();
        registeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = emailaddress.getText().toString().trim();
                String password = enterpassword.getText().toString().trim();
                String cpassword = confirmpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cpassword)){
                    Toast.makeText(SignUpActivity.this, "Confirm Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(SignUpActivity.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(cpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(SignUpActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                        }
                    });

                }
            }
        });


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
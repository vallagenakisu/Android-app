package com.example.mainprojectpersonallifetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText Email,password;
    private Button signUp;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email1 = Email.getText().toString();
                String password1 = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email1,password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {

                                }
                                else
                                {
                                }
                            }
                        });

                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);

            }
        });



    }
}
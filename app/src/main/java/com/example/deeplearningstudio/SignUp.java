package com.example.deeplearningstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    Button signUpButton;
    TextView signInText;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.signUpButton);
        signInText = findViewById(R.id.signInText);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            intent =new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                intent =new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });
    }



}
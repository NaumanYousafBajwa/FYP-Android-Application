package com.example.deeplearningstudio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deeplearningstudio.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignIn extends AppCompatActivity {

    String signinUrl = null;

    EditText email, password;
    TextView signupText;
    Button butt, gooButt;

    InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (EditText) findViewById(R.id.signInEmail);
        password = (EditText) findViewById(R.id.signInPassword);
        butt = (Button) findViewById(R.id.signInButton);
        gooButt = (Button) findViewById(R.id.googleButton);
        signupText = (TextView) findViewById(R.id.signUpText);

//        JSONObject post_dict = new JSONObject();
//        try {
//            post_dict.put("email", email.getText().toString());
//            post_dict.put("password", password.getText().toString());
//
//            HttpPostRequest postReq = new HttpPostRequest();
//
//            String response = postReq.execute(signinUrl, String.valueOf(post_dict)).get();
//            System.out.println("Sign In Response On INIT: " + response);
////            JSONObject obj = new JSONObject(response);
////            System.out.println(obj.getString("text").replace("\\", ""));
////
////            if (obj.getString("text").equals("Logged in")) {
////                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
////                startActivity(intent);
////            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (inputValidatorHelper.isNullOrEmpty(email.getText().toString())) {
                    email.setError("Email can not be empty");
                    butt.setEnabled(false);
                } else if (!inputValidatorHelper.isValidEmail(email.getText().toString())) {
                    email.setError("Email is not valid!");
                    butt.setEnabled(false);
                }
                else{
                    butt.setEnabled(true);
                }

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (inputValidatorHelper.isNullOrEmpty(password.getText().toString())) {
                    password.setError("Email can not be empty");
                    butt.setEnabled(false);
                }else{
                    butt.setEnabled(true);
                }
            }
        });

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject post_dict = new JSONObject();
                try {
                    post_dict.put("email", email.getText().toString());
                    post_dict.put("password", password.getText().toString());

                    HttpPostRequest postReq = new HttpPostRequest();

                    signinUrl = getResources().getString(R.string.server_url)+"signin";
                    String response = postReq.execute("POST", signinUrl, String.valueOf(post_dict)).get();
                    System.out.println("Sign In Response: " + response);
                    JSONObject obj = new JSONObject(response);
                    System.out.println(obj.getString("text").replace("\\", ""));

                    if (obj.getString("text").equals("Logged in")) {
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        gooButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }


    class InputValidatorHelper {
        public boolean isValidEmail(String string) {
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }


        public boolean isNullOrEmpty(String string) {
            return TextUtils.isEmpty(string);
        }

        public boolean isNumeric(String string) {
            return TextUtils.isDigitsOnly(string);
        }

        //Add more validators here if necessary
    }
}
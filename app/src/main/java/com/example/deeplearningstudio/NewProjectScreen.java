package com.example.deeplearningstudio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class NewProjectScreen extends AppCompatActivity {

    String createProjURL = "http://10.0.2.2:3000/createProject";

    EditText pName, pDesc;
    LinearLayout DL, ML;
    ImageView MLimg, DLimg;
    TextView dlText, mlText;
    Button next;
    private String newPID;

    String domain = "Deep Learning";

    Intent intent;

    InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_screen);

        pName = (EditText) findViewById(R.id.textProjName);
        pDesc = (EditText) findViewById(R.id.textProjDesc);
        DL = (LinearLayout) findViewById(R.id.optionDL);
        ML = (LinearLayout) findViewById(R.id.optionML);
        MLimg = (ImageView) findViewById(R.id.mlImage);
        DLimg = (ImageView) findViewById(R.id.dlImage);
        dlText = (TextView) findViewById(R.id.dlText);
        mlText = (TextView) findViewById(R.id.mlText);
        next = (Button) findViewById(R.id.nextButton);

        pName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (inputValidatorHelper.isNullOrEmpty(pName.getText().toString())) {
                    pName.setError("Name can not be empty");
                    next.setEnabled(false);
                }
                else if(inputValidatorHelper.isValidName(pName.getText().toString())){
                    pName.setError("Name is not valid");
                    next.setEnabled(false);
                }
                else {
                    next.setEnabled(true);
                }
            }
        });

        ML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ML.setBackgroundColor(getResources().getColor(R.color.active_color));

                DL.setBackgroundColor(getResources().getColor(R.color.white));

                DrawableCompat.setTint(MLimg.getDrawable(), getResources().getColor(R.color.primary_color));
                DrawableCompat.setTint(DLimg.getDrawable(), getResources().getColor(R.color.black));
                mlText.setTextColor(getResources().getColor(R.color.primary_color));
                dlText.setTextColor(getResources().getColor(R.color.black));

                domain = "Machine Learning";

            }
        });

        DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DL.setBackgroundColor(getResources().getColor(R.color.active_color));

                ML.setBackgroundColor(getResources().getColor(R.color.white));

                DrawableCompat.setTint(DLimg.getDrawable(), getResources().getColor(R.color.primary_color));
                DrawableCompat.setTint(MLimg.getDrawable(), getResources().getColor(R.color.black));
                mlText.setTextColor(getResources().getColor(R.color.black));
                dlText.setTextColor(getResources().getColor(R.color.primary_color));

                domain = "Deep Learning";

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject post_dict = new JSONObject();
                try {
                    post_dict.put("name", pName.getText().toString());
                    post_dict.put("description", pDesc.getText().toString());
                    post_dict.put("domain", domain);

                    HttpPostRequest postReq = new HttpPostRequest();

                    String response = postReq.execute(createProjURL, String.valueOf(post_dict)).get();
                    System.out.println("Create Proj Response: " + response);
                    JSONObject obj = new JSONObject(response);
                    try {
                        newPID = obj.getString("projectID");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent =new Intent(getApplicationContext(), BottomNavigationMenu.class);
                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("projectID", newPID);

                //Add the bundle to the intent
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

}


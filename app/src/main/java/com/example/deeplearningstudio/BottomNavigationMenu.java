package com.example.deeplearningstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("projectID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProjectDescriptionFragment()).commit();
        }
        System.out.println(stuff);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_description:
                            selectedFragment = new ProjectDescriptionFragment();
                            break;
                        case R.id.bottom_nav_preprocess:
                            selectedFragment = new PreProcessFragment();
                            break;
                        case R.id.bottom_nav_visulaize:
                            selectedFragment = new VisualizationFragment();
                            break;
                        case R.id.bottom_nav_model:
                            selectedFragment = new ModelFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
package com.example.deeplearningstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu extends AppCompatActivity {

    String ID = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("projectID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PreProcessFragment(ID)).commit();
        }
        System.out.println(ID);
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
                            selectedFragment = new PreProcessFragment(ID);
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
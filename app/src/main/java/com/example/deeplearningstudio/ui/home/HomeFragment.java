package com.example.deeplearningstudio.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deeplearningstudio.AdopterListView;
import com.example.deeplearningstudio.BottomNavigationMenu;
import com.example.deeplearningstudio.ProjectDescriptionFragment;
import com.example.deeplearningstudio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    AdopterListView adapter;
    FloatingActionButton floatingActionButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
//recycler
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerListProjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdopterListView();
        recyclerView.setAdapter(adapter);
        floatingActionButton= root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), BottomNavigationMenu.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
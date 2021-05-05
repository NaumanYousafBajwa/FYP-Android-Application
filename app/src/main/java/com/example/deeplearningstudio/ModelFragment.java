package com.example.deeplearningstudio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ModelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModelFragment newInstance(String param1, String param2) {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private static final String[] batchSizes = {"16", "32", "64","128","256","512"};
    private static final String[] lossFunctions = {"Binary Crossentropy", "Categorical Crossentropy ", "Sparse Categorical Crossentropy","Mean Absolute Error","Mean Absolute Percentage Error","Mean Squared Error", "Mean Squared Logarithmic Error"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner = view.findViewById(R.id.batchSizeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,batchSizes);

        Spinner secondSpinner = view.findViewById(R.id.lossFuncList);
        ArrayAdapter<String> lossAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,lossFunctions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        secondSpinner.setAdapter(lossAdapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        secondSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
package com.example.deeplearningstudio;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreProcessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreProcessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String projectID = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PreProcessFragment(String projID) {
        // Required empty public constructor
        projectID = projID;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     */
    // TODO: Rename and change types and number of parameters
    public static void newInstance(String param1, String param2) {
//        PreProcessFragment fragment = new PreProcessFragment(projectID);
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = this.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.file_upload_popup, null);
        builder.setView(convertView)
                // Add action buttons
                .setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // edit the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_process, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recycler

        String url = getResources().getString(R.string.server_url)+"getDatasetStaictics?project="+projectID;
        System.out.println("Columns URL: "+url);

        HttpPostRequest postReq = new HttpPostRequest();
        String response = null;
        try {
            response = postReq.execute("GET", url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sign In Response: " + response);

        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerColumnList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ColumnsAdapter adapter = null;
        try {
            //JSONArray parsedData = parseColumnsData(new JSONObject(response));
            adapter = new ColumnsAdapter(new JSONObject(response));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }

    private JSONArray parseColumnsData(JSONObject response){
        try {
            JSONObject columns_data = response.getJSONObject("data_statistics");
            JSONObject preproc_opt = response.getJSONObject("preprocessing_options").getJSONObject("column_wise_options");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }
}
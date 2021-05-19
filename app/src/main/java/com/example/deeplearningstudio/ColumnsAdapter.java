package com.example.deeplearningstudio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ColumnsAdapter extends RecyclerView.Adapter<ColumnsAdapter.ViewHolder> {
    public JSONObject projData;
    public JSONArray columnsData;
    private String getPID;

    public ColumnsAdapter (JSONObject projectData) {
        projData = projectData;
        try {
            columnsData = projectData.getJSONArray("data_statistics");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.preprocessing_columns, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JSONArray temp = null;
        try {
            //temp = projData.getJSONArray("data_statistics");
            holder.colName.setText(columnsData.getJSONObject(position).getString("name"));
//            getPID = temp.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return projData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView colName;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.colName = itemView.findViewById(R.id.columnName);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());
//                    Intent intent = new Intent(cardView.getContext(), BottomNavigationMenu.class);
//
//                    //Create the bundle
//                    Bundle bundle = new Bundle();
//
//                    //Add your data to bundle
//                    bundle.putString("projectID", getPID);
//
//                    //Add the bundle to the intent
//                    intent.putExtras(bundle);
//                    cardView.getContext().startActivity(intent);
//                }
//            });
        }

        @Override
        public void onClick(View v) {

        }
    }

}

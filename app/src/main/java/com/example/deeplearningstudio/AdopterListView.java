package com.example.deeplearningstudio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdopterListView extends RecyclerView.Adapter<AdopterListView.ViewHolder> {
    private String tabTitles[]= new String[]{
            "Information",
            "Fee Structure",
            "Faculty",
            "Ratings & Reviews"
    };
    private String dis[]= new String[]{
            "Informfggfgation",
            "Fee Strfgfgucture",
            "Facufgflty",
            "Ratingsfgfg & Reviews"
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.projectName.setText(tabTitles[position]);
        holder.projectDes.setText(dis[position]);
    }

    @Override
    public int getItemCount() {
        return tabTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView projectDes,projectName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.projectDes=itemView.findViewById(R.id.projectDes);
            this.projectName=itemView.findViewById(R.id.projectName);

        }
    }
}

package com.example.theoldnerds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList comic_ID, comic_name, comic_descrip, comic_date, comic_category;


    CustomAdapter(Activity activity, Context context, ArrayList comic_ID,
                  ArrayList comic_name,
                  ArrayList comic_descrip,
                  ArrayList comic_date,
                  ArrayList comic_category) {
        this.activity = activity;
        this.context = context;
        this.comic_ID = comic_ID;
        this.comic_name = comic_name;
        this.comic_descrip = comic_descrip;
        this.comic_date = comic_date;
        this.comic_category = comic_category;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.comic_ID_txt.setText(String.valueOf(comic_ID.get(position)));
        holder.comic_name_txt.setText(String.valueOf(comic_name.get(position)));
        holder.comic_descrip_txt.setText(String.valueOf(comic_descrip.get(position)));
        holder.comic_date_txt.setText(String.valueOf(comic_date.get(position)));
        holder.comic_category_txt.setText(String.valueOf(comic_category.get(position)));
        holder.mainLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(comic_ID.get(position)));
                intent.putExtra("name", String.valueOf(comic_name.get(position)));
                intent.putExtra("description", String.valueOf(comic_descrip.get(position)));
                intent.putExtra("date", String.valueOf(comic_date.get(position)));
                intent.putExtra("category", String.valueOf(comic_category.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comic_ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView comic_ID_txt, comic_name_txt, comic_descrip_txt, comic_date_txt, comic_category_txt;
        ConstraintLayout mainLayouts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comic_ID_txt = itemView.findViewById(R.id.comicID);
            comic_name_txt = itemView.findViewById(R.id.TXTComicName);
            comic_descrip_txt = itemView.findViewById(R.id.comicDescrip);
            comic_date_txt = itemView.findViewById(R.id.comicDateOfAcqTXT);
            comic_category_txt = itemView.findViewById(R.id.comicCategory);
            mainLayouts = itemView.findViewById(R.id.mainLayout);
        }
    }
}

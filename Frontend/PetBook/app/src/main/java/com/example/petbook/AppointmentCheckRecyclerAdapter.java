package com.example.petbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppointmentCheckRecyclerAdapter extends RecyclerView.Adapter<AppointmentCheckRecyclerAdapter.MyViewHolder> {

    private List<AppointmentProfile> listData = new ArrayList<>();

    public AppointmentCheckRecyclerAdapter(List<AppointmentProfile> list) { this.listData = list; }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.hospital_name.setText(listData.get(position).getHospitalname());
        holder.pet_name.setText(listData.get(position).getPetname());
        holder.date.setText(String.valueOf(listData.get(position).getDate()));
        holder.operating_time.setText(String.valueOf(listData.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_appointment, parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView hospital_name;
        public TextView pet_name;
        public TextView date;
        public TextView operating_time;
        public final View mView;

        public MyViewHolder(View view){
            super(view);
            mView = view;
            hospital_name = (TextView)view.findViewById(R.id.hospital_name);
            pet_name = (TextView)view.findViewById(R.id.pet_name);
            date = (TextView)view.findViewById(R.id.date);
            operating_time = (TextView)view.findViewById(R.id.operating_time);
        }
    }
}

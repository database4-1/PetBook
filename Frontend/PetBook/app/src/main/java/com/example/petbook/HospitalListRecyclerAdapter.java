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

public class HospitalListRecyclerAdapter extends RecyclerView.Adapter<HospitalListRecyclerAdapter.MyViewHolder> {

    private List<HospitalProfile> listData = new ArrayList<>();

    public HospitalListRecyclerAdapter(List<HospitalProfile> list) { this.listData = list; }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.hospital_name.setText(listData.get(position).getName());
        holder.address.setText(listData.get(position).getAddressDetail());
        holder.operating_time.setText(String.valueOf(listData.get(position).getStartTime()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginData.getInstance().setHospitalID(listData.get(position).getHospitalID());

                Intent intent = new Intent(v.getContext(), HospitalInfoPopupActivity.class);
                intent.putExtra("hospitalID",listData.get(position).getHospitalID());
                intent.putExtra("name",listData.get(position).getName());
                intent.putExtra("phone", listData.get(position).getPhone());
                intent.putExtra("addressDo", listData.get(position).getAddressDo());
                intent.putExtra("addressSi",listData.get(position).getAddressSi());
                intent.putExtra("addressGu",listData.get(position).getAddressGu());
                intent.putExtra("addressDetail",listData.get(position).getAddressDetail());
                intent.putExtra("startTime", String.valueOf(listData.get(position).getStartTime()));
                intent.putExtra("endTime", String.valueOf(listData.get(position).getEndTime()));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_hospital_list, parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView hospital_name;
        public TextView address;
        public TextView operating_time;
        public final View mView;

        public MyViewHolder(View view){
            super(view);
            mView = view;
            hospital_name = (TextView)view.findViewById(R.id.hospital_name);
            address = (TextView)view.findViewById(R.id.address);
            operating_time = (TextView)view.findViewById(R.id.operating_time);
        }
    }
}

package com.example.petbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PetListRecyclerAdapter extends RecyclerView.Adapter<PetListRecyclerAdapter.MyViewHolder>{

    private List<PetProfile> listData = new ArrayList<>();
    private String hospitalID;

    public PetListRecyclerAdapter(List<PetProfile> list, String hospitalID) { this.listData = list; this.hospitalID =hospitalID; }

    @Override
    public void onBindViewHolder(@NonNull PetListRecyclerAdapter.MyViewHolder holder, final int position) {

        holder.pet_name.setText(listData.get(position).getName());
        holder.species.setText(listData.get(position).getSpecies());
        holder.speciesOfSpecies.setText(listData.get(position).getSpeciesOfSpecies());
        holder.gender.setText(listData.get(position).getGender());
        holder.age.setText(String.valueOf(listData.get(position).getAge()));
        holder.weight.setText(String.valueOf(listData.get(position).getWeight()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LoginData.getInstance().getTmp() == 2) {
                    Intent intent = new Intent(v.getContext(), HospitalAppointmentActivity.class);

                    LoginData.getInstance().setPetID(listData.get(position).getPetID());

                    v.getContext().startActivity(intent);
                }
                else {
                    Intent intent = new Intent(v.getContext(), PetInfoPopupActivity.class);
                    LoginData.getInstance().setSpecies(listData.get(position).getSpeciesOfSpecies());
                    v.getContext().startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public PetListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pet_list, parent,false);
        return new PetListRecyclerAdapter.MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView pet_name;
        public TextView species;
        public TextView speciesOfSpecies;
        public TextView gender;
        public TextView age;
        public TextView weight;


        public final View mView;

        public MyViewHolder(View view){
            super(view);
            mView = view;
            pet_name = (TextView)view.findViewById(R.id.pet_name);
            species = (TextView)view.findViewById(R.id.species);
            speciesOfSpecies = (TextView)view.findViewById(R.id.speciesOfSpecies);
            gender = (TextView)view.findViewById(R.id.gender);
            age = (TextView)view.findViewById(R.id.age);
            weight = (TextView)view.findViewById(R.id.weight);
        }
    }
}

package com.codefellas.cargrab.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Passenger;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Passenger> passengers;

    public PassengerAdapter(Context context, ArrayList<Passenger> passengers) {
        this.context = context;
        this.passengers = passengers;
    }

    @NonNull
    @Override
    public PassengerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_passenger, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerAdapter.ViewHolder holder, int position) {
        Passenger passenger = passengers.get(position);
        holder.bind(passenger);
    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView name, email, phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.passenger_name);
            email = itemView.findViewById(R.id.passenger_email);
            phone = itemView.findViewById(R.id.passenger_phone);
        }

        public void bind(Passenger passenger) {
            name.setText(String.format("%s %s %s", passenger.getFirstName(), passenger.getMiddleName(), passenger.getLastName()));
            email.setText(passenger.getEmail());
            phone.setText(passenger.getPhone());
        }
    }
}
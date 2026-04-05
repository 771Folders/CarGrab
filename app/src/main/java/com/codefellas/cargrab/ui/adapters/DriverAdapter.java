package com.codefellas.cargrab.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Driver;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    public Context context;
    public ArrayList<Driver> drivers;
    public Listener listener;

    public interface Listener {
        void OnAcceptClick(int positon);
        void OnRejectClick(int position);
    }

    public DriverAdapter(Context context, Listener listener, ArrayList<Driver> drivers) {
        this.context = context;
        this.drivers = drivers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_driver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.ViewHolder holder, int position) {
        Driver driver = drivers.get(position);
        holder.bind(driver);
        holder.accept.setOnClickListener(view -> listener.OnAcceptClick(position));
        holder.reject.setOnClickListener(view -> listener.OnRejectClick(position));
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView name, phone, email, status;
        MaterialButton accept, reject;
        MaterialDivider divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.driver_name);
            phone = itemView.findViewById(R.id.driver_phone);
            email = itemView.findViewById(R.id.driver_email);
            status = itemView.findViewById(R.id.driver_status);
            accept = itemView.findViewById(R.id.driver_accept);
            reject = itemView.findViewById(R.id.driver_reject);
            divider = itemView.findViewById(R.id.driver_divider);
        }

        public void bind(Driver driver) {
            name.setText(String.format("%s %s %s", driver.getFirstName(), driver.getMiddleName(), driver.getLastName()));
            phone.setText(driver.getPhone());
            email.setText(driver.getEmail());
            status.setText(driver.getStatus());

            if (driver.getStatus().equals("Approved") || driver.getStatus().equals("Rejected")) {
                accept.setVisibility(View.GONE);
                reject.setVisibility(View.GONE);
                divider.setVisibility(View.GONE);
            }
        }
    }
}
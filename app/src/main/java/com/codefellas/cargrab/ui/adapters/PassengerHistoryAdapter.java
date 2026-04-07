package com.codefellas.cargrab.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Booking;
import com.codefellas.cargrab.data.Database;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class PassengerHistoryAdapter extends RecyclerView.Adapter<PassengerHistoryAdapter.ViewHolder>{

    Context context;
    ArrayList<Booking> bookings;
    Database db;

    public PassengerHistoryAdapter(Context context, ArrayList<Booking> bookings) {
        this.context = context;
        this.bookings = bookings;
        this.db = new Database(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.passenger_history_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.transacID.setText(booking.getBookingID());
        holder.pickupLoc.setText(booking.getPickupLocation());
        holder.destination.setText(booking.getDestination());
        holder.driver.setText(db.getDriverNameByID(booking.getDriverID()));
        holder.fare.setText(String.valueOf(booking.getEstimatedFare()));
        holder.rating.setRating(booking.getRating());
        holder.rate.setOnClickListener(v -> {
            //TODO: dialog for rating

        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView transacID, pickupLoc, destination, driver, fare;
        RatingBar rating;
        MaterialButton rate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transacID = itemView.findViewById(R.id.txtTransaction);
            pickupLoc = itemView.findViewById(R.id.pickupLocation);
            destination = itemView.findViewById(R.id.destinationLocation);
            driver = itemView.findViewById(R.id.driverName);
            fare = itemView.findViewById(R.id.estimatedFare);
            rating = itemView.findViewById(R.id.rateBar);
            rate = itemView.findViewById(R.id.rateButton);
        }

    }

}

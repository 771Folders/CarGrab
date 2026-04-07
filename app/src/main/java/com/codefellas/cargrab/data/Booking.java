package com.codefellas.cargrab.data;

public class Booking {
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getEstimatedFare() {
        return estimatedFare;
    }

    public void setEstimatedFare(float estimatedFare) {
        this.estimatedFare = estimatedFare;
    }

    private int bookingID;
    private int passengerID;
    private int driverID;
    private String pickupLocation;
    private String destination;
    private float rating;
    private float estimatedFare;
    public Booking() {
        this.bookingID = -1;
        this.passengerID = -1;
        this.driverID = -1;
        this.pickupLocation = null;
        this.destination = null;
        this.rating = 0;
        this.estimatedFare = 0;
    }
}

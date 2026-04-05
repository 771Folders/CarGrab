package com.codefellas.cargrab.data;

public class PassengerLocation {

    public int passengerLocationID;
    public int passengerID;
    public String locationTitle;
    public String location;

    public PassengerLocation(int passengerLocationID, int passengerID, String locationTitle, String location) {
        this.passengerLocationID = passengerLocationID;
        this.passengerID = passengerID;
        this.locationTitle = locationTitle;
        this.location = location;
    }

    public int getPassengerLocationID() {
        return passengerLocationID;
    }

    public void setPassengerLocationID(int passengerLocationID) {
        this.passengerLocationID = passengerLocationID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
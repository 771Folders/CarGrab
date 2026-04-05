package com.codefellas.cargrab.data;

public class Vehicle {

    public int vehicleID;
    public String vehicleModel;
    public String vehiclePlateNumber;

    public Vehicle(int vehicleID, String vehicleModel, String vehiclePlateNumber) {
        this.vehicleID = vehicleID;
        this.vehicleModel = vehicleModel;
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }
}
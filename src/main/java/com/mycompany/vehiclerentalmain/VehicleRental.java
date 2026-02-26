/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vehiclerentalmain;

abstract class VehicleRental {

    private String vehicleid;
    private String brand;
    private String model;
    private double rentalPricePerDay;
    private String availabilityStatus;

    private int year;
    private String color;
    private double mileage;  //in km

    public VehicleRental(String vehicleid, String brand, String model, double rentalPricePerDay, String availabilityStatus, int year, String color, double mileage) {
        this.vehicleid = vehicleid;
        this.brand = brand;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.availabilityStatus = "Available";
        this.year = year;
        this.color = color;
        this.mileage = mileage;

        if (vehicleid == null) {
            System.out.println("Vehicle ID cannot be empty!");
        }
        if (rentalPricePerDay <= 0) {
            System.out.println("Rental Price must be positive!");
        }
        if (year < 1990 || year > 2026) {
            System.out.println("Invalid Manufacture Year!");
        }
        if (mileage < 0) {
            System.out.println("Mileage cannot be negative!");
        }

    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setAvailabilityStatus(String status) {
        if (status.equals("Available") || status.equals("Rented")) {
            this.availabilityStatus = status;
        } else {
            System.out.println("Status must be available or rented!");
        }

    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMileage(double mileage) {
        if (mileage < this.mileage) {
            System.out.println("Mileage cannot decrease!");
        }

        this.mileage = mileage;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public double getMileage() {
        return mileage;
    }

    public abstract String getVehicleType();

    public abstract double calculateRentalCost();

    @Override
    public String toString() {
        return "VehicleRental{" + "vehicleid=" + vehicleid + ", brand=" + brand + ", model=" + model + ", rentalPricePerDay=" + rentalPricePerDay + ", availabilityStatus=" + availabilityStatus + ", year=" + year + ", color=" + color + ", mileage=" + mileage + '}';
    }

}

class Car extends VehicleRental {

    private int numberOfdoors;
    private String feulType;
    private String transmissionType;
    private String passengerType; //suv,sedan...

    public Car(int numberOfdoors, String feulType, String transmissionType, String passengerType, String vehicleid, String brand, String model, double rentalPricePerDay, String availabilityStatus, int year, String color, double mileage) {
        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);
        this.numberOfdoors = numberOfdoors;
        this.feulType = feulType;
        this.transmissionType = transmissionType;
        this.passengerType = passengerType;

        if (numberOfdoors < 2 || numberOfdoors > 4) {
            System.out.println("Invalid number of doors, must be between 2 and 4");
        }

        if (feulType != "Petrol" && feulType != "Diesel" && feulType != "Electric" && feulType != "Hybrid") {
            System.out.println("Feul type must be Petrol, Diesel, Electric, Hybrid!");
        }

    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    public double calculateRentalCost(int days) {
        double cost = getRentalPricePerDay() * days;
        return cost;
    }

    @Override
    public String toString() {
        return "Car{" + "numberOfdoors=" + numberOfdoors + ", feulType=" + feulType + ", transmissionType=" + transmissionType + ", passengerType=" + passengerType + '}';
    }
}

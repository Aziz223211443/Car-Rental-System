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

        if (vehicleid == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty!");
        }
        if (rentalPricePerDay <= 0) {
            throw new IllegalArgumentException("Rental Price must be positive!");
        }
        if (year < 1990 || year > 2026) {
            throw new IllegalArgumentException("Invalid Manufacture Year!");
        }
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative!");
        }
        this.vehicleid = vehicleid;
        this.brand = brand;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.availabilityStatus = "Available";
        this.year = year;
        this.color = color;
        this.mileage = mileage;
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
            throw new IllegalArgumentException("Status must be available or rented!");
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
            throw new IllegalArgumentException("Mileage cannot decrease!");
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

    public abstract double calculateRentalCost(int days);

    @Override
    public String toString() {
        return "VehicleRental{" + "vehicleid=" + vehicleid + ", brand=" + brand + ", model=" + model + ", rentalPricePerDay=" + rentalPricePerDay + ", availabilityStatus=" + availabilityStatus + ", year=" + year + ", color=" + color + ", mileage=" + mileage + '}';
    }

}

class Car extends VehicleRental {

    private int numberOfdoors;
    private String fuelType;
    private String transmissionType;
    private String passengerType; //suv,sedan...

    public Car(int numberOfdoors, String fuelType, String transmissionType, String passengerType, String vehicleid, String brand, String model, double rentalPricePerDay,
            String availabilityStatus, int year, String color, double mileage) {

        if (numberOfdoors < 2 || numberOfdoors > 4) {
            throw new IllegalArgumentException("Invalid number of doors, must be between 2 and 4");
        }

        if (!fuelType.equalsIgnoreCase("petrol") && !fuelType.equalsIgnoreCase("Deisel") && !fuelType.equalsIgnoreCase("Electric")) {
            throw new IllegalArgumentException("Feul type must be Petrol, Diesel or Electric!");
        }

        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);

        this.numberOfdoors = numberOfdoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.passengerType = passengerType;

    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = getRentalPricePerDay() * days;
        return cost;
    }

    @Override
    public String toString() {
        return "Car{" + "numberOfdoors=" + numberOfdoors + ", fuelType=" + fuelType + ", transmissionType=" + transmissionType + ", passengerType=" + passengerType + '}';
    }

}

class Truck extends VehicleRental {

    private double loadCapacity;
    private String truckType;
    private double height;

    public Truck(double loadCapacity, String truckType, double height, String vehicleid, String brand, String model,
            double rentalPricePerDay, String availabilityStatus, int year, String color, double mileage) {

        if (loadCapacity <= 0 || loadCapacity > 40) {
            throw new IllegalArgumentException("loadCapacity must be between 0 and 40 tons");
        }
        if (height <= 0 || height > 4.5) {
            throw new IllegalArgumentException("height must be between 0 and 4.5 meters!");
        }
        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);

        this.loadCapacity = loadCapacity;
        this.truckType = truckType;
        this.height = height;

    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = getRentalPricePerDay() * days;
        cost += loadCapacity * 2 * days;
        return cost;
    }
}
class Bike extends VehicleRental {

    private int engineCapacity; // if gas
    private String bikeType;
    private String engineType; //gas or electric
    private boolean hasGear;

    public Bike(int engineCapacity, String bikeType, String engineType, boolean hasGear, String vehicleid, String brand, String model, double rentalPricePerDay, String availabilityStatus, int year, String color, double mileage) {
        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);

        if (engineCapacity < 50 || engineCapacity > 2500) {
            throw new IllegalArgumentException("Engine capacity must be between 50 and 2500 cc!");
        }
        if (!engineType.equalsIgnoreCase("Gas") && !engineType.equalsIgnoreCase("Electric")) {
            throw new IllegalArgumentException("Engine type must be Gas or Electric");
        }
         if(!bikeType.equalsIgnoreCase("Sport") && !bikeType.equalsIgnoreCase("Cruiser") && !bikeType.equalsIgnoreCase("Roadster")){
            throw new IllegalArgumentException("Bike type must be Sport, Cruiser or Roadster!");
        }

        this.engineCapacity = engineCapacity;
        this.bikeType = bikeType;
        this.engineType = engineType;
        this.hasGear = hasGear;
    }

    @Override
    public String getVehicleType() {
       return "Bike";
    }

    @Override
    public double calculateRentalCost(int days) {
       double cost = getRentalPricePerDay() * days;
       
       if(engineCapacity > 600) cost *= 1.2;
       if(bikeType.equalsIgnoreCase("Sport")) cost *= 1.15;
       return cost;
    }

    @Override
    public String toString() {
        return "Bike{" + "engineCapacity=" + engineCapacity + ", bikeType=" + bikeType + ", engineType=" + engineType + ", hasGear=" + hasGear + '}';
    }

}



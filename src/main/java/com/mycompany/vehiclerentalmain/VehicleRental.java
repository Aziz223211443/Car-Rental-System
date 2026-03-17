/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vehiclerentalmain;

import java.util.ArrayList;

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
        
        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);

        if (numberOfdoors < 2 || numberOfdoors > 4) {
            throw new IllegalArgumentException("Invalid number of doors, must be between 2 and 4");
        }

        if (!fuelType.equalsIgnoreCase("petrol") && !fuelType.equalsIgnoreCase("Deisel") && !fuelType.equalsIgnoreCase("Electric")) {
            throw new IllegalArgumentException("Feul type must be Petrol, Diesel or Electric!");
        }


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

        super(vehicleid, brand, model, rentalPricePerDay, availabilityStatus, year, color, mileage);
        
        if (loadCapacity <= 0 || loadCapacity > 40) {
            throw new IllegalArgumentException("loadCapacity must be between 0 and 40 tons");
        }
        if (height <= 0 || height > 4.5) {
            throw new IllegalArgumentException("height must be between 0 and 4.5 meters!");
        }

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
    //now here is the beginning of R3 ⬇️
     class VehicleRentalSystem
{
    private ArrayList<VehicleRental> vehicles;
    private ArrayList<RentalRecords> records;
    public VehicleRentalSystem() 
    {
        this.vehicles = new ArrayList<>();
        this.records = new ArrayList<>();       // <-------- in R4 we will add the records.add method
    }
    public void addVehicle(VehicleRental v)
    {
        vehicles.add(v);
    }
    
    public void viewAllVehciels()
    {
        if(vehicles.size() == 0 )
        {
            System.out.println("There are no vehicles add yet");
        return;
        }
        for(int i = 0; i < vehicles.size(); i++)
        {
            System.out.println(vehicles.get(i));
        }
    }
    public void viewAllAvailableCars()
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getAvailabilityStatus().equalsIgnoreCase("Available"))
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no available cars");
    }
    
    public void viewRentedCars()
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getAvailabilityStatus().equalsIgnoreCase("Rented"))
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no rented cars");
    }
    
    public void filterByPrice(double price)
    {
        boolean found = false;
        for(int i = 0; i< vehicles.size(); i++)
        {
            if(vehicles.get(i).getRentalPricePerDay() <= price)
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no cars by this price");
    }
    
    public void filterbyBrand(String brand)
    {
        boolean found = false;
        for(int i = 0; i< vehicles.size(); i++)
        {
            if(vehicles.get(i).getBrand().equalsIgnoreCase(brand))
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no cars by this brand");
    }
    
    public void filterbyYear(int year)
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getYear() == year)
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no cars by this year");
    }
    
    public void fitlerBycolor(String color)
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getColor().equalsIgnoreCase(color))
            {
                System.out.println(vehicles.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no cars by this color");
    }
    
    public void viewAllRecords()
    {
        if(records.size() == 0)
        {
            System.out.println("There are no rental records added yet.");
        return;
        }
        for(int i = 0; i < records.size(); i++)
        {
            System.out.println(records.get(i));
        }
    }
    
    public void filterByCustomerName(String name)
    {
        boolean found = false;
        for( int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getCustomerName().equalsIgnoreCase(name))
            {
                System.out.println(records.get(i));
            found = true;
            }
        }
        if(found == false)
            System.out.println("There are no customer by this name.");
    }
    
    public void filterByActiveStatus(boolean status)
    {
        boolean found = false;
        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).isActive() == status)
            {
                System.out.println(records.get(i));
                found = true;
            }
        }
        
        if(found == false)
            System.out.println("There are no records that are active");
    }
    
    public void filterByRentalDays(int days)
    {
        boolean found = false;
        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getRentalDays() == days)
            {
                System.out.println(records.get(i));
                found = true;
            }
            
        }
        if(found == false)
            System.out.println("There are no records by this number of days.");
    }
}

    
    // RentalRecords class is the R6 ⬇️  <---------
 class RentalRecords 
{
    private String recordID;
    private final VehicleRental vehicle;
    private String customerName;
    private int rentalDays;
    private final double totalCost;
    private boolean active;

    public RentalRecords(String recordID, VehicleRental vehicle, String customerName, int rentalDays, double totalCost, boolean active) 
    {
        if(recordID == null)
            throw new IllegalArgumentException("The recordID cannot be null.");
        else this.recordID = recordID;
        
        this.vehicle = vehicle;
        
        if(customerName == null || customerName.isBlank())
            throw new IllegalArgumentException("The customer name cannot be empty.");
        else this.customerName = customerName;
        
        if(rentalDays <= 0)
            throw new IllegalArgumentException("The rental days must be more than zero.");
        else this.rentalDays = rentalDays;
        
        this.totalCost = totalCost;
        this.active = active;
    }

    public String getRecordID() {
        return recordID;
    }

    public VehicleRental getVehicle() {
        return vehicle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() 
    {
        return "Rental Record\n" +
           "---------------------\n" +
           "Record ID: " + recordID + "\n" +
           "Vehicle ID: " + vehicle.getVehicleid() + "\n" +
           "Customer Name: " + customerName + "\n" +
           "Rental Days: " + rentalDays + "\n" +
           "Total Cost: " + totalCost + "\n" +
           "Active?: " + active + "\n";
    }
    
}




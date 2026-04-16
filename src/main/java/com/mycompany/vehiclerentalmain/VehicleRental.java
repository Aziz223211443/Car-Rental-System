/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vehiclerentalmain;

import java.util.ArrayList;
import java.util.Scanner;

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
        return super.toString() + "VehicleRental{" + "vehicleid=" + vehicleid + ", brand=" + brand + ", model=" + model + ", rentalPricePerDay=" + rentalPricePerDay + ", availabilityStatus=" + availabilityStatus + ", year=" + year + ", color=" + color + ", mileage=" + mileage + '}';
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

        if (!fuelType.equalsIgnoreCase("petrol") && !fuelType.equalsIgnoreCase("Diesel") && !fuelType.equalsIgnoreCase("Electric")) {
            throw new IllegalArgumentException("Fuel type must be Petrol, Diesel or Electric!");
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
        return  "Car{" + "numberOfdoors=" + numberOfdoors + ", fuelType=" + fuelType + ", transmissionType=" + transmissionType + ", passengerType=" + passengerType + '}';
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

    @Override
    public String toString() {
        return  "Truck{" + "loadCapacity=" + loadCapacity + ", truckType=" + truckType + ", height=" + height + '}';
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
        if (!bikeType.equalsIgnoreCase("Sport") && !bikeType.equalsIgnoreCase("Cruiser") && !bikeType.equalsIgnoreCase("Roadster")) {
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

        if (engineCapacity > 600) {
            cost *= 1.2;
        }
        if (bikeType.equalsIgnoreCase("Sport")) {
            cost *= 1.15;
        }
        return cost;
    }

    @Override
    public String toString() {
        return  "Bike{" + "engineCapacity=" + engineCapacity + ", bikeType=" + bikeType + ", engineType=" + engineType + ", hasGear=" + hasGear + '}';
    }
}
       //R3️

class VehicleRentalSystem {

    ArrayList<VehicleRental> vehicles;
    private ArrayList<RentalRecords> records;

    public VehicleRentalSystem() {
        this.vehicles = new ArrayList<>();
        this.records = new ArrayList<>();

    }

    public ArrayList<VehicleRental> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public void addVehicle(VehicleRental v) {
        vehicles.add(v);
    }

    public void viewAllVehciels() {
        if (vehicles.size() == 0) {
            System.out.println("There are no vehicles add yet");
            return;
        }
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println(vehicles.get(i));
        }
    }

    public void viewAllAvailableCars() {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getAvailabilityStatus().equalsIgnoreCase("Available")) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no available cars");
        }
    }

    public void viewRentedCars() {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getAvailabilityStatus().equalsIgnoreCase("Rented")) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no rented cars");
        }
    }

    public void filterByPrice(double price) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getRentalPricePerDay() <= price) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no cars by this price");
        }
    }

    public void filterbyBrand(String brand) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getBrand().equalsIgnoreCase(brand)) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no cars by this brand");
        }
    }

    public void filterbyYear(int year) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getYear() == year) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no cars by this year");
        }
    }

    public void fitlerBycolor(String color) {
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getColor().equalsIgnoreCase(color)) {
                System.out.println(vehicles.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no cars by this color");
        }
    }

    public void viewAllRecords() {
        if (records.isEmpty()) {
            System.out.println("There are no rental records added yet.");
            return;
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }
    }

    public void filterByCustomerName(String name) {
        boolean found = false;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getCustomerName().equalsIgnoreCase(name)) {
                System.out.println(records.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no customer by this name.");
        }
    }

    public void filterByActiveStatus(boolean status) {
        boolean found = false;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).isActive() == status) {
                System.out.println(records.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no records that are active");
        }
    }

    public void filterByRentalDays(int days) {
        boolean found = false;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getRentalDays() == days) {
                System.out.println(records.get(i));
                found = true;
            }

        }
        if (!found) {
            System.out.println("There are no records by this number of days.");
        }

    }
}

// R6
class RentalRecords {

    private String recordID;
    private final VehicleRental vehicle;
    private String customerName;
    private int rentalDays;
    private final double totalCost;
    private boolean active;

    public RentalRecords(String recordID, VehicleRental vehicle, String customerName, int rentalDays, double totalCost, boolean active) {
        if (recordID == null) {
            throw new IllegalArgumentException("The recordID cannot be null.");
        } else {
            this.recordID = recordID;
        }

        this.vehicle = vehicle;

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("The customer name cannot be empty.");
        } else {
            this.customerName = customerName;
        }

        if (rentalDays <= 0) {
            throw new IllegalArgumentException("The rental days must be more than zero.");
        } else {
            this.rentalDays = rentalDays;
        }

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
    public String toString() {
        return "Rental Record\n"
                + "---------------------\n"
                + "Record ID: " + recordID + "\n"
                + "Vehicle ID: " + vehicle.getVehicleid() + "\n"
                + "Customer Name: " + customerName + "\n"
                + "Rental Days: " + rentalDays + "\n"
                + "Total Cost: " + totalCost + "\n"
                + "Active?: " + active + "\n";
    }

    // method to rent a vehicle (R4)
    public static void rentVehicle(ArrayList<VehicleRental> vehicles, String id) {

        for (int i = 0; i < vehicles.size(); i++) {

            VehicleRental v = vehicles.get(i);

            if (v.getVehicleid().equals(id)) {

                if (v.getAvailabilityStatus().equals("Available")) {

                    v.setAvailabilityStatus("Rented");
                    System.out.println("Vehicle rented successfully!");

                } else {
                    System.out.println("Vehicle is already rented!");
                }

                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

    // method to return a vehicle (R5)
    public static void returnVehicle(ArrayList<VehicleRental> vehicles, String id) {

        for (int i = 0; i < vehicles.size(); i++) {

            VehicleRental v = vehicles.get(i);

            if (v.getVehicleid().equals(id)) {

                if (v.getAvailabilityStatus().equals("Rented")) {

                    v.setAvailabilityStatus("Available");
                    System.out.println("Vehicle returned successfully!");

                } else {
                    System.out.println("Vehicle is already available!");
                }

                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

}

public class VehicleRentalMain {

    public static void main(String[] args) {

        VehicleRentalSystem rentalSystem = new VehicleRentalSystem();
        Scanner scanner = new Scanner(System.in);

        
        // sample vehicles
        Car car1 = new Car(4, "Petrol", "Automatic", "Sedan", "C001", "Toyota", "Camry", 50, "Available", 2022, "White", 15000);
        Car car2 = new Car(2, "Diesel", "Manual", "Coupe", "C002", "Honda", "Civic", 45, "Available", 2021, "Black", 20000);
        Truck truck1 = new Truck(5, "Box", 3.5, "T001", "Ford", "F-150", 80, "Available", 2020, "Blue", 30000);
        Bike bike1 = new Bike(600, "Sport", "Gas", true, "B001", "Yamaha", "R6", 60, "Available", 2023, "Red", 5000);

        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(car2);
        rentalSystem.addVehicle(truck1);
        rentalSystem.addVehicle(bike1);

        // R8 menue
        while (true) {

            System.out.println("\n=========================================");
            System.out.println("      VEHICLE RENTAL SYSTEM MENU");
            System.out.println("=========================================");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. View All Rental Records");
            System.out.println("6. Exit");
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- ADD NEW VEHICLE ---");
                    System.out.print("Enter Vehicle ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Price per Day: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Mileage: ");
                    double mileage = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Type (Car/Truck/Bike): ");
                    String type = scanner.nextLine();

                    try {
                        if (type.equalsIgnoreCase("Car")) {
                            System.out.print("Enter Number of Doors: ");
                            int doors = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Fuel Type (Petrol/Diesel/Electric): ");
                            String fuel = scanner.nextLine();
                            System.out.print("Enter Transmission (Manual/Automatic): ");
                            String trans = scanner.nextLine();
                            System.out.print("Enter Passenger Type (Sedan/SUV/Coupe): ");
                            String passType = scanner.nextLine();
                            Car newCar = new Car(doors, fuel, trans, passType, id, brand, model, price, "Available", year, color, mileage);
                            rentalSystem.addVehicle(newCar);
                            System.out.println("Car added successfully!");
                        } else if (type.equalsIgnoreCase("Truck")) {
                            System.out.print("Enter Load Capacity (tons): ");
                            double load = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Enter Truck Type: ");
                            String truckType = scanner.nextLine();
                            System.out.print("Enter Height (meters): ");
                            double height = scanner.nextDouble();
                            scanner.nextLine();
                            Truck newTruck = new Truck(load, truckType, height, id, brand, model, price, "Available", year, color, mileage);
                            rentalSystem.addVehicle(newTruck);
                            System.out.println("Truck added successfully!");
                        } else if (type.equalsIgnoreCase("Bike")) {
                            System.out.print("Enter Engine Capacity (cc): ");
                            int engine = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Bike Type (Sport/Cruiser/Roadster): ");
                            String bikeType = scanner.nextLine();
                            System.out.print("Enter Engine Type (Gas/Electric): ");
                            String engineType = scanner.nextLine();
                            System.out.print("Has Gear? (true/false): ");
                            boolean hasGear = scanner.nextBoolean();
                            scanner.nextLine();
                            Bike newBike = new Bike(engine, bikeType, engineType, hasGear, id, brand, model, price, "Available", year, color, mileage);
                            rentalSystem.addVehicle(newBike);
                            System.out.println("Bike added successfully!");
                        } else {
                            System.out.println("Invalid vehicle type!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    rentalSystem.viewAllVehciels();
                    break;

                case 3:
                    System.out.println("\n--- RENT VEHICLE ---");
                    rentalSystem.viewAllAvailableCars();
                    System.out.print("Enter Vehicle ID to rent: ");
                    String rentId = scanner.nextLine();
                    RentalRecords.rentVehicle(rentalSystem.getVehicles(), rentId);
                    break;

                case 4:
                    System.out.println("\n--- RETURN VEHICLE ---");
                    rentalSystem.viewRentedCars();
                    System.out.print("Enter Vehicle ID to return: ");
                    String returnId = scanner.nextLine();
                    RentalRecords.returnVehicle(rentalSystem.getVehicles(), returnId);
                    break;

                case 5:
                    rentalSystem.viewAllRecords();
                    break;

                case 6:
                    System.out.println("Thank you for using Vehicle Rental System!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1-6.");
            }
        }
    }
}


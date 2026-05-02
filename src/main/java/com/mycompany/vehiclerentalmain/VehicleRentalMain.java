/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vehiclerentalmain;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        return  "\n{Vehicle ID= " + vehicleid + ", Brand= " + brand + ", Model=" + model + ", Rental Price Per Day= " + rentalPricePerDay + ",\n Availability Status= " + availabilityStatus + ", Year= " + year + ", Color= " + color + ", Mileage= " + mileage;
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
        return super.toString() + ", \nNumber of doors= " + numberOfdoors + ", Feul Type= " + fuelType + ", Transmission Type= " + transmissionType + ", Passenger Type= " + passengerType + "} <-- Car";
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
        return super.toString() + ", \nLoad Capacity= " + loadCapacity + ", Truck Type= " + truckType + ", Height= " + height + "} <--- Truck";
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
        return super.toString() + ", \nEngine Capacity= " + engineCapacity + ", Bike Type= " + bikeType + ", Engine Type= " + engineType + ", Has Gear= " + hasGear + "} <--- Bike";
    }
}

       //R3️ on Fayadh 
class VehicleRentalSystem {

    
    ArrayList<VehicleRental> vehicles;
    private ArrayList<RentalRecords> records;
    private int recordCounter = 1;
    private int carCounter = 1;
    private int truckCounter = 1;
    private int bikeCounter = 1;
    
    public VehicleRentalSystem() {
        this.vehicles = new ArrayList<>();
        this.records = new ArrayList<>();

    }

    public ArrayList<RentalRecords> getRecords() {
        return records;
    }
    
    public String generateRecordID() 
    {
        String id;
        
        if (recordCounter < 10)
        id = "R00" + recordCounter;
    else if (recordCounter < 100)
        id = "R0" + recordCounter;
    else
        id = "R" + recordCounter;
        
        recordCounter ++;
        return id;
    }
    
    public String generateVehicleID(String type)
    {
        String id = "";

        if (type.equalsIgnoreCase("Car"))
        {
            if (carCounter < 10)
                id = "C00" + carCounter;
            else if (carCounter < 100)
                id = "C0" + carCounter;
            else
                id = "C" + carCounter;

            carCounter++;
        }
        else if (type.equalsIgnoreCase("Truck"))
        {
            if (truckCounter < 10)
                id = "T00" + truckCounter;
            else if (truckCounter < 100)
                id = "T0" + truckCounter;
            else
                id = "T" + truckCounter;

            truckCounter++;
        }
        else if (type.equalsIgnoreCase("Bike"))
        {
            if (bikeCounter < 10)
                id = "B00" + bikeCounter;
            else if (bikeCounter < 100)
                id = "B0" + bikeCounter;
            else
                id = "B" + bikeCounter;

            bikeCounter++;
        }

        return id;
    }

    public ArrayList<VehicleRental> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public void addVehicle(VehicleRental v) {
        vehicles.add(v);
    }
    
    public void addRecord(RentalRecords r) {
        records.add(r);
    }

    public void viewAllVehicles() {
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

    public void filterBycolor(String color) {
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

// R6 on Fayadh
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
        return "\nRental Record\n"
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- ADD NEW VEHICLE ---");

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
                            String id = rentalSystem.generateVehicleID("Car");
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
                            System.out.println("Car added successfully! ID: " + id);
                        } else if (type.equalsIgnoreCase("Truck")) {
                            String id = rentalSystem.generateVehicleID("Truck");
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
                            System.out.println("Truck added successfully! ID: " + id);
                        } else if (type.equalsIgnoreCase("Bike")) {
                            String id = rentalSystem.generateVehicleID("Bike");
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
                            System.out.println("Bike added successfully! ID: " + id);
                        } else {
                            System.out.println("Invalid vehicle type!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                    //case 2 on Fayadh
                case 2:
                    while (true) 
                    {                        
                        System.out.println("\n--- VEHICLE OPTIONS ---");
                        System.out.println("1. View All Vehicles");
                        System.out.println("2. View Available Vehicles");
                        System.out.println("3. View Rented Vehicles");
                        System.out.println("4. Filter by Price");
                        System.out.println("5. Filter by Brand");
                        System.out.println("6. Filter by Year");
                        System.out.println("7. Filter by Color");
                        System.out.println("8. Back to Main Menu");
                        
                        System.out.print("Enter your choice: ");
                        int vChoice;
                        try 
                        {
                            vChoice = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException e) 
                        {
                            
                            System.out.print("Invalid input! Please enter a number.: ");
                            scanner.nextLine();
                            continue;
                        }
                        
                        switch (vChoice) {
                            case 1:
                                rentalSystem.viewAllVehicles(); break;
                                
                            case 2:
                                rentalSystem.viewAllAvailableCars(); break;
                            
                            case 3: 
                                rentalSystem.viewRentedCars(); break;
                            
                            case 4: 
                                System.out.print("Enter maximum price: ");
                                double vPrice = scanner.nextDouble();
                                scanner.nextLine();
                                rentalSystem.filterByPrice(vPrice);
                                break;
                                
                            case 5: 
                                System.out.print("Enter the brand: ");
                                String vBrand = scanner.nextLine();
                                rentalSystem.filterbyBrand(vBrand);
                                break;
                                
                            case 6: 
                                System.out.print("Enter the year: ");
                                int vYear = scanner.nextInt();
                                scanner.nextLine();
                                rentalSystem.filterbyYear(vYear);
                                break;
                                
                            case 7: 
                                System.out.print("Enter the color: ");
                                String vColor = scanner.nextLine();
                                rentalSystem.filterBycolor(vColor);
                                break;
                                
                            case 8:
                                break;
                                
                                
                                
                            default:
                                System.out.println("Invalid choice.");     
                                continue;
                        }
                        if(vChoice == 8)
                            break;
                    }

                    break;

                case 3:
                    System.out.println("\n--- RENT VEHICLE ---");
                    rentalSystem.viewAllAvailableCars();
                    System.out.print("\nEnter Vehicle ID to rent: ");
                    String rentId = scanner.nextLine();
                    
                    boolean found = false;
                    for(int i = 0; i < rentalSystem.getVehicles().size(); i++)
                    {
                         VehicleRental v = rentalSystem.vehicles.get(i);
                         
                         if(v.getVehicleid().equalsIgnoreCase(rentId))
                                 {
                                    if(v.getAvailabilityStatus().equalsIgnoreCase("Available"))
                                    {
                                        v.setAvailabilityStatus("Rented");
                             
                                        System.out.print("Enter Customer Name: ");
                                        String customer = scanner.nextLine();
                             
                                        System.out.print("Enter Rental days: ");
                                        int days = scanner.nextInt();
                                        scanner.nextLine();
                                        
                                        double cost = v.calculateRentalCost(days);
                                        
                                        RentalRecords record = new RentalRecords((rentalSystem.generateRecordID()), v, customer, days, cost, true);
                                        
                                        rentalSystem.addRecord(record);
                                        
                                        System.out.println("Vehicle has been rented successfully");
                                        found = true;
                                    }   
                                    
                                    else
                                    {
                                        System.out.println("Vehicle is already rented.");
                                    }
                                    
                                    break;
                                    
                                }
                    }
                    if(!found) 
                    {
                        System.out.println("Vehicle was not found.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- RETURN VEHICLE ---");
                    rentalSystem.viewRentedCars();

                    System.out.print("Enter Vehicle ID to return: ");
                    String returnId = scanner.nextLine();

                    boolean founde = false;

                    for (int i = 0; i < rentalSystem.getVehicles().size(); i++) 
                    {

                         VehicleRental v = rentalSystem.vehicles.get(i);

                        if (v.getVehicleid().equalsIgnoreCase(returnId)) 
                        {

                             if (v.getAvailabilityStatus().equalsIgnoreCase("Rented")) 
                             {

                                v.setAvailabilityStatus("Available");

                                for (int j = 0; j < rentalSystem.getRecords().size(); j++) 
                                {

                                    RentalRecords r = rentalSystem.getRecords().get(j);

                                    if (r.getVehicle().getVehicleid().equalsIgnoreCase(returnId) && r.isActive()) 
                                    {
                                        r.setActive(false);
                                        System.out.println("\n--- RENTAL SUMMARY ---");
                                        System.out.println(r.toString());
                                        System.out.println("Rental record closed successfully!");
                                        break;
                                    }
                                }
                                

                                System.out.println("Vehicle returned successfully!");
                                founde = true;

                            } else 
                             {
                                System.out.println("Vehicle is already available.");
                                founde = true;
                             }
                             
                            break;
                        }
                    }

                    if (!founde) 
                    {
                        System.out.println("Vehicle not found.");
                    }
                    break;

                    // case 5 on Fayadh
                case 5:
                    
                    while (true) 
                    {
                        
                        System.out.println("\n--- RENTAL RECORD OPTIONS ---");
                        System.out.println("1. View All Records");
                        System.out.println("2. Filter by Customer Name");
                        System.out.println("3. Filter by Active Status");
                        System.out.println("4. Filter by Rental Days");
                        System.out.println("5. Back to Main Menu");
                        
                        System.out.print("Enter your choice: ");
                        int rChoice;
                        
                        try 
                        {
                            rChoice = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (Exception e) 
                        {
                            
                            System.out.print("Invalid input! Please enter a number.: ");
                            scanner.nextLine();
                            continue;
                        }
                        
                        switch (rChoice) 
                        {
                            case 1: 
                                rentalSystem.viewAllRecords(); break;
                            
                            case 2: 
                                System.out.print("Enter the customer name: ");
                                String rCustomerName = scanner.nextLine();
                                rentalSystem.filterByCustomerName(rCustomerName);
                                break;
                                
                            case 3: 
                                System.out.println("Enter if its active or not active, (true or false)");
                                String input = scanner.nextLine();
                                
                                boolean status;
                                if(input.equalsIgnoreCase("true"))
                                        status = true;
                                else if(input.equalsIgnoreCase("false"))
                                        status = false;
                                else
                                {
                                    System.out.println("Invalid input. Enter either true or false");
                                    break;
                                }
                                rentalSystem.filterByActiveStatus(status);
                                break;
                                
                            case 4: 
                                System.out.print("Enter the rental days: ");
                                int rRentalDays = scanner.nextInt();
                                scanner.nextLine();
                                rentalSystem.filterByRentalDays(rRentalDays);
                                break;
                                
                            case 5: 
                                break;
                                
                                
                            default:
                                System.out.println("Invalid choice.");
                                continue;
                        }
                        
                        if(rChoice == 5)
                            break;
                        
                    }
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

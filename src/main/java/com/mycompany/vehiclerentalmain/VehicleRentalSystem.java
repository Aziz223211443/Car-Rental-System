
// ""NOTICE"" From here its a new .java file named VehicleRentalSystem
package com.mycompany.vehiclerentalmain;

import java.util.ArrayList;

//This is R1 ⬇️ to store veihcles 
public class VehicleRentalSystem
{
    private ArrayList<VehicleRental> vehicles;

    public VehicleRentalSystem() {
        this.vehicles = new ArrayList<>();
    }
    public void addVehicle(VehicleRental v)
    {
        vehicles.add(v);
    }
    
    //now here are the methods for R3
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
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no available cars");
    }
    
    public void viewRentedCars()
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getAvailabilityStatus().equalsIgnoreCase("Rented"))
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no rented cars");
    }
    
    public void filterByPrice(double price)
    {
        boolean found = false;
        for(int i = 0; i< vehicles.size(); i++)
        {
            if(vehicles.get(i).getRentalPricePerDay() <= price)
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no cars by this price");
    }
    
    public void filterbyBrand(String brand)
    {
        boolean found = false;
        for(int i = 0; i< vehicles.size(); i++)
        {
            if(vehicles.get(i).getBrand().equalsIgnoreCase(brand))
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no cars by this brand");
    }
    
    public void filterbyYear(int year)
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getYear() == year)
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no cars by this year");
    }
    
    public void fitlerBycolor(String color)
    {
        boolean found = false;
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).getColor().equalsIgnoreCase(color))
                System.out.println(vehicles.get(i));
            found = true;
        }
        if(!found)
            System.out.println("There are no cars by this color");
    }
}

// Fayadh will continue R3 when R6 is Done <----- (important)

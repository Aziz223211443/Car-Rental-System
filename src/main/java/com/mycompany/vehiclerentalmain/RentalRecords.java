
package com.mycompany.vehiclerentalmain;

public class RentalRecords 
{
    private String recordID;
    private VehicleRental vehicle;
    private String customerName;
    private int rentalDays;
    private double totalCost;
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
           "-----------------------\n" +
           "Record ID: " + recordID + "\n" +
           "Vehicle ID: " + vehicle.getVehicleid() + "\n" +
           "Customer Name: " + customerName + "\n" +
           "Rental Days: " + rentalDays + "\n" +
           "Total Cost: " + totalCost + "\n" +
           "Active?: " + active + "\n";
    }
    
}

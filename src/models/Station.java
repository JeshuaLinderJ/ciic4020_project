// Station.java


package models;

import interfaces.StationManagement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Station implements StationManagement {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private String name;
    private int capacity;
    private int currentOccupancy;
    private Set<Object> inventory;
    private Queue<Object> waitlistbicy;
    private Queue<Object> waitlistscoo;
    private Queue<Object> waitlistskate;
    private Scanner scanner = new Scanner(System.in);

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Station(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.inventory = new HashSet<>();
        this.waitlistbicy = new LinkedList<>();
        this.waitlistscoo = new LinkedList<>();
        this.waitlistskate = new LinkedList<>();
    }

    //--------------------------------------------------
    // Getters
    //--------------------------------------------------
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------

    // Compiler recommended stubs
    @Override
    public void makeReservation(HashMap<String, Operator> operatorMap, boolean ismody, String modid) {
        System.out.println("Making reservation at " + this.name + " station");
        // Implementation to be added
    }
    
    @Override
    public void modifyReservation(HashMap<String, Operator> operatorMap) {
        System.out.println("Modifying reservation at " + this.name + " station");
        // Implementation to be added
    }
    
    @Override
    public void cancelReservation(HashMap<String, Operator> operatorMap) {
        System.out.println("Cancelling reservation at " + this.name + " station");
        // Implementation to be added
    }
    
    @Override
    public void getVehicleAvailability(String station, LocalDateTime time) {
        System.out.println("Checking vehicle availability at " + this.name + " station for time " + time);
        // Implementation to be added
    }
    
    @Override
    public void addToWaitlist(HashMap<String, Operator> operatorMap) {
        System.out.println("Adding to waitlist at " + this.name + " station");
        // Implementation to be added
    }
    
    @Override
    public void addVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Adding vehicle at " + this.name + " station");
        if (currentOccupancy < capacity) {
            // Implementation to be added to create the specific vehicle types
            currentOccupancy++;
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Station is full. Cannot add more vehicles.");
        }
    }
    
    @Override
    public void removeVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Removing vehicle from " + this.name + " station");
        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();
        
        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // logic to remove vehicle would go here
            inventory.removeIf(vehicle -> ((Operator) vehicle).getId().equals(ownerID));
            currentOccupancy--;
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }
    
    @Override
    public void modifyVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Modifying vehicle at " + this.name + " station");
        // Implementation to be added
    }
    
    @Override
    public void listVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Listing vehicles at " + this.name + " station");
        System.out.println(inventory);
    }
    
    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    
    // Helpr methods for waitlist management
    public Queue<Object> getWaitlistBicycle() {
        return waitlistbicy;
    }
    
    public Queue<Object> getWaitlistScooter() {
        return waitlistscoo;
    }
    
    public Queue<Object> getWaitlistSkateboard() {
        return waitlistskate;
    }
    
    // Helper method to print inventory
    public void printInventory() {
        System.out.println(inventory);
    }
}
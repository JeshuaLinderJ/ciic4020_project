package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the users in our system.
 * Has roles (client/owner/both) and tracks their vehicles & reservations.
 * Check plan-of-attack.md for more details on what this needs to do.
 */
public class Operator {
    //Instance vars
    private String name;
    private String id;
    private String uprEmail;
    private String phoneNumber;
    private boolean isClient;
    private boolean isOwner;
    private boolean isBoth;
    private double credit; // money stuff
    private int reserved;  // how many reservations they've made
    private int registered;  // how many vehicles they own
    private double profit; // for owners
    private List<String> ownedVehicles;  // list of vehicle IDs they own

    // Main constructor
    public Operator(String name, String id, String uprEmail, boolean isOwner, boolean isClient, 
                   boolean isBoth, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.uprEmail = uprEmail;
        this.isOwner = isOwner;
        this.isClient = isClient;
        this.isBoth = isBoth;
        this.phoneNumber = phoneNumber;
        this.credit = 0.0; // start with 0
        this.reserved = 0;
        this.registered = 0;
        this.profit = 0.0;
        this.ownedVehicles = new ArrayList<>(); // empty list to start
    }

    // Getters & setters below
    public String getName() {
        return name;
    };

    public String getId() {
        return id;
    }

    public String getUprEmail() {
        return uprEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isClient() {
        return isClient;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public boolean isBoth() {
        return isBoth;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public List<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    /**
     * Adds a vehicle to this person's owned vehicles.
     * They can only have 2 max (see requirements)
     */
    public boolean addOwnedVehicle(String vehicleId) {
        // max 2 vehicles per owner - that's the rule!
        if (isOwner && ownedVehicles.size() < 2) {
            ownedVehicles.add(vehicleId);
            registered++; // increase count
            return true;
        }
        return false; // couldn't add it
    }

    /**
     * Takes away a vehicle from their list
     */
    public boolean removeOwnedVehicle(String vehicleId) {
        boolean removed = ownedVehicles.remove(vehicleId);
        if (removed) {
            registered--; // decrease counter if we actually removed something
        }
        return removed;
    }

    // Print all info about this person
    public void display() {
        System.out.println("\n------ Operator Information ------");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("UPR Email: " + uprEmail);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Role: " + (isBoth ? "Client & Owner" : (isClient ? "Client" : (isOwner ? "Owner" : "None"))));
        System.out.println("Credit Balance: " + credit);
        System.out.println("Reservations Made: " + reserved);
        System.out.println("Vehicles Registered: " + registered);
        System.out.println("Profit: " + profit);
        if (isOwner && !ownedVehicles.isEmpty()) {
            System.out.println("Owned Vehicles: " + String.join(", ", ownedVehicles));
        }
        System.out.println("----------------------------------");
    }
}
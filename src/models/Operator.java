package models;

import java.util.ArrayList;
import java.util.List;

/**
 * OPERATOR MODEL
 * 
 * This class represents users of the system as defined in
 * plan-of-attack.md sections 2 (Users) and 5 (Operator Functionality).
 * 
 * An ArrayList is used to store the IDs of owned vehicles (limited to 2 max)
 * for efficient sequential access and modification.
 */
public class Operator {
    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private String name;
    private String id;
    private String uprEmail;
    private String phoneNumber;
    private boolean isClient;
    private boolean isOwner;
    private boolean isBoth;
    private double credit;
    private int reserved;  // Number of reservations made
    private int registered;  // Number of vehicles registered
    private double profit;
    private List<String> ownedVehicles;  // List of vehicle IDs owned by this operator

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Operator(String name, String id, String uprEmail, boolean isOwner, boolean isClient, 
                   boolean isBoth, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.uprEmail = uprEmail;
        this.isOwner = isOwner;
        this.isClient = isClient;
        this.isBoth = isBoth;
        this.phoneNumber = phoneNumber;
        this.credit = 0.0;
        this.reserved = 0;
        this.registered = 0;
        this.profit = 0.0;
        this.ownedVehicles = new ArrayList<>();
    }

    //--------------------------------------------------
    // Getters and Setters
    //--------------------------------------------------
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
     * Adds a vehicle to the operator's owned vehicles list.
     * Enforces the constraint that owners can have at most 2 vehicles.
     * 
     * @param vehicleId The ID of the vehicle to add
     * @return true if the vehicle was added, false if the limit has been reached
     */
    public boolean addOwnedVehicle(String vehicleId) {
        // Check constraint: maximum 2 vehicles per owner
        if (isOwner && ownedVehicles.size() < 2) {
            ownedVehicles.add(vehicleId);
            registered++;
            return true;
        }
        return false;
    }

    /**
     * Removes a vehicle from the operator's owned vehicles list.
     * 
     * @param vehicleId The ID of the vehicle to remove
     * @return true if the vehicle was removed, false if not found
     */
    public boolean removeOwnedVehicle(String vehicleId) {
        boolean removed = ownedVehicles.remove(vehicleId);
        if (removed) {
            registered--;
        }
        return removed;
    }

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    /**
     * Displays all information about this operator.
     * Called by the OperatorManager when listing operators.
     */
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
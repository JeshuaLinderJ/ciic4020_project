package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID; // For unique IDs

public class Bicycle implements Vehicle {

    // Main properties
    private final String id; // cant change after creation
    private final String type; // always "Bicycle" 
    private final String description; // what it looks like
    private String location; // which station it's at
    private boolean available; // can be reserved?
    private String ownerid; // who owns it
    // might add these later:
    // private Schedule usageSchedule;
    // private String ownerId;

    // Constructor - make a new bike
    public Bicycle(String description, String initialLocation, String OwnerId) {
        // Generate random ID with BIC prefix
        this.id = "BIC-" + UUID.randomUUID().toString().substring(0, 8);
        this.type = "Bicycle";
        this.description = description;
        this.location = initialLocation;
        this.available = true; // starts as available
        this.ownerid = OwnerId;
    }

    // Interface methods - getters & setters
    @Override
    public String getOwnerid() {
        return ownerid;
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setAvailability(boolean available) {
        this.available = available;
    }

    /**
     * Calculate rental cost
     * First hour: 3 credits
     * Each additional hour: 2 credits
     */
    public double calculateCost(long durationMinutes) {
        // sanity check
        if (durationMinutes <= 0) {
            return 0;
        }
        
        // Round up partial hours
        double hours = Math.ceil((double) durationMinutes / 60.0);
        
        if (hours <= 1) {
            return 3.0; // just first hour
        } else {
            // first hour + additional hours
            return 3.0 + (hours - 1) * 2.0;
        }
    }

    // For debugging and display
    @Override
    public String toString() {
        return "Bicycle{" +
               "id='" + id + '\'' +
               ", description='" + description + '\'' +
               ", location='" + location + '\'' +
               ", available=" + available + '\'' +
               ", ownerid=" + ownerid +
               '}';
    }
}

package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID;

// Skateboards - the cheapest option for getting around campus
public class Skateboard implements Vehicle {

    // Basic properties
    private String id; // unique ID
    private String type; // always "Skateboard"
    private String description; // color, brand, etc.
    private String location; // which station it's at
    private boolean available; // can someone rent it?
    private String ownerid; // who owns it
    // Might add these later:
    // private Schedule usageSchedule;

    // Create a new skateboard
    public Skateboard(String description, String initialLocation, String OwnerId) {
        // Random ID with SKB prefix
        this.id = "SKB-" + UUID.randomUUID().toString().substring(0, 8);
        this.type = "Skateboard";
        this.description = description;
        this.location = initialLocation;
        this.available = true; // starts as available
        this.ownerid = OwnerId;
    }

    // Interface methods & getters
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
     * Calculate rental cost - cheapest of all vehicles
     * First hour: 1 credit
     * Each additional hour: only 0.5 credits
     */
    public double calculateCost(long durationMinutes) {
        // Can't have negative time
        if (durationMinutes <= 0) {
            return 0;
        }
        
        // Round up to nearest hour
        double hours = Math.ceil((double) durationMinutes / 60.0);
        
        if (hours <= 1) {
            return 1.0; // just first hour
        } else {
            return 1.0 + (hours - 1) * 0.5; // first hour + cheap additional hours
        }
    }

    // For debugging and display purposes
    @Override
    public String toString() {
        return "Skateboard{" +
               "id='" + id + 
               "', desc='" + description + 
               "', at='" + location + 
               "', avail=" + available +
               ", owner=" + ownerid +
               '}';
    }
}

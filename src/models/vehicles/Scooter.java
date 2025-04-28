package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID;

public class Scooter implements Vehicle {

    // Scooter properties
    private String id; // unique identifier
    private String type; // vehicle type
    private String description; // what it looks like
    private String location; // where it's parked
    private boolean available; // can be reserved or not
    private String ownerid; // who owns it
    // Might implement later:
    // private Schedule usageSchedule;

    // Create a new scooter
    public Scooter(String description, String initialLocation, String OwnerId) {
        // Generate ID with SCO prefix and random part
        this.id = "SCO-" + UUID.randomUUID().toString().substring(0, 8); // TODO: maybe change ID format?
        this.type = "Scooter";
        this.description = description;
        this.location = initialLocation;
        this.available = true; // start as available
        this.ownerid = OwnerId;
    }

    // Required interface methods 
    
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
     * Figures out how much to charge
     * Scooter prices:
     * - First hour: 2 credits
     * - Each additional hour: 1 credit
     */
    public double calculateCost(long durationMinutes) {
        // Handle bad input
        if (durationMinutes <= 0) {
            return 0;
        }
        
        // Convert to hours (rounded up)
        double hours = Math.ceil((double) durationMinutes / 60.0);
        
        // Calculate cost
        if (hours <= 1) {
            return 2.0; // just the first hour
        } else {
            return 2.0 + (hours - 1) * 1.0; // first hour + additional hours
        }
    }

    // Better toString for debugging
    @Override
    public String toString() {
        return "Scooter{" +
               "id='" + id + 
               "', desc='" + description + 
               "', at='" + location + 
               "', avail=" + available +
               ", owner=" + ownerid +
               '}';
    }
}

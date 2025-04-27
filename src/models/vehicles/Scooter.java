package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID;

public class Scooter implements Vehicle {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private String id;
    private String type;
    private String description;
    private String location;
    private boolean available;
    // private Schedule usageSchedule;
    // private String ownerId;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Scooter(String description, String initialLocation) {
        this.id = "SCO-" + UUID.randomUUID().toString().substring(0, 8); // TODO: Discuss vehicle ID, whether first 3 letters or some other format
        this.type = "Scooter";
        this.description = description;
        this.location = initialLocation;
        this.available = true;
    }

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
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

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    /**
     * Calculates the cost based on duration.
     * Scooter: 2 credits first hour, 1 credit additional hours.
     * @param durationMinutes The duration of the rental in minutes.
     * @return The calculated cost in credits.
     */
    public double calculateCost(long durationMinutes) {
        if (durationMinutes <= 0) {
            return 0;
        }
        double hours = Math.ceil((double) durationMinutes / 60.0);
        if (hours <= 1) {
            return 2.0; // First hour cost
        } else {
            return 2.0 + (hours - 1) * 1.0; // First hour + additional hours
        }
    }

    //--------------------------------------------------
    // Object Overrides
    //--------------------------------------------------
    @Override
    public String toString() {
        return "Scooter{" +
               "id='" + id + 
               "', description='" + description + 
               "', location='" + location + 
               "', available=" + available + 
               '}'; // Tabbed just to shorten the line for ease of reading
    }
};

package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID; // For generating unique IDs

public class Bicycle implements Vehicle {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private final String id;
    private final String type;
    private final String description;
    private String location; // Station name or ID
    private boolean available;
    private String ownerid;
    // private Schedule usageSchedule; // Placeholder for schedule implementation
    // private String ownerId; // Optional: Add if needed

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Bicycle(String description, String initialLocation, String OwnerId) {
        this.id = "BIC-" + UUID.randomUUID().toString().substring(0, 8); // Example ID format
        this.type = "Bicycle";
        this.description = description;
        this.location = initialLocation;
        this.available = true; 
        this.ownerid = OwnerId;
        // Assume available initially
        // Initialize schedule and owner if applicable
    }

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
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

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    /**
     * Calculates the cost based on duration.
     * Bike: 3 credits first hour, 2 credits additional hours.
     * @param durationMinutes The duration of the rental in minutes.
     * @return The calculated cost in credits.
     */
    public double calculateCost(long durationMinutes) {
        if (durationMinutes <= 0) {
            return 0;
        }
        double hours = Math.ceil((double) durationMinutes / 60.0);
        if (hours <= 1) {
            return 3.0; // First hour cost
        } else {
            return 3.0 + (hours - 1) * 2.0; // First hour + additional hours
        }
    }

    // --- Getters for other potential fields (Schedule, Owner) ---
    // public Schedule getUsageSchedule() { return usageSchedule; }
    // public String getOwnerId() { return ownerId; }

    // Optional: Override toString for better representation
    @Override
    public String toString() {
        return "Bicycle{" +
               "id='" + id + '\'' +
               ", description='" + description + '\'' +
               ", location='" + location + '\'' +
               ", available=" + available + '\'' +
               ",ownerid=" + ownerid +
               '}'; // Tabbed just to shorten the line for ease of reading
    }
}

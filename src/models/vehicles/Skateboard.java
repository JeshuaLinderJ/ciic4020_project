package models.vehicles;

import interfaces.Vehicle;
import java.util.UUID;

public class Skateboard implements Vehicle {

    private String id;
    private String type;
    private String description;
    private String location;
    private boolean available;
    // private Schedule usageSchedule;
    // private String ownerId;

    public Skateboard(String description, String initialLocation) {
        this.id = "SKB-" + UUID.randomUUID().toString().substring(0, 8);
        this.type = "Skateboard";
        this.description = description;
        this.location = initialLocation;
        this.available = true;
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

    // TODO: read this method definition, we have to use more of these, use as example

    /**
     * Calculates the cost based on duration.
     * Skateboard: 1 credit first hour, 0.5 credits additional hours.
     * @param durationMinutes The duration of the rental in minutes.
     * @return The calculated cost in credits.
     */
    public double calculateCost(long durationMinutes) {
        if (durationMinutes <= 0) {
            return 0;
        }
        double hours = Math.ceil((double) durationMinutes / 60.0);
        if (hours <= 1) {
            return 1.0; // First hour cost
        } else {
            return 1.0 + (hours - 1) * 0.5; // First hour + additional hours
        }
    }

    // Optional: Override toString for better representation
    @Override
    public String toString() {
        return "Skateboard{" +
               "id='" + id + 
               "', description='" + description + 
               "', location='" + location + 
               "', available=" + available + 
               '}'; // Tabbed just to shorten the line for ease of reading
    }
}

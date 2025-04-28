package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a vehicle reservation in the system
 * Tracks who reserved what, when, and how much it costs
 */
public class Reservation {
    // The important data fields
    private String reservationId; // unique identifier
    private String operatorId; // who made the reservation
    private String vehicleId; // what they reserved
    private LocalDateTime startTime; // when it starts
    private LocalDateTime endTime; // when it ends
    private String stationLocation; // where the vehicle is
    private double cost; // how much it costs
    private boolean active; // is it still valid?

    // Create a new reservation
    public Reservation(String operatorId, String vehicleId, LocalDateTime startTime, 
                      LocalDateTime endTime, String stationLocation) {
        // Generate random ID with RES prefix
        this.reservationId = "RES-" + UUID.randomUUID().toString().substring(0, 8);
        this.operatorId = operatorId;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stationLocation = stationLocation;
        this.cost = 0.0; // will calculate this later
        this.active = true; // start as active
    }

    // Getters and setters
    public String getReservationId() {
        return reservationId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Utility methods
    
    /**
     * Get how long the reservation is for (in minutes)
     */
    public long getDurationMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }
    
    /**
     * Check if reservation is during allowed hours
     * (7am-6pm weekdays only)
     */
    public boolean isWithinServiceHours() {
        // Check day of week (1=Monday, 5=Friday)
        int day = startTime.getDayOfWeek().getValue();
        if (day > 5) {
            return false; // it's a weekend
        }
        
        // Check time (7am-6pm = 7-18 in 24hr format)
        int startHr = startTime.getHour();
        int endHr = endTime.getHour();
        
        return startHr >= 7 && endHr <= 18;
    }
    
    /**
     * Make sure reservation isn't too long
     * Max allowed is 6 hours
     */
    public boolean isValidDuration() {
        long hours = getDurationMinutes() / 60;
        return hours <= 6; // max 6 hours
    }
    
    /**
     * Figure out how much to charge based on vehicle type
     * Different rates for each vehicle type
     */
    public double calculateCost(String vehicleType) {
        long mins = getDurationMinutes();
        double hours = Math.ceil((double) mins / 60.0);
        
        // Each vehicle has different pricing
        switch (vehicleType.toLowerCase()) {
            case "bicycle":
                // Bikes: 3 credits first hour, 2 credits after
                if (hours <= 1) {
                    this.cost = 3.0;
                } else {
                    this.cost = 3.0 + (hours - 1) * 2.0;
                }
                break;
            case "scooter":
                // Scooters: 2 credits first hour, 1 credit after
                if (hours <= 1) {
                    this.cost = 2.0;
                } else {
                    this.cost = 2.0 + (hours - 1) * 1.0;
                }
                break;
            case "skateboard":
                // Skateboards: 1 credit first hour, 0.5 credits after
                if (hours <= 1) {
                    this.cost = 1.0;
                } else {
                    this.cost = 1.0 + (hours - 1) * 0.5;
                }
                break;
            default:
                // Unknown type
                this.cost = 0.0;
        }
        
        return this.cost;
    }
    
    // For debugging/display
    @Override
    public String toString() {
        return "Reservation{" +
               "id='" + reservationId + '\'' +
               ", user='" + operatorId + '\'' +
               ", vehicle='" + vehicleId + '\'' +
               ", from=" + startTime +
               ", to=" + endTime +
               ", at='" + stationLocation + '\'' +
               ", cost=" + cost +
               ", active=" + active +
               '}';
    }
}
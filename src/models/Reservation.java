package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private String reservationId;
    private String operatorId;
    private String vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String stationLocation;
    private double cost;
    private boolean active;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Reservation(String operatorId, String vehicleId, LocalDateTime startTime, 
                      LocalDateTime endTime, String stationLocation) {
        this.reservationId = "RES-" + UUID.randomUUID().toString().substring(0, 8);
        this.operatorId = operatorId;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stationLocation = stationLocation;
        this.cost = 0.0; // Will be calculated separately
        this.active = true;
    }

    //--------------------------------------------------
    // Getters and Setters
    //--------------------------------------------------
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

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    
    /**
     * Calculates the duration of the reservation in minutes
     * @return Duration in minutes
     */
    public long getDurationMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }
    
    /**
     * Validates if the reservation is within service hours (7:00 AM - 6:00 PM, Mon-Fri)
     * @return True if valid, false otherwise
     */
    public boolean isWithinServiceHours() {
        // Check if weekday (Monday = 1, Friday = 5)
        int dayOfWeek = startTime.getDayOfWeek().getValue();
        if (dayOfWeek > 5) {
            return false; // Weekend
        }
        
        // Check if within hours (7:00 AM - 6:00 PM)
        int startHour = startTime.getHour();
        int endHour = endTime.getHour();
        
        return startHour >= 7 && endHour <= 18;
    }
    
    /**
     * Validates if the reservation duration is within the 6-hour limit
     * @return True if valid, false otherwise
     */
    public boolean isValidDuration() {
        long hours = getDurationMinutes() / 60;
        return hours <= 6;
    }
    
    /**
     * Calculates the reservation cost based on vehicle type and duration
     * @param vehicleType The type of vehicle (bicycle, scooter, skateboard)
     * @return The calculated cost
     */
    public double calculateCost(String vehicleType) {
        long durationMinutes = getDurationMinutes();
        double hours = Math.ceil((double) durationMinutes / 60.0);
        
        switch (vehicleType.toLowerCase()) {
            case "bicycle":
                if (hours <= 1) {
                    this.cost = 3.0; // First hour cost
                } else {
                    this.cost = 3.0 + (hours - 1) * 2.0; // First hour + additional hours
                }
                break;
            case "scooter":
                if (hours <= 1) {
                    this.cost = 2.0; // First hour cost
                } else {
                    this.cost = 2.0 + (hours - 1) * 1.0; // First hour + additional hours
                }
                break;
            case "skateboard":
                if (hours <= 1) {
                    this.cost = 1.0; // First hour cost
                } else {
                    this.cost = 1.0 + (hours - 1) * 0.5; // First hour + additional hours
                }
                break;
            default:
                this.cost = 0.0;
        }
        
        return this.cost;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
               "id='" + reservationId + '\'' +
               ", operator='" + operatorId + '\'' +
               ", vehicle='" + vehicleId + '\'' +
               ", start=" + startTime +
               ", end=" + endTime +
               ", location='" + stationLocation + '\'' +
               ", cost=" + cost +
               ", active=" + active +
               '}';
    }
}
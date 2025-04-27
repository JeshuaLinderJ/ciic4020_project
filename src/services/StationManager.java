package services;

import interfaces.StationManagement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import models.Reservation;
import models.Station;

public class StationManager implements StationManagement {
    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private Map<String, Station> stations; // this can be changed for an int if we want to have them be like 0-4 or something

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public StationManager() {
        this.stations = new HashMap<>();
    };

    // @Override
    // public void addStation(Station station) {
    //     stations.put(station.getName(), station);
    // };
    
    // @Override
    // public void removeStation(String stationName) {
    //     stations.remove(stationName);
    // };
    
    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
    // @Override // commented to get rid of error
    public Station getStation(String stationName) {
        return stations.get(stationName);
    };
    
    @Override
    public void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end) {
        // TODO: Implement reservation logic
        System.out.println("Reservation made for operator " + OperatorID + " and vehicle " + vehicleID);
    }
    
    @Override
    public void modifyReservation(String reservationID, Reservation updated) {
        // TODO: Implement modification logic
        System.out.println("Reservation " + reservationID + " modified.");
    }
    
    @Override
    public void cancelReservation(String reservationID) {
        // TODO: Implement cancellation logic
        System.out.println("Reservation " + reservationID + " canceled.");
    }
    
    @Override
    public void getVehicleAvailability(String station, LocalDateTime time) {
        // TODO: Implement vehicle availability check
        System.out.println("Checking availability at station " + station + " for time " + time);
    }
    
    @Override
    public void addToWaitlist(String OperatorID, String vehicleType, String station) {
        // TODO: Implement waitlist logic
        System.out.println("Added operator " + OperatorID + " to waitlist for vehicle type " + vehicleType + " at station " + station);
    }
};
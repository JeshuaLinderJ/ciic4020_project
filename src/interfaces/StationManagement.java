// Interface of Managment

package interfaces;



import models.Station;
import models.Reservation;

public interface StationManagement {
    //void addStation(Station station);//remove
    //void removeStation(String stationName);//remove since they are predefined and it dosent tell us to add or remove anny 
    //Station getStation(String stationName);

    //i think they should go here 
    void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end);
    void modifyReservation(String reservationID, Reservation updated);

    void cancelReservation(String reservationID);

    void getVehicleAvailability(String station, LocalDateTime time);

    void addToWaitlist(String OperatorID, String vehicleType, String station);

    void addVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap);
    void removeVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap);
    void modifyVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap);
    void listVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap);

};

// ----------------------------------------------------------
// STATION MANAGEMENT INTERFACE
//
// This interface handles station operations as defined in the
// plan-of-attack.md
//
// Data structures used:
//   - Set - For managing the vehicle inventory at each station (no duplicates)
//   - Queue - For implementing the waitlist at each station (first-come-first-served)
//
// Core responsibilities:
//   - Manage the five predefined stations and their vehicle capacities:
//     - Stefani Building (40)
//     - Student Center (90)
//     - Biology Building (35)
//     - Chemical Engineering Building (45)
//     - Business Admin Building (45)
//   - Track vehicle inventory and location
//   - Manage reservations at each station
//   - Maintain waitlists when stations are at capacity
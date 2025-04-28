// Interface of Managment

package interfaces;



import java.time.LocalDateTime;
import java.util.HashMap;
import models.Operator;
import models.Reservation;


public interface StationManagement {
    //void addStation(Station station);//remove
    //void removeStation(String stationName);//remove since they are predefined and it dosent tell us to add or remove anny 
    //Station getStation(String stationName);

    //i think they should go here 
    void .makeReservation(HashMap<String, Operator> operatorMap,boolean ismody, String modid);
    void modifyReservation(HashMap<String, Operator> operatorMap);

    void cancelReservation(HashMap<String, Operator> operatorMap);

    void getVehicleAvailability(String station, LocalDateTime time);

    void addToWaitlist(HashMap<String, Operator> operatorMap);

    void addVehicle(HashMap<String, Operator> operatorMap);
    void removeVehicle( HashMap<String, Operator> operatorMap);
    void modifyVehicle(HashMap<String, Operator> operatorMap);
    void listVehicle( HashMap<String, Operator> operatorMap);

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
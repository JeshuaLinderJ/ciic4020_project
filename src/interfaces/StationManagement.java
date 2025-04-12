package interfaces;

import models.Station;

public interface StationManagement {
    void addStation(Station station);
    void removeStation(String stationName);
    Station getStation(String stationName);
}

// ----------------------------------------------------------
// STATION MANAGEMENT INTERFACE
//
// This interface handles station operations as defined in the
// plan-of-attack.md
//
// Data structures used:
//   • Set - For managing the vehicle inventory at each station (no duplicates)
//   • Queue - For implementing the waitlist at each station (first-come-first-served)
//
// Core responsibilities:
//   • Manage the five predefined stations and their vehicle capacities:
//     - Stefani Building (40)
//     - Student Center (90)
//     - Biology Building (35)
//     - Chemical Engineering Building (45)
//     - Business Admin Building (45)
//   • Track vehicle inventory and location
//   • Manage reservations at each station
//   • Maintain waitlists when stations are at capacity
package models;

public class Station {
    private String name;
    private int capacity;

    public Station(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}

// ----------------------------------------------------------
// STATION MODEL
//
// This class represents a physical station location as defined in the
// plan-of-attack.md
//
// Five predefined stations with capacities:
//   • Stefani Building: 40 vehicles
//   • Student Center: 90 vehicles
//   • Biology Building: 35 vehicles
//   • Chemical Engineering Building: 45 vehicles
//   • Business Admin Building: 45 vehicles
//
// Needs to be expanded to include:
//   • Vehicle inventory tracking (using Set)
//   • Station-specific waitlist (using Queue)
//   • Current occupancy tracking
//
// Core responsibilities:
//   • Track vehicles currently at the station
//   • Monitor capacity and availability
//   • Manage station-specific reservations
//   • Maintain waitlist when station is at capacity
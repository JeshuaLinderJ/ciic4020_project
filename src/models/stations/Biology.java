package models.stations;

import models.Station;

// The Biology building station
// One of the smaller stations with 35 vehicle capacity
public class Biology extends Station {
    // Initialize with name and capacity
    public Biology() {
        super("Biology", 35); // only 35 spots here
    };
    
    // Note: This is smaller than Stefani, might need to expand later?
}

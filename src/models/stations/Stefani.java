package models.stations;

import models.Station;

// Represents the Stefani Building station
// This one has a medium capacity (40)
public class Stefani extends Station {
    // Constructor just passes name and capacity to parent
    public Stefani() {
        super("Stefani", 40); // 40 vehicle capacity
    };
    
    // We might add building-specific methods later
}
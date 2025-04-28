package models.stations;

import models.Station;

// Business Administration Building station
// Medium-large capacity (45 vehicles)
public class Business extends Station {
    // Basic constructor - just sets name and size
    public Business() {
        super("Business Administration", 45); // 45 vehicle spaces
    };
    
    // Business students tend to use this station a lot!
}

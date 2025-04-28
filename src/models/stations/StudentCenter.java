package models.stations;

import models.Station;

// Student Center - biggest station on campus
// Has the highest capacity for vehicles
public class StudentCenter extends Station {
    // Just set name and capacity in parent constructor
    public StudentCenter() {
        super("Student Center", 90); // 90 vehicles - largest capacity!
    };
    
    // TODO: Maybe add specific student center features in the future?
}

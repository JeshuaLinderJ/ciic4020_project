package models.stations;

import models.Station;

// Chemical Engineering Building station
// Has same capacity as Business Admin (45)
public class ChemicalEngineering extends Station {
    // Simple constructor passes values to parent
    public ChemicalEngineering() {
        super("Chemical Engineering", 45); // fixed the extra space in name
    };
    
    // Engineers often grab vehicles early in the morning!
}

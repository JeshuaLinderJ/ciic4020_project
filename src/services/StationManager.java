package services;

import interfaces.StationManagement;
import java.util.HashMap;
import java.util.Map;
import models.Station;

public class StationManager implements StationManagement {
    private Map<String, Station> stations; // this can be changed for an int if we want to have them be like 0-4 or something
    public StationManager() {
        this.stations = new HashMap<>();
    };

    @Override
    public void addStation(Station station) {
        stations.put(station.getName(), station);
    };
    
    @Override
    public void removeStation(String stationName) {
        stations.remove(stationName);
    };
    
    @Override
    public Station getStation(String stationName) {
        return stations.get(stationName);
    };
};
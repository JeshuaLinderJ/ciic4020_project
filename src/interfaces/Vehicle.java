package interfaces;

// TODO: Maybe use an enum later?
// enum VehicleType { BICYCLE, SCOOTER, SKATEBOARD }

/**
 * Basic interface all vehicles must implement
 * We have 3 vehicle types with different pricing:
 * - Bicycles (most expensive)
 * - Scooters (medium)
 * - Skateboards (cheapest)
 */
public interface Vehicle {

    // Core getters/setters all vehicles need
    String getId(); // unique identifier
    String getOwnerid(); // who owns it
    String getType(); // what kind of vehicle
    String getDescription(); // what it looks like
    String getLocation(); // which station it's at
    boolean isAvailable(); // can be reserved?
    
    // Mutators
    void setLocation(String location);
    void setAvailability(boolean available);

    // Might add these later:
    // E getUsageSchedule();
    // String getOwnerId();
}

// Some important rules:
// - Owners can only register up to 2 vehicles
// - Clients can only reserve 1 vehicle at a time
// - Reservations max duration: 6 hours
// 
// Pricing tiers:
// Bicycle: 3 credits first hour, 2 credits additional hours
// Scooter: 2 credits first hour, 1 credit additional hours
// Skateboard: 1 credit first hour, 0.5 credits additional hours
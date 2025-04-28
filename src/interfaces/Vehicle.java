package interfaces;

// PLANNED ENUM
// enum VehicleType { BICYCLE, SCOOTER, SKATEBOARD }

public interface Vehicle {

// Implementations will provide the fields

    String getId();
    String ownerid();
    String getType();
    String getDescription();
    String getLocation(); // Station name or ID
    boolean isAvailable();
    void setLocation(String location);
    void setAvailability(boolean available);

    // methods
    // E getUsageSchedule();
    // String getOwnerId();
}

// ----------------------------------------------------------
// VEHICLE SYSTEM INTERFACE
// 
// This interface defines the core functionality required for all vehicle types
// as outlined in the plan-of-attack.md
//
// Implementation classes will represent:
//   - Bicycle - Highest cost tier (3 credits first hour, 2 credits additional)
//   - Scooter - Medium cost tier (2 credits first hour, 1 credit additional)
//   - Skateboard - Lowest cost tier (1 credit first hour, 0.5 credits additional)
//
// The vehicle inventory will be implemented using a Set data structure
// to ensure unique vehicles and efficient lookup operations.
//
// Key constraints from requirements:
//   - Users can only register up to 2 vehicles for rent
//   - Users can only reserve 1 vehicle at a time
//   - Maximum reservation duration: 6 hours
//   - Each vehicle must have unique ID, type, description and location
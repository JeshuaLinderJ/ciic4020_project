package models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Station {
//--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private String name;
    private int capacity;
    private int currentOccupancy;

    private Set<Object> inventory;
    private Queue<Object> waitlist;

//--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Station(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.inventory = new HashSet<>();
        this.waitlist = new LinkedList<>();
    };

//--------------------------------------------------
    // Getters
    //--------------------------------------------------
    public String getName() {
        return name;
    };

    public int getCapacity() {
        return capacity;
    };

    public int getCurrentOccupancy() {
        return currentOccupancy;
    };

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
   //vehicle is just a stand in until for now, i know we have the vehicle interface but yeah that's what i got for now 
   //maybe the add and remove could be void but i thougth that maybe gving feed back for each add or remove could be good
    public boolean addVehicle(Object vehicle) {
        if (currentOccupancy < capacity) {
            inventory.add(vehicle);
            currentOccupancy++;
            return true;
            //there could be a true or false here that can be used for stuff like limiting the amount register up to 2 vehicles for rent
            //, something like
            //a amountregisterdlimit variable could be tracked and if it == 2 then this is checked to make it so that they cant regestier anymore
            //it needs some changes like a if(!amountregisterdlimit) at the start then this is done, something like amountregisterdlimit < 2
            //workes as well probably better.

            //if capacity is at max then its added to waitlist
        } else {
            waitlist.add(vehicle);
            return false;
        }
};

//this and add will need the most changes later I think after the vehicle types are added 
public boolean removeVehicle(Object vehicle) {
    if (inventory.remove(vehicle)) {
        currentOccupancy--;
       
        if (!waitlist.isEmpty()) {
            Object nextVehicle = waitlist.poll();
            inventory.add(nextVehicle);
            currentOccupancy++;
            //if a vehicle is in the waitlist it will get added since now theres space for it 
           
        };
        return true;
    };
    return false;
};
//this can be inrpoved later to better display the inventory
public Set<Object> getInventory() {
    return inventory;
};

public Queue<Object> getWaitlist() {
    return waitlist;
    };
};


// // Stefani  Station
// public class Stefani extends Station {
//     public Stefani() {
//         super("Stefani", 40);
//     };
// };

// // Student Center Station
// public class StudentCenter extends Station {
//     public StudentCenter() {
//         super("Student Center", 90);
//     };
// };

// Biology  Station
// public class Biology extends Station {
//     public Biology() {
//         super("Biology", 35);
//     };
// };

// // Chemical Engineering Staiont 
// public class ChemicalEngineering extends Station {
//     public ChemicalEngineering() {
//         super("Chemical Engineering ", 45);
//     };
// };

// // Business Administration Station
// public class BusinessAdmin extends Station {
//     public BusinessAdmin() {
//         super("Business Administration", 45);
//     };
// };

// ----------------------------------------------------------
// STATION MODEL
//
// This class represents a physical station location as defined in the
// plan-of-attack.md
//
// Five predefined stations with capacities:
//   - Stefani Building: 40 vehicles
//   - Student Center: 90 vehicles
//   - Biology Building: 35 vehicles
//   - Chemical Engineering Building: 45 vehicles
//   - Business Admin Building: 45 vehicles
//
// Needs to be expanded to include:
//   - Vehicle inventory tracking (using Set)
//   - Station-specific waitlist (using Queue)
//   - Current occupancy tracking
//
// Core responsibilities:
//   - Track vehicles currently at the station
//   - Monitor capacity and availability
//   - Manage station-specific reservations
//   - Maintain waitlist when station is at capacity

// package models;

// import java.util.HashSet;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.Scanner;
// import java.util.Set;

// import models.vehicles.Bicycle;
// import models.vehicles.Scooter;
// import models.vehicles.Skateboard;

// public class Station {
//     private String name;
//     private int capacity;
//     private int currentOccupancy;

//     private Set<Object> inventory;
//     private Queue<Object> waitlist;

//     public Station(String name, int capacity) {
//         this.name = name;
//         this.capacity = capacity;
//         this.currentOccupancy = 0;
//         this.inventory = new HashSet<>();
//         this.waitlist = new LinkedList<>();
//     }

//     public String getName() {
//         return name;
//     }

//     public int getCapacity() {
//         return capacity;
//     }

//     public int getCurrentOccupancy() {
//         return currentOccupancy;
//     }

//     // Adds vehicle by asking the user what type of vehicle they want
//     public boolean addVehicle() {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("What type of vehicle do you want to add? (bicycle/scooter/skateboard)");
//         String vehicleType = scanner.nextLine().trim().toLowerCase();

//         System.out.println("Enter a description for the vehicle:");
//         String description = scanner.nextLine();

//         System.out.println("Enter the initial location (station name):");
//         String location = scanner.nextLine();

//         Object vehicle = null;

//         if (vehicleType.equals("bicycle")) {
//             vehicle = new Bicycle(description, location);
//         } else if (vehicleType.equals("scooter")) {
//             vehicle = new Scooter(description, location);
//         } else if (vehicleType.equals("skateboard")) {
//             vehicle = new Skateboard(description, location);
//         } else {
//             System.out.println("Invalid vehicle type. Vehicle not added.");
//             return false;
//         }

//         if (currentOccupancy < capacity) {
//             inventory.add(vehicle);
//             currentOccupancy++;
//             System.out.println("Vehicle added successfully.");
//             return true;
//         } else {
//             waitlist.add(vehicle);
//             System.out.println("Station is full. Vehicle added to waitlist.");
//             return false;
//         }
//     }

//     public boolean removeVehicle(Object vehicle) {
//         if (inventory.remove(vehicle)) {
//             currentOccupancy--;
//             System.out.println("Vehicle removed successfully.");
//             return true;
//         }
//         System.out.println("Vehicle not found in inventory.");
//         return false;
//     }

//     public Set<Object> getInventory() {
//         return inventory;
//     }

//     public Queue<Object> getWaitlist() {
//         return waitlist;
//     };
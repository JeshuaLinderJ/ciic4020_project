Station.java


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

    
    private Queue<Object> waitlistbicy;
    private Queue<Object> waitlistscoo;
    private Queue<Object> waitlistskate;


    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public Station(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.inventory = new HashSet<>();
        this.waitlistbicy = new LinkedList<>();
        this.waitlistscoo = new LinkedList<>();
        this.waitlistskate = new LinkedList<>();
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

    public void AddVehicle(Object vehicle) {
        if (currentOccupancy < capacity) {
            //inventory.add(vehicle);
            currentOccupancy++;
   
};


//this and add will need the most changes later I think after the vehicle types are added 
public void RemoveVehicle() {

    System.out.println("Enter the owner's ID:");
    String ownerID = scanner.nextLine();
    // Check if the owner exists and if they are an owner or both
   Operator owner = operatorMap.get(ownerID); 


   inventory.removeIf(owner -> owner.getId() == ownerID);//fix later with ne perameter

   public void setProfit(double profit) { this.profit += profit; }
   // if (inventory.remove(vehicle)) {
        currentOccupancy--;
       
       //if (!waitlist.isEmpty()) {
           // Object nextVehicle = waitlist.poll();
           // inventory.add(nextVehicle);
           // currentOccupancy++;
            //revmove this later
           
       // };
       }
};
//this can be inrpoved later to better display the inventory
public void printInventory() {
    System.out.println(inventory);
}

public Queue<Object> getWaitlist() {
    return waitlist;
    };//make the other waitlists
};




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
//     }
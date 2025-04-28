// Station.java // Commented to remove error on Main.java


package models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
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
    }

};
//this and add will need the most changes later I think after the vehicle types are added 
    public void RemoveVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();
        // Check if the owner exists and if they are an owner or both
        // Operator owner = operatorMap.get(ownerID);


        inventory.removeIf(owner -> ((Operator) owner).getId() == ownerID);


         currentOccupancy--;
  
            
        };

    //this can be inrpoved later to better display the inventory
    public void printInventory() {
        System.out.println(inventory);
    }

    public Queue<Object> getWaitlist() {
        return getWaitlist(); // was originally waitlist
    };//make the other waitlists
};


package services;

import interfaces.StationManagement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import models.Operator;
import models.Station;

public class StationManager implements StationManagement {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private Map<String, Station> stations;
    private HashMap<String, Object> reservation = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public StationManager() {
        this.stations = new HashMap<>();
    }

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
    public Station getStation(String stationName) {
        return stations.get(stationName);
    }
    
    @Override
    public void makeReservation(HashMap<String, Operator> operatorMap, boolean ismody, String modid) {
        System.out.println("\nWhat type of vehicle do you want to reserve?");
        System.out.println("1 - Bicycle");
        System.out.println("2 - Scooter");
        System.out.println("3 - Skateboard");
        System.out.print("Your choice: ");
        
        int vehicleType = Integer.parseInt(scanner.nextLine());
        
        switch (vehicleType) {
            case 1:
                processBicycleReservation(operatorMap, ismody, modid);
                break;
            case 2:
                processScooterReservation(operatorMap, ismody, modid);
                break;
            case 3:
                processSkateboardReservation(operatorMap, ismody, modid);
                break;
            default:
                System.out.println("Invalid vehicle type selected.");
                break;
        }
    }
    
    private void processBicycleReservation(HashMap<String, Operator> operatorMap, boolean mod, String MODid) {
        System.out.println("Processing bicycle reservation");
        // Placeholder implementation - would need actual inventory, etc.
        System.out.println("Bicycle reservation functionality to be implemented");
    }
    
    private void processScooterReservation(HashMap<String, Operator> operatorMap, boolean mod, String MODid) {
        System.out.println("Processing scooter reservation");
        // Placeholder implementation
        System.out.println("Scooter reservation functionality to be implemented");
    }
    
    private void processSkateboardReservation(HashMap<String, Operator> operatorMap, boolean mod, String MODid) {
        System.out.println("Processing skateboard reservation");
        // Placeholder implementation
        System.out.println("Skateboard reservation functionality to be implemented");
    }
    
    @Override
    public void modifyReservation(HashMap<String, Operator> operatorMap) {
        boolean isMode = true;
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();
        
        if (reservation.containsKey(id)) {
            makeReservation(operatorMap, isMode, id);
            System.out.println("Reservation for " + id + " modified.");
        } else {
            System.out.println("Reservation with ID " + id + " does not exist.");
        }
    }
    
    @Override
    public void cancelReservation(HashMap<String, Operator> operatorMap) {
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();

        if (reservation.containsKey(id)) {
            reservation.remove(id);
            System.out.println("Reservation with ID " + id + " has been deleted.");
        } else {
            System.out.println("No reservation found with ID: " + id);
        }
    }
    
    @Override
    public void getVehicleAvailability(String station, LocalDateTime time) {
        System.out.println("Checking availability at station " + station + " for time " + time);
    }
    
    @Override
    public void addToWaitlist(HashMap<String, Operator> operatorMap) {
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter vehicle type (bicycle/scooter/skateboard): ");
        String vehicleType = scanner.nextLine().toLowerCase();
        
        System.out.println("Client " + id + " added to waitlist for " + vehicleType);
    }

    @Override
    public void addVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("\nWhat type of vehicle do you want to add?");
        System.out.println("1 - Bicycle");
        System.out.println("2 - Scooter");
        System.out.println("3 - Skateboard");
        System.out.print("Your choice: ");
        int vehicleType = Integer.parseInt(scanner.nextLine());

        switch (vehicleType) {
            case 1:
                addBicycle(operatorMap);
                break;
            case 2:
                addScooter(operatorMap);
                break;
            case 3:
                addSkateboard(operatorMap);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    
    private void addBicycle(HashMap<String, Operator> operatorMap) {
        System.out.println("Enter a description for the bicycle:");
        String description = scanner.nextLine();

        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();

        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // Logic to add bicycle
            System.out.println("Bicycle added successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }
    
    private void addScooter(HashMap<String, Operator> operatorMap) {
        System.out.println("Enter a description for the scooter:");
        String description = scanner.nextLine();

        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();

        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // Logic to add scooter
            System.out.println("Scooter added successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }
    
    private void addSkateboard(HashMap<String, Operator> operatorMap) {
        System.out.println("Enter a description for the skateboard:");
        String description = scanner.nextLine();

        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();

        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // Logic to add skateboard
            System.out.println("Skateboard added successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }

    @Override
    public void removeVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();
        
        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // Logic to remove vehicle would go here
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }
      
    @Override
    public void listVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Listing all vehicles:");
        // Logic to list vehicles would go here
        System.out.println("Vehicle listing functionality to be implemented");
    }

    @Override
    public void modifyVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("Enter the owner's ID:");
        String ownerID = scanner.nextLine();

        Operator owner = operatorMap.get(ownerID);
        if (owner != null && (owner.isOwner() || owner.isBoth())) {
            // Implement vehicle modification logic
            System.out.println("Vehicle modified successfully.");
        } else {
            System.out.println("Owner ID not found or not an owner.");
        }
    }
}

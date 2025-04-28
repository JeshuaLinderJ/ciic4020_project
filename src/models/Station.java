// Station.java
package models;

import interfaces.StationManagement;
import interfaces.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import models.vehicles.Bicycle;
import models.vehicles.Scooter;
import models.vehicles.Skateboard;
import services.ServiceHoursManager;

/**
 * Base station class - handles all the vehicle/reservation stuff
 * Each station is at a different building and has its own capacity
 */
public class Station implements StationManagement {

    // Basic properties
    private String name; // station name
    private int capacity; // max # of vehicles
    private int currentOccupancy; // how many we have now
    private Set<Vehicle> inventory; // all vehicles at this station
    
    // Waitlists for each vehicle type
    private Queue<String> waitlistbicy; // people waiting for bikes
    private Queue<String> waitlistscoo; // people waiting for scooters  
    private Queue<String> waitlistskate; // people waiting for skateboards // first set of 3 
    
    private Scanner scanner = new Scanner(System.in);
    private ServiceHoursManager serviceHoursManager;

    // Constructor - just basic setup
    public Station(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.inventory = new HashSet<>(); // using HashSet for fast lookups
        this.waitlistbicy = new LinkedList<>();
        this.waitlistscoo = new LinkedList<>();
        this.waitlistskate = new LinkedList<>(); // heres the second set of 3
        this.serviceHoursManager = ServiceHoursManager.getInstance();
    }

    // Some basic getters
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    // Now the main methods from our interface

    @Override
    public void makeReservation(HashMap<String, Operator> operatorMap, boolean isModify, String modId) {
        // Can't do this outside service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        System.out.println("\nMaking reservation at " + this.name + " station");
        
        // Get client ID
        String clientID;
        if (isModify && modId != null) {
            clientID = modId;
        } else {
            System.out.print("Enter client ID: ");
            clientID = scanner.nextLine().trim();
        }
        
        // Make sure client exists and is actually a client
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client ID not found in the system.");
            return;
        }
        
        if (!client.isClient() && !client.isBoth()) {
            System.out.println("[x] User with ID " + clientID + " is not registered as a client.");
            return;
        }
        
        // Can only have 1 reservation at a time
        if (!isModify && client.getReserved() >= 1) {
            System.out.println("[x] Client already has an active reservation. Only one reservation per client is allowed.");
            return;
        }
        
        // Find out what they want to reserve
        System.out.println("\nWhat type of vehicle do you want to reserve?");
        System.out.println("1 - Bicycle (3 credits first hour, 2 credits additional hours)");
        System.out.println("2 - Scooter (2 credits first hour, 1 credit additional hours)");
        System.out.println("3 - Skateboard (1 credit first hour, 0.5 credits additional hours)");
        
        int vehicleType;
        while (true) {
            try {
                System.out.print("Your choice: ");
                vehicleType = Integer.parseInt(scanner.nextLine().trim());
                if (vehicleType >= 1 && vehicleType <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a value between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        String vehicleTypeStr;
        List<Vehicle> availableVehicles = new ArrayList<>();
        
        // Find which vehicles are available based on their type
        switch (vehicleType) {
            case 1:
                vehicleTypeStr = "Bicycle";
                for (Vehicle v : inventory) {
                    if (v.getType().equals("Bicycle") && v.isAvailable()) {
                        availableVehicles.add(v);
                    }
                }
                break;
            case 2:
                vehicleTypeStr = "Scooter";
                for (Vehicle v : inventory) {
                    if (v.getType().equals("Scooter") && v.isAvailable()) {
                        availableVehicles.add(v);
                    }
                }
                break;
            case 3:
                vehicleTypeStr = "Skateboard";
                for (Vehicle v : inventory) {
                    if (v.getType().equals("Skateboard") && v.isAvailable()) {
                        availableVehicles.add(v);
                    }
                }
                break;
            default:
                System.out.println("[x] Invalid vehicle type.");
                return;
        }
        
        // Check if there are any available
        if (availableVehicles.isEmpty()) {
            System.out.println("[x] No available " + vehicleTypeStr + " at " + this.name + " station.");
            System.out.println("Would you like to be added to the waitlist? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes")) {
                addToWaitlist(operatorMap, vehicleTypeStr, clientID);
            }
            return;
        }
        
        // Show the available options
        System.out.println("\nAvailable " + vehicleTypeStr + "s at " + this.name + " station:");
        for (int i = 0; i < availableVehicles.size(); i++) {
            Vehicle v = availableVehicles.get(i);
            Operator owner = operatorMap.get(v.getOwnerid());
            String ownerName = (owner != null) ? owner.getName() : "Unknown";
            System.out.println((i+1) + " - ID: " + v.getId() + 
                             ", Description: " + v.getDescription() + 
                             ", Owner: " + ownerName);
        }
        
        // Let them choose which one
        int selection;
        while (true) {
            try {
                System.out.print("Your choice (0 to cancel): ");
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection >= 0 && selection <= availableVehicles.size()) {
                    break;
                } else {
                    System.out.println("Please enter a value between 0 and " + availableVehicles.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        if (selection == 0) {
            System.out.println("Reservation cancelled.");
            return;
        }
        
        Vehicle selectedVehicle = availableVehicles.get(selection-1);
        
        // Get times for the reservation
        System.out.println("\nEnter reservation details:");
        
        LocalDateTime startTime;
        LocalDateTime endTime;
        
        while (true) {
            try {
                System.out.println("Start time (format: yyyy-MM-dd HH:mm):");
                String startInput = scanner.nextLine().trim();
                startTime = LocalDateTime.parse(startInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                
                System.out.println("End time (format: yyyy-MM-dd HH:mm):");
                String endInput = scanner.nextLine().trim();
                endTime = LocalDateTime.parse(endInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                
                // Validate times - lots of checks!
                if (startTime.isAfter(endTime)) {
                    System.out.println("[x] Start time must be before end time.");
                    continue;
                }
                
                // Can't reserve in the past
                LocalDateTime now = LocalDateTime.now();
                if (startTime.isBefore(now)) {
                    System.out.println("[x] Cannot make reservations in the past. Start time must be after current time.");
                    System.out.println("    Current time: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    continue;
                }
                
                // Must be during service hours
                if (startTime.getHour() < 7 || endTime.getHour() > 18) {
                    System.out.println("[x] Reservation must be within service hours (7:00 AM - 6:00 PM).");
                    continue;
                }
                
                // Max 6 hours
                long durationHours = Duration.between(startTime, endTime).toHours();
                if (durationHours > 6) {
                    System.out.println("[x] Reservation duration cannot exceed 6 hours.");
                    continue;
                }
                
                break;
            } catch (DateTimeParseException e) {
                System.out.println("[x] Invalid date format. Please use yyyy-MM-dd HH:mm");
            }
        }
        
        // Calculate cost based on duration and vehicle type
        double cost = 0.0;
        long durationMinutes = Duration.between(startTime, endTime).toMinutes();
        
        switch (vehicleTypeStr) {
            case "Bicycle":
                if (availableVehicles.get(selection-1) instanceof Bicycle) {
                    Bicycle bike = (Bicycle) availableVehicles.get(selection-1);
                    cost = bike.calculateCost(durationMinutes);
                }
                break;
            case "Scooter":
                if (availableVehicles.get(selection-1) instanceof Scooter) {
                    Scooter scooter = (Scooter) availableVehicles.get(selection-1);
                    cost = scooter.calculateCost(durationMinutes);
                }
                break;
            case "Skateboard":
                if (availableVehicles.get(selection-1) instanceof Skateboard) {
                    Skateboard skateboard = (Skateboard) availableVehicles.get(selection-1);
                    cost = skateboard.calculateCost(durationMinutes);
                }
                break;
        }
        
        // Show summary and confirm
        System.out.println("\nReservation Details:");
        System.out.println("Client: " + client.getName() + " (ID: " + clientID + ")");
        System.out.println("Vehicle: " + selectedVehicle.getType() + " (ID: " + selectedVehicle.getId() + ")");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Duration: " + durationMinutes + " minutes");
        System.out.println("Cost: " + cost + " credits");
        
        System.out.println("\nConfirm reservation? (yes/no)");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("yes")) {
            System.out.println("Reservation cancelled.");
            return;
        }
        
        // All good! Create the reservation
        Reservation reservation = new Reservation(clientID, selectedVehicle.getId(), startTime, endTime, this.name);
        reservation.setCost(cost);
        
        // Update vehicle availability
        selectedVehicle.setAvailability(false);
        
        // Update client's reservation count
        client.setReserved(client.getReserved() + 1);
        
        // Show success message
        System.out.println("[^.^]Reservation created successfully!");
        System.out.println("   Reservation ID: " + reservation.getReservationId());
        System.out.println("   Vehicle: " + selectedVehicle.getType() + " (ID: " + selectedVehicle.getId() + ")");
        System.out.println("   Start Time: " + startTime);
        System.out.println("   End Time: " + endTime);
        System.out.println("   Cost: " + cost + " credits");
    }

    /**
     * Helper method to add someone to waitlist
     */
    private void addToWaitlist(HashMap<String, Operator> operatorMap, String vehicleType, String clientID) {
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client not found.");
            return;
        }
        
        // Add to right waitlist based on vehicle type
        switch (vehicleType.toLowerCase()) {
            case "bicycle":
                waitlistbicy.add(clientID);
                break;
            case "scooter":
                waitlistscoo.add(clientID);
                break;
            case "skateboard":
                waitlistskate.add(clientID);
                break;
            default:
                System.out.println("[x] Invalid vehicle type for waitlist.");
                return;
        }
        
        System.out.println("[^.^]" + client.getName() + " added to the waitlist for " + vehicleType + " at " + this.name + " station.");
        System.out.println("   You will be notified when a vehicle becomes available.");
    }
    
    @Override
    public void modifyReservation(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        System.out.println("\nModifying reservation at " + this.name + " station");
        
        // Get reservation ID or client ID
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine().trim();
        
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client ID not found in the system.");
            return;
        }
        
        // For a real implementation, we would look up the client's reservations
        // For now, just call makeReservation with isModify=true
        makeReservation(operatorMap, true, clientID);
    }
    
    @Override
    public void cancelReservation(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        System.out.println("\nCancelling reservation at " + this.name + " station");
        
        // Get client ID
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine().trim();
        
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client ID not found in the system.");
            return;
        }
        
        // For a real implementation, we would look up the client's reservations
        // For now, just simulate a cancellation
        
        if (client.getReserved() <= 0) {
            System.out.println("[x] Client has no active reservations.");
            return;
        }
        
        // Update client's reservation count
        client.setReserved(client.getReserved() - 1);
        
        System.out.println("[^.^]Reservation cancelled successfully!");
        
        // Process waitlist if applicable
        processWaitlist(operatorMap);
    }
    
    @Override
    public void getVehicleAvailability(String station, LocalDateTime time) {
        System.out.println("\nChecking vehicle availability at " + this.name + " station for " + time);
        
        // Count available vehicles by type
        int availableBicycles = 0;
        int availableScooters = 0;
        int availableSkateboards = 0;
        
        for (Vehicle v : inventory) {
            if (v.isAvailable()) {
                switch (v.getType()) {
                    case "Bicycle":
                        availableBicycles++;
                        break;
                    case "Scooter":
                        availableScooters++;
                        break;
                    case "Skateboard":
                        availableSkateboards++;
                        break;
                }
            }
        }
        
        System.out.println("\nAvailable vehicles at " + this.name + " station:");
        System.out.println("Bicycles: " + availableBicycles);
        System.out.println("Scooters: " + availableScooters);
        System.out.println("Skateboards: " + availableSkateboards);
        System.out.println("Total: " + (availableBicycles + availableScooters + availableSkateboards) + 
                         " / " + currentOccupancy);
        
        // Show waitlist status
        System.out.println("\nWaitlist status:");
        System.out.println("Bicycle waitlist: " + waitlistbicy.size() + " people waiting");
        System.out.println("Scooter waitlist: " + waitlistscoo.size() + " people waiting");
        System.out.println("Skateboard waitlist: " + waitlistskate.size() + " people waiting");
    }
    
    @Override
    public void addToWaitlist(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        System.out.println("\nAdding to waitlist at " + this.name + " station");
        
        // Get client ID
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine().trim();
        
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client ID not found in the system.");
            return;
        }
        
        if (!client.isClient() && !client.isBoth()) {
            System.out.println("[x] User with ID " + clientID + " is not registered as a client.");
            return;
        }
        
        // Select vehicle type for waitlist
        System.out.println("\nWhat type of vehicle do you want to waitlist for?");
        System.out.println("1 - Bicycle");
        System.out.println("2 - Scooter");
        System.out.println("3 - Skateboard");
        
        int vehicleType;
        while (true) {
            try {
                System.out.print("Your choice: ");
                vehicleType = Integer.parseInt(scanner.nextLine().trim());
                if (vehicleType >= 1 && vehicleType <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a value between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        String vehicleTypeStr;
        switch (vehicleType) {
            case 1:
                vehicleTypeStr = "Bicycle";
                waitlistbicy.add(clientID);
                break;
            case 2:
                vehicleTypeStr = "Scooter";
                waitlistscoo.add(clientID);
                break;
            case 3:
                vehicleTypeStr = "Skateboard";
                waitlistskate.add(clientID);
                break;
            default:
                System.out.println("[x] Invalid vehicle type.");
                return;
        }
        
        System.out.println("[^.^]" + client.getName() + " added to the waitlist for " + vehicleTypeStr + " at " + this.name + " station.");
        System.out.println("   Current position in waitlist: " + (vehicleType == 1 ? waitlistbicy.size() : 
                                                               (vehicleType == 2 ? waitlistscoo.size() : 
                                                                waitlistskate.size())));
    }
    
    /**
     * Process waitlist when a vehicle becomes available
     */
    private void processWaitlist(HashMap<String, Operator> operatorMap) {
        // Process bicycle waitlist
        processWaitlistForType(operatorMap, "Bicycle", waitlistbicy);
        
        // Process scooter waitlist
        processWaitlistForType(operatorMap, "Scooter", waitlistscoo);
        
        // Process skateboard waitlist
        processWaitlistForType(operatorMap, "Skateboard", waitlistskate);
    }

    // HashMap to store confirmation timers for waiting clients (clientId -> timestamp)
    private HashMap<String, LocalDateTime> confirmationTimers = new HashMap<>();

    /**
     * Process waitlist for a specific vehicle type
     * 
     * @param operatorMap The map of operators
     * @param vehicleType The type of vehicle
     * @param waitlist The waitlist queue for this vehicle type
     */
    private void processWaitlistForType(HashMap<String, Operator> operatorMap, String vehicleType, Queue<String> waitlist) {
        if (waitlist.isEmpty()) {
            return; // No one waiting for this type
        }

        // Find an available vehicle of this type
        Vehicle availableVehicle = null;
        for (Vehicle v : inventory) {
            if (v.getType().equals(vehicleType) && v.isAvailable()) {
                availableVehicle = v;
                break;
            }
        }

        if (availableVehicle == null) {
            return; // No available vehicle of this type
        }

        // Get next client from waitlist but don't remove them yet
        String nextClientId = waitlist.peek();
        if (nextClientId == null) {
            return;
        }

        Operator nextClient = operatorMap.get(nextClientId);
        if (nextClient == null) {
            // Invalid client, remove from waitlist and try next
            waitlist.poll();
            System.out.println("[x] Error: Client ID " + nextClientId + " on waitlist not found in system.");
            processWaitlistForType(operatorMap, vehicleType, waitlist);
            return;
        }

        // Check if client already has a reservation
        if (nextClient.getReserved() >= 1) {
            // Client already has a reservation, remove from waitlist and try next
            waitlist.poll();
            System.out.println("[i] Client " + nextClient.getName() + " already has an active reservation.");
            System.out.println("    Skipping and trying next client on waitlist.");
            processWaitlistForType(operatorMap, vehicleType, waitlist);
            return;
        }

        // Check if this client already has a timer running
        if (confirmationTimers.containsKey(nextClientId)) {
            LocalDateTime timerStarted = confirmationTimers.get(nextClientId);
            LocalDateTime now = LocalDateTime.now();
            
            // Check if 5 minutes have passed since notifying the client
            if (now.isAfter(timerStarted.plusMinutes(5))) {
                // Time's up - client didn't confirm within 5 minutes
                System.out.println("[i] Client " + nextClient.getName() + " (ID: " + nextClientId + ") did not confirm within 5 minutes.");
                System.out.println("    Removing from waitlist and notifying next client.");
                
                // Remove this client from the waitlist and from timers
                waitlist.poll();
                confirmationTimers.remove(nextClientId);
                
                // Try with the next client in line
                processWaitlistForType(operatorMap, vehicleType, waitlist);
                return;
            }
            
            // Timer is still running, do nothing until it expires
            System.out.println("[i] Client " + nextClient.getName() + " still has time to confirm the reservation.");
            System.out.println("    Time remaining: " + 
                             Duration.between(now, timerStarted.plusMinutes(5)).toSeconds() + " seconds");
            return;
        }
        
        // Start a timer for this client
        confirmationTimers.put(nextClientId, LocalDateTime.now());
        
        // Notify the client
        System.out.println("\n[^.^] Vehicle available notification for waitlisted client:");
        System.out.println("   Client: " + nextClient.getName() + " (ID: " + nextClientId + ")");
        System.out.println("   A " + vehicleType + " (ID: " + availableVehicle.getId() + ") is now available.");
        System.out.println("   You have 5 minutes to confirm your reservation.");
        System.out.println("   Please use the \"Confirm Waitlist Reservation\" option in the Waitlist Management menu.");
        System.out.println("   If not confirmed within 5 minutes, the opportunity will pass to the next person in line.");
    }

    /**
     * Confirms a reservation for a waitlisted client
     * 
     * @param clientId The ID of the client confirming the reservation
     * @param operatorMap The map of operators
     * @return true if confirmation was successful, false otherwise
     */
    public boolean confirmWaitlistReservation(String clientId, HashMap<String, Operator> operatorMap) {
        // Check if this client has a pending confirmation
        if (!confirmationTimers.containsKey(clientId)) {
            System.out.println("[x] No pending reservation confirmation for client ID " + clientId);
            return false;
        }
        
        // Find which waitlist the client is in
        String vehicleType = null;
        Queue<String> clientWaitlist = null;
        
        if (waitlistbicy.peek() != null && waitlistbicy.peek().equals(clientId)) {
            vehicleType = "Bicycle";
            clientWaitlist = waitlistbicy;
        } else if (waitlistscoo.peek() != null && waitlistscoo.peek().equals(clientId)) {
            vehicleType = "Scooter";
            clientWaitlist = waitlistscoo;
        } else if (waitlistskate.peek() != null && waitlistskate.peek().equals(clientId)) {
            vehicleType = "Skateboard";
            clientWaitlist = waitlistskate;
        }
        
        if (vehicleType == null || clientWaitlist == null) {
            System.out.println("[x] Client is not first in any waitlist.");
            confirmationTimers.remove(clientId);
            return false;
        }
        
        // Find an available vehicle of this type
        Vehicle availableVehicle = null;
        for (Vehicle v : inventory) {
            if (v.getType().equals(vehicleType) && v.isAvailable()) {
                availableVehicle = v;
                break;
            }
        }
        
        if (availableVehicle == null) {
            System.out.println("[x] No available " + vehicleType + " found. Vehicle was taken by someone else.");
            confirmationTimers.remove(clientId);
            clientWaitlist.poll(); // Remove from waitlist
            return false;
        }
        
        // Get client from map
        Operator client = operatorMap.get(clientId);
        if (client == null) {
            System.out.println("[x] Client not found in system.");
            confirmationTimers.remove(clientId);
            clientWaitlist.poll();
            return false;
        }
        
        // Client confirmed within time limit - create reservation
        // Remove from waitlist and clear timer
        clientWaitlist.poll();
        confirmationTimers.remove(clientId);
        
        // Create a reservation for the next available time slot (starting now)
        LocalDateTime now = LocalDateTime.now();
        
        // If we're outside service hours, schedule for next valid time
        LocalDateTime startTime = now;
        
        // Round up to the nearest 15 minutes for better time slots
        int minutes = startTime.getMinute();
        int minutesToAdd = 0;
        if (minutes % 15 != 0) {
            minutesToAdd = 15 - (minutes % 15);
        }
        startTime = startTime.plusMinutes(minutesToAdd).withSecond(0).withNano(0);
        
        // If outside service hours (7AM-6PM), reschedule
        if (startTime.getHour() < 7 || startTime.getHour() >= 18) {
            // If it's evening, schedule for tomorrow morning
            if (startTime.getHour() >= 18) {
                startTime = startTime.plusDays(1).withHour(9).withMinute(0);
            } else {
                // It's early morning, schedule for today at 9AM
                startTime = startTime.withHour(9).withMinute(0);
            }
        }
        
        // If it's weekend, schedule for Monday
        if (startTime.getDayOfWeek().getValue() > 5) {
            startTime = startTime.plusDays(8 - startTime.getDayOfWeek().getValue()).withHour(9).withMinute(0);
        }
        
        // Default reservation duration: 1 hour
        LocalDateTime endTime = startTime.plusHours(1);
        
        // Calculate cost
        double cost = 0.0;
        switch (vehicleType) {
            case "Bicycle":
                cost = 3.0; // First hour cost for bicycle
                break;
            case "Scooter":
                cost = 2.0; // First hour cost for scooter
                break;
            case "Skateboard":
                cost = 1.0; // First hour cost for skateboard
                break;
        }

        // Create reservation
        Reservation reservation = new Reservation(clientId, availableVehicle.getId(), 
                                                 startTime, endTime, this.name);
        reservation.setCost(cost);
        
        // Update vehicle status
        availableVehicle.setAvailability(false);
        
        // Update client's reservation count
        client.setReserved(client.getReserved() + 1);

        // Notify the client
        System.out.println("\n[^.^] Reservation confirmed successfully for waitlisted client:");
        System.out.println("   Client: " + client.getName() + " (ID: " + clientId + ")");
        System.out.println("   Vehicle: " + availableVehicle.getType() + " (ID: " + availableVehicle.getId() + ")");
        System.out.println("   Start Time: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("   End Time: " + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("   Cost: " + cost + " credits");
        
        return true;
    }
    
    //--------------------------------------------------
    // Vehicle Management Implementation
    //--------------------------------------------------
    
    @Override
    public void addVehicle(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }
        
        System.out.println("\nAdding vehicle to " + this.name + " station");
        
        // Check if station is at capacity
        if (currentOccupancy >= capacity) {
            System.out.println("[x] Station is at full capacity (" + capacity + " vehicles). Cannot add more vehicles.");
            return;
        }
        
        System.out.println("\nWhat type of vehicle do you want to add?");
        System.out.println("1 - Bicycle");
        System.out.println("2 - Scooter");
        System.out.println("3 - Skateboard");
        
        int vehicleType;
        while (true) {
            try {
                System.out.print("Your choice: ");
                vehicleType = Integer.parseInt(scanner.nextLine().trim());
                if (vehicleType >= 1 && vehicleType <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a value between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        // Get owner ID
        System.out.print("Enter owner ID: ");
        String ownerID = scanner.nextLine().trim();
        
        // Verify owner exists and is an owner
        Operator owner = operatorMap.get(ownerID);
        if (owner == null) {
            System.out.println("[x] Owner ID not found in the system.");
            return;
        }
        
        if (!owner.isOwner() && !owner.isBoth()) {
            System.out.println("[x] User with ID " + ownerID + " is not registered as an owner.");
            return;
        }
        
        // Check if owner already has 2 vehicles (max allowed)
        if (owner.getRegistered() >= 2) {
            System.out.println("[x] Owner already has " + owner.getRegistered() + " vehicles registered.");
            System.out.println("    Maximum of 2 vehicles per owner is allowed.");
            return;
        }
        
        // Get vehicle description
        System.out.print("Enter vehicle description: ");
        String description = scanner.nextLine().trim();
        
        // Create the vehicle based on type
        Vehicle newVehicle = null;
        switch (vehicleType) {
            case 1:
                newVehicle = new Bicycle(description, this.name, ownerID);
                break;
            case 2:
                newVehicle = new Scooter(description, this.name, ownerID);
                break;
            case 3:
                newVehicle = new Skateboard(description, this.name, ownerID);
                break;
        }
        
        // Add vehicle to inventory
        if (newVehicle != null) {
            inventory.add(newVehicle);
            currentOccupancy++;
            
            // Update owner's vehicle list
            owner.addOwnedVehicle(newVehicle.getId());
            
            System.out.println("[^.^]Vehicle added successfully!");
            System.out.println("   Vehicle ID: " + newVehicle.getId());
            System.out.println("   Type: " + newVehicle.getType());
            System.out.println("   Description: " + newVehicle.getDescription());
            System.out.println("   Owner: " + owner.getName() + " (ID: " + ownerID + ")");
            System.out.println("   Location: " + this.name + " station");
        } else {
            System.out.println("[x] Failed to create vehicle.");
        }
    }
    
    @Override
    public void removeVehicle(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }
        
        System.out.println("\nRemoving vehicle from " + this.name + " station");
        
        // Get owner ID
        System.out.print("Enter owner ID: ");
        String ownerID = scanner.nextLine().trim();
        
        // Verify owner exists and is an owner
        Operator owner = operatorMap.get(ownerID);
        if (owner == null) {
            System.out.println("[x] Owner ID not found in the system.");
            return;
        }
        
        if (!owner.isOwner() && !owner.isBoth()) {
            System.out.println("[x] User with ID " + ownerID + " is not registered as an owner.");
            return;
        }
        
        // Get owner's vehicles at this station
        List<Vehicle> ownerVehicles = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOwnerid().equals(ownerID)) {
                ownerVehicles.add(v);
            }
        }
        
        if (ownerVehicles.isEmpty()) {
            System.out.println("[x] No vehicles found for this owner at " + this.name + " station.");
            return;
        }
        
        // Show owner's vehicles
        System.out.println("\nVehicles owned by " + owner.getName() + " at " + this.name + " station:");
        for (int i = 0; i < ownerVehicles.size(); i++) {
            Vehicle v = ownerVehicles.get(i);
            System.out.println((i+1) + " - ID: " + v.getId() + 
                            ", Type: " + v.getType() + 
                            ", Description: " + v.getDescription());
        }
        
        // Let owner select a vehicle to remove
        int selection;
        while (true) {
            try {
                System.out.print("Select vehicle to remove (0 to cancel): ");
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection >= 0 && selection <= ownerVehicles.size()) {
                    break;
                } else {
                    System.out.println("Please enter a value between 0 and " + ownerVehicles.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        if (selection == 0) {
            System.out.println("Operation cancelled.");
            return;
        }
        
        Vehicle selectedVehicle = ownerVehicles.get(selection-1);
        
        // Check if vehicle is currently reserved
        if (!selectedVehicle.isAvailable()) {
            System.out.println("[x] Cannot remove vehicle that is currently reserved.");
            return;
        }
        
        // Remove vehicle from inventory
        inventory.remove(selectedVehicle);
        currentOccupancy--;
        
        // Update owner's vehicle list
        owner.removeOwnedVehicle(selectedVehicle.getId());
        
        System.out.println("[^.^]Vehicle removed successfully!");
        System.out.println("   Vehicle ID: " + selectedVehicle.getId());
        System.out.println("   Type: " + selectedVehicle.getType());
        System.out.println("   Description: " + selectedVehicle.getDescription());
    }
    
    @Override
    public void modifyVehicle(HashMap<String, Operator> operatorMap) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }
        
        System.out.println("\nModifying vehicle at " + this.name + " station");
        
        // Get owner ID
        System.out.print("Enter owner ID: ");
        String ownerID = scanner.nextLine().trim();
        
        // Verify owner exists and is an owner
        Operator owner = operatorMap.get(ownerID);
        if (owner == null) {
            System.out.println("[x] Owner ID not found in the system.");
            return;
        }
        
        if (!owner.isOwner() && !owner.isBoth()) {
            System.out.println("[x] User with ID " + ownerID + " is not registered as an owner.");
            return;
        }
        
        // Get owner's vehicles at this station
        List<Vehicle> ownerVehicles = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOwnerid().equals(ownerID)) {
                ownerVehicles.add(v);
            }
        }
        
        if (ownerVehicles.isEmpty()) {
            System.out.println("[x] No vehicles found for this owner at " + this.name + " station.");
            return;
        }
        
        // Show owner's vehicles
        System.out.println("\nVehicles owned by " + owner.getName() + " at " + this.name + " station:");
        for (int i = 0; i < ownerVehicles.size(); i++) {
            Vehicle v = ownerVehicles.get(i);
            System.out.println((i+1) + " - ID: " + v.getId() + 
                            ", Type: " + v.getType() + 
                            ", Description: " + v.getDescription() + 
                            ", Available: " + (v.isAvailable() ? "Yes" : "No"));
        }
        
        // Let owner select a vehicle to modify
        int selection;
        while (true) {
            try {
                System.out.print("Select vehicle to modify (0 to cancel): ");
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection >= 0 && selection <= ownerVehicles.size()) {
                    break;
                } else {
                    System.out.println("Please enter a value between 0 and " + ownerVehicles.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        if (selection == 0) {
            System.out.println("Operation cancelled.");
            return;
        }
        
        Vehicle selectedVehicle = ownerVehicles.get(selection-1);
        
        // Options for modification are limited due to vehicle implementation
        System.out.println("\nModification options:");
        System.out.println("1 - Change description");
        System.out.println("2 - Set availability");
        System.out.println("0 - Cancel");
        
        int option;
        while (true) {
            try {
                System.out.print("Your choice: ");
                option = Integer.parseInt(scanner.nextLine().trim());
                if (option >= 0 && option <= 2) {
                    break;
                } else {
                    System.out.println("Please enter a value between 0 and 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        switch (option) {
            case 0:
                System.out.println("Operation cancelled.");
                break;
            case 1:
                // We can't modify description directly as it's a final field in the vehicle implementations
                System.out.println("[x] Changing description is not supported in the current implementation.");
                System.out.println("    You need to remove the vehicle and add a new one with the updated description.");
                break;
            case 2:
                System.out.print("Set availability (true/false): ");
                String availInput = scanner.nextLine().trim().toLowerCase();
                boolean availability = availInput.equals("true");
                
                // Cannot set a reserved vehicle to available this way
                if (availability && !selectedVehicle.isAvailable()) {
                    System.out.println("[x] Cannot set a reserved vehicle to available.");
                    System.out.println("    The reservation must be cancelled first.");
                } else {
                    selectedVehicle.setAvailability(availability);
                    System.out.println("[^.^]Vehicle availability updated successfully!");
                    System.out.println("   Vehicle ID: " + selectedVehicle.getId());
                    System.out.println("   New availability: " + (selectedVehicle.isAvailable() ? "Available" : "Not Available"));
                }
                break;
        }
    }
    
    @Override
    public void listVehicle(HashMap<String, Operator> operatorMap) {
        System.out.println("\nListing vehicles at " + this.name + " station");
        
        if (inventory.isEmpty()) {
            System.out.println("[i] No vehicles at this station.");
            return;
        }
        
        // Count vehicles by type
        int bicycleCount = 0;
        int scooterCount = 0;
        int skateboardCount = 0;
        
        for (Vehicle v : inventory) {
            switch (v.getType()) {
                case "Bicycle":
                    bicycleCount++;
                    break;
                case "Scooter":
                    scooterCount++;
                    break;
                case "Skateboard":
                    skateboardCount++;
                    break;
            }
        }
        
        System.out.println("\nVehicle Distribution:");
        System.out.println("Bicycles: " + bicycleCount);
        System.out.println("Scooters: " + scooterCount);
        System.out.println("Skateboards: " + skateboardCount);
        System.out.println("Total: " + inventory.size() + " / " + capacity);
        
        // Show all vehicles
        System.out.println("\nAll vehicles at " + this.name + " station:");
        int count = 1;
        for (Vehicle v : inventory) {
            Operator owner = operatorMap.get(v.getOwnerid());
            String ownerName = (owner != null) ? owner.getName() : "Unknown";
            
            System.out.println(count + " - ID: " + v.getId() + 
                            ", Type: " + v.getType() + 
                            ", Description: " + v.getDescription() + 
                            ", Owner: " + ownerName + 
                            ", Available: " + (v.isAvailable() ? "Yes" : "No"));
            count++;
        }
    }

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------
    
    // Helper methods for waitlist management
    public Queue<String> getWaitlistBicycle() {
        return waitlistbicy;
    }
    
    public Queue<String> getWaitlistScooter() {
        return waitlistscoo;
    }
    
    public Queue<String> getWaitlistSkateboard() {
        return waitlistskate;
    }
    
    // Debug method to see what's in inventory
    public void printInventory() {
        System.out.println(inventory);
    }
}
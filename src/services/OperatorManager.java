package services;

import interfaces.OperatorManagement;
import java.time.*;
import java.util.*;
import models.Operator;

/**
 * USER MANAGEMENT IMPLEMENTATION
 * 
 * This class implements the OperatorManagement interface to handle
 * all user/operator operations defined in the system.
 * 
 * Data structures used:
 * - Queue: To maintain a FIFO list of operators during batch processing
 * - HashMap: To store registered operators with quick lookup by ID
 */
public class OperatorManager implements OperatorManagement {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private Queue<Operator> operatorQueue = new LinkedList<>();
    private Map<String, Operator> registeredOperators = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
    @Override
    public void addOperator(Operator operator) {
        if (!isWithinServiceHours()) {
            System.out.println("The available service hours will be from 7:00 AM to 6:00 PM, Monday to Friday.");
            return;
        }

        boolean addingOperators = true;

        while (addingOperators) {
            Operator newOperator = createOperatorFromInput();
            operatorQueue.add(newOperator);
            
            // Also add to the registered operators map for future reference
            registeredOperators.put(newOperator.getId(), newOperator);
            
            newOperator.display();

            System.out.println("\nAdd another operator? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                addingOperators = false;
            }
        }

        System.out.println("\nAll Operators in Queue (FIFO):");
        for (Operator op : operatorQueue) {
            System.out.println("- " + op.getName() + " (" + op.getId() + ")");
        }
    }

    //--------------------------------------------------
    // Helper Methods
    //--------------------------------------------------
    /**
     * Creates a new Operator from user input via the console.
     * Collects all necessary information including role-specific details.
     * 
     * @return A new Operator object populated with user input
     */
    private Operator createOperatorFromInput() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter your UPR Email: ");
        String uprEmail = scanner.nextLine();

        System.out.print("Enter your Phone Number: ");
        String phoneNum = scanner.nextLine();

        System.out.println("What would you like to do?");
        System.out.println("Press 1 to reserve a vehicle");
        System.out.println("Press 2 to register a vehicle");
        System.out.println("Press 3 to do both");
    
        int action = Integer.parseInt(scanner.nextLine());
        boolean isClient = false;
        boolean isOwner = false;
        boolean isBoth = false;
    
        switch (action) {
            case 1 -> isClient = true;
            case 2 -> isOwner = true;
            case 3 -> {
                isClient = true;
                isOwner = true;
                isBoth = true;
            }
            default -> System.out.println("Invalid option. Defaulting to reservation only.");
        }

        System.out.print("Enter initial credit amount (e.g. 50.00): ");
        double credit = Double.parseDouble(scanner.nextLine());

        Operator operator = new Operator(name, id, uprEmail, isOwner, isClient, isBoth, phoneNum);
        operator.setCredit(credit);

        // Ask appropriate questions based on role
        if (isBoth) {
            System.out.println("Would you like to:");
            System.out.println("1. Make a reservation");
            System.out.println("2. Register a vehicle");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.println("Proceeding with reservation...");
                operator.setReserved(operator.getReserved() + 1);
                // Reservation logic would be implemented here
            } else if (choice == 2) {
                System.out.println("Proceeding with vehicle registration...");
                registerVehicleForOperator(operator);
            } else {
                System.out.println("Invalid choice. No action taken.");
            }
        } else if (isClient) {
            System.out.println("Would you like to make a reservation? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                System.out.println("Proceeding with reservation...");
                operator.setReserved(operator.getReserved() + 1);
                // Reservation logic would be implemented here
            } else {
                System.out.println("No reservation made.");
            }
        } else if (isOwner) {
            System.out.println("Would you like to register your vehicle? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                System.out.println("Proceeding with vehicle registration...");
                registerVehicleForOperator(operator);
            } else {
                System.out.println("No vehicle registered.");
            }
        }

        return operator;
    }
    
    /**
     * Handles the vehicle registration process for an operator.
     * Enforces the constraint that an owner can register at most 2 vehicles.
     * 
     * @param operator The operator who wants to register a vehicle
     */
    private void registerVehicleForOperator(Operator operator) {
        // Check if operator already has 2 vehicles registered
        if (operator.getRegistered() >= 2) {
            System.out.println("You've already registered the maximum of 2 vehicles.");
            return;
        }
        
        System.out.println("Enter vehicle type (bicycle/scooter/skateboard): ");
        String vehicleType = scanner.nextLine().trim().toLowerCase();
        
        System.out.println("Enter a unique ID for your vehicle: ");
        String vehicleId = scanner.nextLine().trim();
        
        // Add vehicle to operator's owned vehicles
        boolean added = operator.addOwnedVehicle(vehicleId);
        
        if (added) {
            System.out.println("Vehicle registered successfully!");
        } else {
            System.out.println("Unable to register vehicle. You may have reached your limit.");
        }
    }

    /**
     * Checks if the current time is within service hours.
     * Service hours are 7:00 AM to 6:00 PM, Monday to Friday.
     * 
     * @return true if current time is within service hours, false otherwise
     */
    private boolean isWithinServiceHours() {
        ZonedDateTime now = ZonedDateTime.now();
        DayOfWeek day = now.getDayOfWeek();
        LocalTime start = LocalTime.of(7, 0);
        LocalTime end = LocalTime.of(18, 0);
        LocalTime currentTime = now.toLocalTime();

        boolean isWeekday = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
        boolean isWithinTime = !currentTime.isBefore(start) && !currentTime.isAfter(end);

        return isWeekday && isWithinTime;
    }

    //--------------------------------------------------
    // Interface Methods Implementation (continued)
    //--------------------------------------------------
    @Override
    public void modifyOperator(String studentID, Operator updatedOperator) {
        if (registeredOperators.containsKey(studentID)) {
            registeredOperators.put(studentID, updatedOperator);
            System.out.println("Operator with ID " + studentID + " has been updated.");
        } else {
            System.out.println("No operator found with ID: " + studentID);
        }
    }

    @Override
    public void deleteOperator(String studentID) {
        if (registeredOperators.containsKey(studentID)) {
            registeredOperators.remove(studentID);
            System.out.println("Operator with ID " + studentID + " has been deleted.");
        } else {
            System.out.println("No operator found with ID: " + studentID);
        }
    }

    @Override
    public void listRegisteredOperators() {
        if (registeredOperators.isEmpty()) {
            System.out.println("No operators are currently registered.");
        } else {
            System.out.println("List of registered operators:");
            for (Operator op : registeredOperators.values()) {
                System.out.println("- " + op.getName() + " | ID: " + op.getId() + 
                                 " | Email: " + op.getUprEmail() +
                                 " | Role: " + (op.isBoth() ? "Client & Owner" : 
                                              (op.isClient() ? "Client" : "Owner")));
            }
        }
    }
}
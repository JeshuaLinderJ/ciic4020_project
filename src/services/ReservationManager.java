// filepath: /Users/jeshualinderjimenez/Desktop/UPRM/34-dsproject/ciic4020_project/src/services/ReservationManager.java
package services;

import interfaces.ReservationOperations;
import interfaces.UndoAction;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import models.Reservation;

/**
 * RESERVATION MANAGER CLASS
 * 
 * This class implements the ReservationOperations interface to handle reservation functionality.
 * It also implements UndoAction to provide undo functionality for reservations.
 * 
 * Data structures used:
 * - HashMap: To store active reservations with reservationID as key for quick lookup O(1)
 * - LinkedList: To maintain a chronological history of all reservations for reporting O(1) append
 * - Stack: To implement undo operations for reservations and cancellations O(1) push/pop
 * - Queue: For managing waitlists when no vehicles are available O(1) enqueue/dequeue
 */
public class ReservationManager implements ReservationOperations, UndoAction {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private HashMap<String, Reservation> activeReservations; // Maps reservationID to Reservation
    private HashMap<String, List<Reservation>> clientReservations; // Maps clientID to their reservations
    private LinkedList<Reservation> reservationHistory; // Chronological history of all reservations
    private Stack<Reservation> undoReservationStack; // For undoing reservations
    private Stack<Reservation> undoCancellationStack; // For undoing cancellations
    private Scanner scanner;
    private ServiceHoursManager serviceHoursManager;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public ReservationManager() {
        this.activeReservations = new HashMap<>();
        this.clientReservations = new HashMap<>();
        this.reservationHistory = new LinkedList<>();
        this.undoReservationStack = new Stack<>();
        this.undoCancellationStack = new Stack<>();
        this.scanner = new Scanner(System.in);
        this.serviceHoursManager = ServiceHoursManager.getInstance();
    }

    //--------------------------------------------------
    // ReservationOperations Implementation
    //--------------------------------------------------
    @Override
    public void makeReservation(String operatorID, String vehicleID, LocalDateTime start, LocalDateTime end) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        // Validate operator has no active reservations
        if (hasActiveReservation(operatorID)) {
            System.out.println("[x] Operator already has an active reservation. Only one reservation per client is allowed.");
            return;
        }

        // Create new reservation
        String stationLocation = ""; // This would come from the vehicle's location
        Reservation reservation = new Reservation(operatorID, vehicleID, start, end, stationLocation);

        // Validate reservation duration
        if (!reservation.isValidDuration()) {
            System.out.println("[x] Reservation duration exceeds the maximum allowed (6 hours).");
            return;
        }

        // Validate reservation is within service hours
        if (!reservation.isWithinServiceHours()) {
            System.out.println("[x] Reservation must be within service hours (7:00 AM - 6:00 PM, Mon-Fri).");
            return;
        }

        // Add to data structures
        activeReservations.put(reservation.getReservationId(), reservation);
        
        if (!clientReservations.containsKey(operatorID)) {
            clientReservations.put(operatorID, new ArrayList<>());
        }
        clientReservations.get(operatorID).add(reservation);
        
        reservationHistory.add(reservation);
        undoReservationStack.push(reservation);

        System.out.println("[^.^]Reservation created successfully!");
        System.out.println("   Reservation ID: " + reservation.getReservationId());
        System.out.println("   Vehicle ID: " + vehicleID);
        System.out.println("   Start time: " + start.format(formatter));
        System.out.println("   End time: " + end.format(formatter));
        System.out.println("   Duration: " + reservation.getDurationMinutes() + " minutes");
        // Cost would be calculated based on vehicle type
    }

    @Override
    public void modifyReservation(String reservationID, Reservation updated) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        if (!activeReservations.containsKey(reservationID)) {
            System.out.println("[x] Reservation with ID " + reservationID + " not found.");
            return;
        }

        Reservation original = activeReservations.get(reservationID);
        
        // Validate updated reservation
        if (!updated.isValidDuration()) {
            System.out.println("[x] Updated reservation duration exceeds the maximum allowed (6 hours).");
            return;
        }

        if (!updated.isWithinServiceHours()) {
            System.out.println("[x] Updated reservation must be within service hours (7:00 AM - 6:00 PM, Mon-Fri).");
            return;
        }

        // Update the reservation
        activeReservations.put(reservationID, updated);
        
        // Update client reservations list
        List<Reservation> clientReservationList = clientReservations.get(original.getOperatorId());
        if (clientReservationList != null) {
            for (int i = 0; i < clientReservationList.size(); i++) {
                if (clientReservationList.get(i).getReservationId().equals(reservationID)) {
                    clientReservationList.set(i, updated);
                    break;
                }
            }
        }
        
        // Add to history
        reservationHistory.add(updated);
        
        System.out.println("[^.^]Reservation modified successfully!");
        System.out.println("   Reservation ID: " + updated.getReservationId());
        System.out.println("   New start time: " + updated.getStartTime().format(formatter));
        System.out.println("   New end time: " + updated.getEndTime().format(formatter));
        System.out.println("   New duration: " + updated.getDurationMinutes() + " minutes");
    }

    @Override
    public void cancelReservation(String reservationID) {
        // Check service hours
        if (!serviceHoursManager.isWithinServiceHours()) {
            System.out.println("[x] Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        if (!activeReservations.containsKey(reservationID)) {
            System.out.println("[x] Reservation with ID " + reservationID + " not found.");
            return;
        }

        Reservation reservation = activeReservations.remove(reservationID);
        reservation.setActive(false);
        undoCancellationStack.push(reservation);

        // Update client reservations list
        List<Reservation> clientReservationList = clientReservations.get(reservation.getOperatorId());
        if (clientReservationList != null) {
            clientReservationList.removeIf(r -> r.getReservationId().equals(reservationID));
            if (clientReservationList.isEmpty()) {
                clientReservations.remove(reservation.getOperatorId());
            }
        }
        
        System.out.println("[^.^]Reservation cancelled successfully!");
        System.out.println("   Reservation ID: " + reservationID);
        
        // Here we would notify the next person on the waitlist if any
        // processWaitlist(reservation.getVehicleId(), reservation.getStationLocation());
    }

    @Override
    public void getVehicleAvailability(String station, LocalDateTime time) {
        System.out.println("\nChecking vehicle availability at " + station + " for " + time.format(formatter));
        
        // This would query the station's vehicle inventory and check reservations
        // For each vehicle, we'd check if it's available at the specified time
        
        // For now, just a placeholder message
        System.out.println("Vehicle availability check functionality to be implemented.");
    }

    @Override
    public void addToWaitlist(String operatorID, String vehicleType, String station) {
        System.out.println("\nAdding " + operatorID + " to waitlist for " + vehicleType + " at " + station);
        
        // This would add the client to the appropriate waitlist at the station
        // We would use the station's waitlist queues (waitlistbicy, waitlistscoo, waitlistskate)
        
        System.out.println("Waitlist functionality to be implemented within Station class.");
    }

    //--------------------------------------------------
    // UndoAction Implementation
    //--------------------------------------------------
    @Override
    public void undoLastReservation() {
        if (undoReservationStack.isEmpty()) {
            System.out.println("[x] No reservations to undo.");
            return;
        }

        Reservation lastReservation = undoReservationStack.pop();
        cancelReservation(lastReservation.getReservationId());
        System.out.println("[^.^]Last reservation undone successfully.");
    }

    @Override
    public void undoLastCancellation() {
        if (undoCancellationStack.isEmpty()) {
            System.out.println("[x] No cancellations to undo.");
            return;
        }

        Reservation lastCancelled = undoCancellationStack.pop();
        lastCancelled.setActive(true);
        activeReservations.put(lastCancelled.getReservationId(), lastCancelled);
        
        if (!clientReservations.containsKey(lastCancelled.getOperatorId())) {
            clientReservations.put(lastCancelled.getOperatorId(), new ArrayList<>());
        }
        clientReservations.get(lastCancelled.getOperatorId()).add(lastCancelled);
        
        System.out.println("[^.^]Last cancellation undone successfully. Reservation is now active again.");
    }

    //--------------------------------------------------
    // Helper Methods
    //--------------------------------------------------
    /**
     * Checks if an operator already has an active reservation
     * 
     * @param operatorID The ID of the operator to check
     * @return True if the operator has an active reservation, false otherwise
     */
    private boolean hasActiveReservation(String operatorID) {
        List<Reservation> reservations = clientReservations.get(operatorID);
        return reservations != null && !reservations.isEmpty();
    }

    /**
     * Processes the waitlist for a vehicle that just became available
     * 
     * @param vehicleID The ID of the vehicle that became available
     * @param stationLocation The location of the station
     */
    private void processWaitlist(String vehicleID, String stationLocation) {
        // This would pull the next client from the waitlist and notify them
        // Implementation would interact with the Station's waitlist queues
    }

    /**
     * Gets all reservations for a specific operator
     * 
     * @param operatorID The ID of the operator
     * @return A list of reservations for the operator
     */
    public List<Reservation> getOperatorReservations(String operatorID) {
        return clientReservations.getOrDefault(operatorID, new ArrayList<>());
    }

    /**
     * Displays reservation history for reporting purposes
     */
    public void displayReservationHistory() {
        if (reservationHistory.isEmpty()) {
            System.out.println("\nNo reservation history available.");
            return;
        }

        System.out.println("\n----- Reservation History -----");
        for (Reservation reservation : reservationHistory) {
            System.out.println(reservation);
        }
        System.out.println("------------------------------");
    }

    /**
     * Helper method to get a valid date time input from the user
     * 
     * @param prompt The prompt to display to the user
     * @return A LocalDateTime object
     */
    public LocalDateTime getValidDateTime(String prompt) {
        LocalDateTime dateTime = null;
        boolean valid = false;
        
        while (!valid) {
            System.out.println(prompt + " (format: yyyy-MM-dd HH:mm)");
            String input = scanner.nextLine().trim();
            
            try {
                dateTime = LocalDateTime.parse(input, formatter);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("[x] Invalid date format. Please use yyyy-MM-dd HH:mm");
            }
        }
        
        return dateTime;
    }
}
package interfaces;
import java.time.LocalDateTime;
import models.Reservation;

public interface ReservationOperations {
    void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end);
    void modifyReservation(String reservationID, Reservation updated);
    void cancelReservation(String reservationID);
    void getVehicleAvailability(String station, LocalDateTime time);
    void addToWaitlist(String OperatorID, String vehicleType, String station);
}

// ----------------------------------------------------------
// RESERVATION SYSTEM INTERFACE
//
// This interface manages the core reservation functionality defined in the
// plan-of-attack.md
//
// Data structures used:
//   • HashMap - For mapping clients to their reservations (efficient lookup)
//   • Stack - For implementing undo functionality (LIFO for recent actions)
//   • Queue - For waitlist management (FIFO for fair assignment)
//
// Core constraints:
//   • Reservations only between 7:00 AM – 6:00 PM, Monday–Friday
//   • Maximum duration: 6 hours
//   • Users can only have one active reservation at a time
//   • Cancellations notify the next user on the waitlist
//   • System must calculate and display total cost before confirmation
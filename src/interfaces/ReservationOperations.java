package interfaces;
import java.time.LocalDateTime;
import models.Reservation;

public interface ReservationOperations {
    void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end);
    void modifyReservation(String reservationID, Reservation updated);
    void cancelReservation(String reservationID);
    void getVehicleAvailability(String station, LocalDateTime time);
    void addToWaitlist(String OperatorID, String vehicleType, String station);
};
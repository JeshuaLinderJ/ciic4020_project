package interfaces;
public interface UndoAction {
    void undoLastReservation();
    void undoLastCancellation();
};

// ----------------------------------------------------------
// UNDO ACTION INTERFACE
//
// This interface provides functionality to reverse recent operations
// as part of the reservation system functionality.
//
// Data structure used:
//   - Stack - Perfect for implementing undo operations due to its LIFO nature
//           - Allows tracking the most recent actions for immediate reversal
//
// Core functionality:
//   - Allows reversing the most recent reservation
//   - Allows reversing the most recent cancellation
//   - Integrates with the ReservationOperations implementation
//   - Maintains system integrity when operations are reversed
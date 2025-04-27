package models;

public class Reservation {
    //--------------------------------------------------
    // TODO: Add instance variables and methods
    //--------------------------------------------------
};

// ----------------------------------------------------------
// RESERVATION MODEL
//
// This class represents a vehicle reservation as defined in the
// plan-of-attack.md
//
// Needs to be implemented with fields for:
//   - Unique reservation ID
//   - Operator (user) making the reservation
//   - Vehicle being reserved
//   - Start and end times
//   - Station location
//   - Reservation cost
//
// Will interact with:
//   - HashMap - For client-reservation mapping (quick lookup)
//   - Stack - For undo functionality (reverse recent actions)
//   - Queue - For waitlist management (fair assignment system)
//
// Core constraints:
//   - Must validate time (7:00 AM – 6:00 PM, Mon–Fri)
//   - Must enforce maximum duration (6 hours)
//   - Must check operator eligibility (only one reservation at a time)
//   - Must calculate costs based on vehicle type and duration
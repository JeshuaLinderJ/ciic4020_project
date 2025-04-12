package interfaces;
public interface TransactionHistory {
    void getReservationLog();
    void getOperatorTransactionSummary(String OperatorID);
    void getAllTransactionSummaries();
}

// ----------------------------------------------------------
// TRANSACTION & HISTORY INTERFACE
//
// This interface manages the logging and reporting of all system activities
// as referenced in the plan-of-attack.md
//
// Data structure used:
//   • LinkedList - For maintaining an ordered transaction log (chronological order)
//                - Efficient for sequential access and frequent appends
//
// Core responsibilities:
//   • Track all reservation activities chronologically
//   • Generate reports for individual users
//   • Provide system-wide transaction summaries
//   • Support payment and balance tracking
//   • Enable administrators to review system usage patterns
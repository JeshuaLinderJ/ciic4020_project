package interfaces;

import models.Operator;

public interface OperatorManagement {
    void addOperator(Operator Operator);
    void modifyOperator(String studentID, Operator updatedOperator);
    void deleteOperator(String studentID);
    void listRegisteredOperators();
};

// ----------------------------------------------------------
// USER MANAGEMENT INTERFACE
//
// This interface handles all user/operator operations as defined in 
// plan-of-attack.md sections 2 (Users) and 5 (Operator Functionality).
//
// Core responsibilities:
//   - Manage system user accounts (add/modify/delete)
//   - Track user roles (Client, Owner, or Both)
//   - List all registered operators in the system
//   - Maintain user balances and expense/income records
//
// Implementation will need to maintain records of:
//   - User personal details (name, ID, email, phone)
//   - User balance (expenses or income)
//   - User's owned vehicles (if any)
//   - User roles and permissions in the system


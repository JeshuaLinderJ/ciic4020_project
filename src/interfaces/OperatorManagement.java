package interfaces;

import models.Operator;

public interface OperatorManagement {
    void addOperator(HashMap<String, Operator> operatorMap);//should this sbe empty or a queqe or list or something added
    void modifyOperator(String studentID, HashMap<String, Operator> operatorMap);
    void deleteOperator(String studentID, HashMap<String, Operator> operatorMap);
    void listRegisteredOperators(HashMap<String, Operator> operatorMap);
}

// ----------------------------------------------------------
// USER MANAGEMENT INTERFACE
//
// This interface handles all user/operator operations as defined in 
// plan-of-attack.md sections 2 (Users) and 5 (Operator Functionality).
//
// Core responsibilities:
//   • Manage system user accounts (add/modify/delete)
//   • Track user roles (Client, Owner, or Both)
//   • List all registered operators in the system
//   • Maintain user balances and expense/income records
//
// Implementation will need to maintain records of:
//   • User personal details (name, ID, email, phone)
//   • User balance (expenses or income)
//   • User's owned vehicles (if any)
//   • User roles and permissions in the system


//for later




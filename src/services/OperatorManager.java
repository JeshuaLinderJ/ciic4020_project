package services;

import interfaces.OperatorManagement;
import java.util.*;
import models.Operator;

/**
 * Handles user management - adding/removing clients & owners
 * 
 * Uses a Queue for processing new operators and the operatorMap from main
 * for storing them permanently
 */
public class OperatorManager implements OperatorManagement{

    // Our variables
    private Queue<Operator> operatorQueue = new LinkedList<>();
    //private Map<String, Operator> registeredOperators = new HashMap<>(); // got rid of the error?
    private Scanner scanner = new Scanner(System.in);
    private ServiceHoursManager svcHoursMgr = ServiceHoursManager.getInstance();

    /**
     * This creates clients/owners and adds them to the system
     * Uses the operatorMap from main.java to store them
     */
    public void addOperator(HashMap<String, Operator> operatorMap) {
        // Check hours first
        if (!svcHoursMgr.isWithinServiceHours()) {
            System.out.println("Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        boolean keepAdding = true;

        while (keepAdding) {
            Operator op = createOperatorFromInput();

            // add to queue and map
            operatorQueue.add(op);
            operatorMap.put(op.getId(), op);
            op.display();

            // ask if they want to add more
            System.out.println("\nAdd another person? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                keepAdding = false;
            }
        }

        // show everyone in the queue
        System.out.println("\nPeople in Queue (FIFO order):");
        for (Operator op : operatorQueue) {
            System.out.println("- " + op.getName() + " (" + op.getId() + ")");
        }
    }
    
    // Input validation helpers
    
    /**
     * Checks if a name only has letters & spaces
     */
    private boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetter(c) || c == ' ')) {
                return false;
            }
        }
        return !name.trim().isEmpty();
    }
    
    /**
     * Makes sure ID only has numbers
     */
    private boolean isValidId(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return !id.isEmpty();
    }
    
    /**
     * Email validator - needs @ and proper domain
     */
    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 0) {
            return false;
        }
        
        String domain = email.substring(atIndex + 1);
        return domain.equals("upr.edu") || domain.endsWith(".com");
    }
    
    /**
     * Phone # validator - needs 9-10 digits
     * Can have dashes in it too
     */
    private boolean isValidPhone(String phone) {
        // Strip out dashes to count digits
        String digitsOnly = phone.replace("-", "");
        
        // Check if it has 9-10 digits
        if (digitsOnly.length() < 9 || digitsOnly.length() > 10) {
            return false;
        }
        
        // Make sure only digits & dashes
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (!Character.isDigit(c) && c != '-') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Keeps asking until user gives valid input
     * Uses the validator function to check it
     */
    private String getValidInput(String prompt, java.util.function.Predicate<String> validator, String errorMsg) {
        String input;
        boolean isValid;
        
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            isValid = validator.test(input);
            
            if (!isValid) {
                System.out.println(errorMsg);
            }
        } while (!isValid);
        
        return input;
    }
    
    /**
     * Gets all the info needed to create an operator
     * Has validation for each field
     */
    private Operator createOperatorFromInput() {
        // Name (letters & spaces only)
        String name = getValidInput(
            "Enter your name (letters only): ", 
            this::isValidName, 
            "Bad name format! Only letters & spaces allowed."
        );
        
        // ID (numbers only)
        String id = getValidInput(
            "Enter your ID (just numbers): ", 
            this::isValidId, 
            "Invalid ID! Numbers only please."
        );
        
        // Email (needs @, ends with upr.edu or .com)
        String email = getValidInput(
            "Enter your UPR Email: ", 
            this::isValidEmail, 
            "Bad email! Must have @ and end with upr.edu or .com"
        );
        
        // Phone (9-10 digits, can have dashes)
        String phone = getValidInput(
            "Enter your Phone # (9-10 digits, dashes ok): ", 
            this::isValidPhone, 
            "Invalid phone #! Need 9-10 digits, dashes are optional."
        );

        // What type of user are they?
        System.out.println("What do you want to be?");
        System.out.println("Press 1: Client");
        System.out.println("Press 2: Owner");
        System.out.println("Press 3: both");
    
        // Get valid choice 1-3
        int choice;
        while (true) {
            try {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Please pick 1, 2, or 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a number! Try again.");
            }
        }
        
        // Set flags based on choice
        boolean isClient = false;
        boolean isOwner = false;
        boolean isBoth = false;
    
        switch (choice) {
            case 1 -> isClient = true;
            case 2 -> isOwner = true;
            case 3 -> {
                isClient = true;
                isOwner = true;
                isBoth = true;
            }
        }

        // Get starting credit amount
        double credit;
        while (true) {
            try {
                System.out.print("Starting credit amount? (like 50.00): ");
                credit = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number! Try something like 50.00");
            }
        }

        // Create new operator & set credit
        Operator operator = new Operator(name, id, email, isOwner, isClient, isBoth, phone);
        operator.setCredit(credit);
        return operator;
    }

    /**
     * Changes info for existing operator
     */
    public void modifyOperator(String studentID, HashMap<String, Operator> operatorMap) {
        // Check service hours first!
        if (!svcHoursMgr.isWithinServiceHours()) {
            System.out.println("Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        // See if ID exists in our map
        if (operatorMap.containsKey(studentID)) {
            // Just create a new one & replace the old one
            Operator operator = createOperatorFromInput();
            operatorMap.put(studentID, operator);
            System.out.println("Operator with ID " + studentID + " has been updated!");
        } else {
            System.out.println("Hmm, can't find ID " + studentID + " in our system.");
        }
    }

    /**
     * Deletes an operator from the system
     */
    public void deleteOperator(String studentID, HashMap<String, Operator> operatorMap) {
        // Check service hours first!
        if (!svcHoursMgr.isWithinServiceHours()) {
            System.out.println("Operation not allowed outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("   Toggle test mode in the main menu to bypass this restriction.");
            return;
        }

        // Try to remove from map
        if (operatorMap.containsKey(studentID)) {
            operatorMap.remove(studentID);
            System.out.println("Operator " + studentID + " deleted successfully!");
        } else {
            System.out.println("No operator with ID: " + studentID + " found!");
        }
    }

    /**
     * Shows all registered operators
     * This is allowed outside service hours since it's just reading
     */
    public void listRegisteredOperators(HashMap<String, Operator> operatorMap) {
        if (operatorMap.isEmpty()) {
            System.out.println("Nobody registered yet!");
            return;
        }

        System.out.println("\n------ Registered Operators ------");
        for (Map.Entry<String, Operator> entry : operatorMap.entrySet()) {
            String key = entry.getKey();          // The ID
            Operator value = entry.getValue();   // The person
            
            // Show basic info about each person
            System.out.println("ID: " + key + ", Name: " + value.getName() + 
                             ", Role: " + (value.isBoth() ? "Client & Owner" : (value.isClient() ? "Client" : "Owner")) +
                             ", Email: " + value.getUprEmail() +
                             ", Phone: " + value.getPhoneNumber());
        }
        System.out.println("----------------------------------");
    }

    // Interface methods we don't use - required but just throw exception
    @Override
    public void addOperator(Operator Operator) {
        // TODO: Implement this when needed
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void modifyOperator(String studentID, Operator updatedOperator) {
        // TODO: Implement this when needed
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void deleteOperator(String studentID) {
        // TODO: Implement this when needed
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void listRegisteredOperators() {
        // TODO: Implement this when needed
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}

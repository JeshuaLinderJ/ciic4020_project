package services;

import models.Operator;
import interfaces.OperatorManagement;
import java.time.*;
import java.util.*;

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

public class OperatorManager implements OperatorManagement{

      //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private Queue<Operator> operatorQueue = new LinkedList<>();
    //private Map<String, Operator> registeredOperators = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

     //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------


    //This creates a client owner or both it’s called in Maine and it uses the #map operator from Maine. 
    // It also uses a function called create operator from input and add the operator to the operator queue an ad to the operator hash
    //  map.
    @Override
    public void addOperator(HashMap<String, Operator> operatorMap); {
        if (!isWithinServiceHours()) {
            System.out.println("The available service hours will be from 7:00 AM to 6:00 PM, Monday to Friday.");
        }

        boolean addingOperators = true;

        while (addingOperators) {
            Operator operator = createOperatorFromInput();


            operatorQueue.add(operator);
            operatorMap.put(operator.getId(), operator);
            operator.display();

            //change to clients
            System.out.println("\nAdd another operator? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                addingOperators = false;
            }
        }

        //change to clients
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

        System.out.print("Enter your ID(Student number or any other ID): ");
        String id = scanner.nextLine();

        System.out.print("Enter your UPR Email: ");
        String uprEmail = scanner.nextLine();

        System.out.print("Enter your Phone Number: ");
        String phoneNum = scanner.nextLine();

        System.out.println("What would you like to be registered as?");
        System.out.println("Press 1: Client");
        System.out.println("Press 2: Owner");
        System.out.println("Press 3: both");
    
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
         return operator;
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

    //Modifies a pre-existing clients and owners , called by main
    @Override
    public void modifyOperator(String studentID, HashMap<String, Operator> operatorMap) {

if (operatorMap.containsKey(studentID)) {
    Operator operator = createOperatorFromInput();

    operatorMap.put(studentID, operator);
} else {
    System.out.println("Operator with ID " + studentID + " does not exist.");
}
        }

        //delete a pre-existing clients and owners , called by main
    @Override
    public void deleteOperator(String studentID, HashMap<String, Operator> operatorMap){
        if (operatorMap.containsKey(studentID)){
            
            operatorMap.remove(studentID);
            System.out.println("Operator with ID " + studentID + " has been deleted.");
        } else {
            System.out.println("No operator found with ID: " + studentID);
        }
    }

    // shows all clients and owners in the operatorMap, called by main
    @Override
    public void listRegisteredOperators(HashMap<String, Operator> operatorMap) {


        if (operatorMap.isEmpty()) {
        for (Map.Entry<String, Operator> entry : operatorMap.entrySet()) {
            String key = entry.getKey();          // The ID (key)
            Operator value = entry.getValue();   // The Operator (value)
        
            // Displaying the key and value (you can customize this)
            System.out.println("ID: " + key + ", Name: " + value.getName() + ", Phone: " + value.getPhoneNum());
        }
        else {
                 System.out.println("The operator map is not empty.");
    }
}
}
}
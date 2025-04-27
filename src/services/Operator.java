package services;

import models.Operator;
import interfaces.OperatorManagement;
import java.time.*;
import java.util.*;

public class OperatorManager implements OperatorManagement{

    private Queue<Operator> operatorQueue = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addOperator(Operator Operator); {
        if (!isWithinServiceHours()) {
            System.out.println("The available service hours will be from 7:00 AM to 6:00 PM, Monday to Friday.");
            return;
        }

        boolean addingOperators = true;

        while (addingOperators) {
            Operator operator = createOperatorFromInput();
            operatorQueue.add(operator);
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

    //maybe move ti the staion one
    private Operator createOperatorFromInput() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter your UPR Email: ");
        String uprEmail = scanner.nextLine();

        System.out.print("Enter your Phone Number: ");
        String phoneNum = scanner.nextLine();

        System.out.println("What would you like to do?");
        System.out.println("Press 1 to reserve a vehicle");
        System.out.println("Press 2 to register a vehicle");
        System.out.println("Press 3 to do both");
    
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

        //System.out.print("Enter number of reservations made: ");
        //int reserved = Integer.parseInt(scanner.nextLine());

        //System.out.print("Enter number of items registered: ");
        //int registered = Integer.parseInt(scanner.nextLine());

        //System.out.print("Enter profit amount (if any): ");
        //double profit = Double.parseDouble(scanner.nextLine());

        Operator operator = new Operator(name, id, uprEmail, isOwner, isClient, isBoth, phoneNum);
        operator.setCredit(credit);
        //operator.setReserved(reserved);
        //operator.setRegistered(registered);
        //operator.setProfit(profit);

        //ask them based on their role
if (isBoth) {
    System.out.println("Would you like to:");
    System.out.println("1. Make a reservation");
    System.out.println("2. Register a vehicle");
    int choice = Integer.parseInt(scanner.nextLine());
    if (choice == 1) {
        System.out.println("Proceeding with reservation...");
        // add reservation logic here
    } else if (choice == 2) {
        System.out.println("Proceeding with vehicle registration...");
        // add registration logic here
    } else {
        System.out.println("Invalid choice. No action taken.");
    }

} else if (isClient) {
    System.out.println("Would you like to make a reservation? (yes/no)");
    String response = scanner.nextLine().trim().toLowerCase();
    if (response.equals("yes")) {
        System.out.println("Proceeding with reservation...");
        // add reservation logic here
    } else {
        System.out.println("No reservation made.");
    }

    
} else if (isOwner) {
    System.out.println("Would you like to register your vehicle? (yes/no)");
    String response = scanner.nextLine().trim().toLowerCase();
    if (response.equals("yes")) {
        System.out.println("Proceeding with vehicle registration...");
        // add registration logic here
    } else {
        System.out.println("No vehicle registered.");
    }
}

        return operator;
    }

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





    @Override
    public void modifyOperator(String studentID, Operator updatedOperator) {
        if (registeredOperators.containsKey(studentID)) {
            registeredOperators.put(studentID, updatedOperator);
            System.out.println("Operator with ID " + studentID + " has been updated.");
        } else {
            System.out.println("No operator found with ID: " + studentID);
        }
    }

    @Override
    public void deleteOperator(String studentID) {
        if (registeredOperators.containsKey(studentID)) {
            registeredOperators.remove(studentID);
            System.out.println("Operator with ID " + studentID + " has been deleted.");
        } else {
            System.out.println("No operator found with ID: " + studentID);
        }
    }

    @Override
    public void listRegisteredOperators() {
        if (registeredOperators.isEmpty()) {
            System.out.println("No operators are currently registered.");
        } else {
            System.out.println("List of registered operators:");
            for (Operator op : registeredOperators.values()) {
                System.out.println("- " + op.getName() + " | ID: " + op.getId() + " | Email: " + op.getUprEmail());
            }
        }
    }
}
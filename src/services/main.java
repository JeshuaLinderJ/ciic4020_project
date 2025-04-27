package services;

import models.Station;
import models.Operator;
import models.stations.Stefani;
import models.stations.StudentCenter;
import models.stations.Biology;
import models.stations.Business;
import models.stations.ChemicalEngineering;
import interfaces.Vehicle;
import java.util.Scanner;
import java.util.HashMap;

//--------------------------------------------------
// Main Method
//--------------------------------------------------

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        OperatorManager operatorManager = new OperatorManager(); 
        HashMap<String, Operator> operatorMap = new HashMap<>();

        // Create station instances
        Station stefani = new Stefani();
        Station studentCenter = new StudentCenter();
        Station biology = new Biology();
        Station businessAdministration = new Business();
        Station chemicalEngineering = new ChemicalEngineering();

        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Work with Clients");
            System.out.println("2 - Work withVehicles");
            System.out.println("3 - Work with Reservations");
            System.out.println("0 - Quit");
            System.out.print("Your choice: ");
            int mainChoice = Integer.parseInt(scanner.nextLine());

            switch (mainChoice) {
                case 1 -> {
                    System.out.println("\nClient Management:");
                    System.out.println("1 - Add a client");
                    System.out.println("2 - Modify a client");
                    System.out.println("3 - Delete a client");
                    System.out.println("4 - List all registered clients");
                    System.out.print("Your choice: ");
                    int clientChoice = Integer.parseInt(scanner.nextLine());

                    switch (clientChoice) {
                        case 1 -> operatorManager.addOperator(operatorMap);
                       // operatorMap.put("John Doe", new Operator("John Doe", "001", "john@example.com", true, false, false, "123-456-7890"));
                        case 2 -> {
                            System.out.print("Enter client/Owner ID to modify: ");
                            String modId = scanner.nextLine();
                            operatorManager.modifyOperator( modId,operatorMap);
                            //System.out.println("Modifying not fully implemented. Placeholder logic here.");
                            // add
                        }
                        case 3 -> {
                            System.out.print("Enter client/Owner ID to delete: ");
                            String delId = scanner.nextLine();
                            operatorManager.deleteOperator(delId, operatorMap);
                        }
                        case 4 -> operatorManager.listRegisteredOperators(operatorMap);
                        default -> System.out.println("Invalid choice for client operations.");
                    }
                }
                case 2 -> {

                    System.out.println("\nStation Management:");
                    System.out.println("1 - Add a vehicle");
                    System.out.println("2 - Modify a vehicle");
                    System.out.println("3 - Delete a vehicle");
                    System.out.println("4 - List all registered vehicle");
                    System.out.print("Your choice: ");
                    int StationMChoice = Integer.parseInt(scanner.nextLine());

                   
                    //String Intlocation;
                    //System.out.println("\nWhere would you like to add a vehicle?");

                    System.out.println("\nWhere would you like to work with");
                    System.out.println("1 - Stefani");
                    System.out.println("2 - Student Center");
                    System.out.println("3 - Biology");
                    System.out.println("4 - Business Administration");
                    System.out.println("5 - Chemical Engineering");
                    System.out.print("Your choice: ");
                    int stationChoice = Integer.parseInt(scanner.nextLine());

                    Station chosenStation = switch (stationChoice) {
                        case 1 -> {
                            if(StationMChoice == 1)
                            stefani.AddVehicle(operatorMap);

                            else if(StationMChoice == 2)
                            stefani.removeVehicle(operatorMap);

                            if(StationMChoice == 3)
                            stefani.modifyVehicle(operatorMap);

                            if(StationMChoice == 4)
                            stefani.listVehicle(operatorMap);
                        }

                        case 2 ->  {
                            if(StationMChoice == 1)
                            studentCenter.AddVehicle(operatorMap);

                            else if(StationMChoice == 2)
                            studentCenter.removeVehicle(operatorMap);

                            if(StationMChoice == 3)
                            studentCenter.modifyVehicle(operatorMap);

                            if(StationMChoice == 4)
                            studentCenter.listVehicle(operatorMap);
                        }

                        case 3 ->  {
                            if(StationMChoice == 1)
                            biology.AddVehicle(operatorMap);

                            else if(StationMChoice == 2)
                            biology.removeVehicle(operatorMap);

                            if(StationMChoice == 3)
                            biology.modifyVehicle(operatorMap);

                            if(StationMChoice == 4)
                            biology.listVehicle(operatorMap);
                        }

                        case 4 ->  {
                            if(StationMChoice == 1)
                            businessAdministration.AddVehicle(operatorMap);

                            else if(StationMChoice == 2)
                            businessAdministration.removeVehicle(operatorMap);

                            if(StationMChoice == 3)
                            businessAdministration.modifyVehicle(operatorMap);

                            if(StationMChoice == 4)
                            businessAdministration.listVehicle(operatorMap);
                        }

                        case 5 ->  {
                            if(StationMChoice == 1)
                            chemicalEngineering.AddVehicle(operatorMap);

                            else if(StationMChoice == 2)
                            chemicalEngineering.removeVehicle(operatorMap);

                            if(StationMChoice == 3)
                            chemicalEngineering.modifyVehicle(operatorMap);

                            if(StationMChoice == 4)
                            chemicalEngineering.listVehicle(operatorMap);
                        }
                        default -> null;
                    };

                    if (chosenStation == null) {
                        System.out.println("Invalid station choice.");
                        continue;
                    }

    //                 System.out.println("\nWhat type of vehicle do you want to add?");
    //                 System.out.println("1 - Bicycle");
    //                 System.out.println("2 - Scooter");
    //                 System.out.println("3 - Skateboard");
    //                 System.out.print("Your choice: ");
    //                 int vehicleType = Integer.parseInt(scanner.nextLine());

    //                 String type = switch (vehicleType) {
    //                     case 1 -> {
    //                         System.out.println("Enter a description for the vehicle:");
    //                         String description = scanner.nextLine();

    //                         System.out.println("Enter the owner's ID:");
    //                         String ownerID = scanner.nextLine();

    //     // Check if the owner exists and if they are an owner or both
    //     Operator owner = operatorMap.get(ownerID); 

    //     if (owner != null) {
    //         if (owner.isOwner() || owner.isBoth()) { 
    //             System.out.println("Owner " + ownerID + " is valid. Description: " + description);

    //         } else {
    //             System.out.println("The ID provided does not belong to an owner or both.");
    //                        }
    //     } else {
    //         System.out.println("Owner ID not found.");

    //     }
    //     break;  // have to make sure this dosent contione ro the station stuff maybe make it a loop later or some
    // }
    //                     };
    //                     case 2 -> "Scooter";
    //                     case 3 -> "Skateboard";
    //                     default -> "Unknown";
    //                 };

    //                 System.out.print("Enter vehicle ID: ");
    //                 String vehicleId = scanner.nextLine();
    //                 Vehicle newVehicle = new Vehicle(vehicleId, type);

    //                 if (chosenStation.addVehicle(newVehicle)) {
    //                     System.out.println("Vehicle added successfully to " + chosenStation.getName());
    //                 } else {
    //                     System.out.println("Failed to add vehicle. Station may be at full capacity.");
    //                 }
                }
                case 3 -> {
                    System.out.println("\nReservation Options:");
                    System.out.println("1 - Make a reservation");
                    System.out.println("2 - Modify a reservation");
                    System.out.println("3 - Cancel a reservation");
                    System.out.println("4 - Check vehicle availability");
                    System.out.print("Your choice: ");
                    int resChoice = Integer.parseInt(scanner.nextLine());

                    switch (resChoice) {
                        case 1 -> System.out.println("Reservation logic placeholder.");
                        case 2 -> System.out.println("Modify reservation placeholder.");
                        case 3 -> System.out.println("Cancel reservation placeholder.");
                        case 4 -> System.out.println("Check vehicle availability placeholder.");
                        default -> System.out.println("Invalid reservation choice.");
                    }
                }
                case 0 -> {
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid main menu choice.");
                }
            }
        scanner.close();
        }
    }
}
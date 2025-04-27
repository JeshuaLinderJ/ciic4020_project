// MAIN

package services;

import interfaces.Vehicle;
import java.util.Scanner;
import models.Station;
import models.vehicles.Bicycle;
import models.vehicles.Scooter;
import models.vehicles.Skateboard;

public class main {
    //--------------------------------------------------
    // Main Method
    //--------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OperatorManager operatorManager = new OperatorManager();

        // Create station instances
        Station stefani = new Station("Stefani", 40);
        Station studentCenter = new Station("Student Center", 90);
        Station biology = new Station("Biology", 35);
        Station businessAdministration = new Station("Business Administration", 45);
        Station chemicalEngineering = new Station("Chemical Engineering", 45);

        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Work with Clients");
            System.out.println("2 - Add Vehicles");
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
                        case 1 -> operatorManager.addOperator(null);
                        case 2 -> {
                            System.out.print("Enter client ID to modify: ");
                            String modId = scanner.nextLine();
                            System.out.println("Modifying not fully implemented. Placeholder logic here.");
                            // add
                        }
                        case 3 -> {
                            System.out.print("Enter client ID to delete: ");
                            String delId = scanner.nextLine();
                            operatorManager.deleteOperator(delId);
                        }
                        case 4 -> operatorManager.listRegisteredOperators();
                        default -> System.out.println("Invalid choice for client operations.");
                    }
                }
                case 2 -> {
                    System.out.println("\nWhere would you like to add a vehicle?");
                    System.out.println("1 - Stefani");
                    System.out.println("2 - Student Center");
                    System.out.println("3 - Biology");
                    System.out.println("4 - Business Administration");
                    System.out.println("5 - Chemical Engineering");
                    System.out.print("Your choice: ");
                    int stationChoice = Integer.parseInt(scanner.nextLine());

                    Station chosenStation = switch (stationChoice) {
                        case 1 -> stefani;
                        case 2 -> studentCenter;
                        case 3 -> biology;
                        case 4 -> businessAdministration;
                        case 5 -> chemicalEngineering;
                        default -> null;
                    };

                    if (chosenStation == null) {
                        System.out.println("Invalid station choice.");
                        continue;
                    }

                    System.out.println("\nWhat type of vehicle do you want to add?");
                    System.out.println("1 - Bicycle");
                    System.out.println("2 - Scooter");
                    System.out.println("3 - Skateboard");
                    System.out.print("Your choice: ");
                    int vehicleType = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter vehicle description: ");
                    String description = scanner.nextLine();
                    
                    Vehicle newVehicle = null;
                    String stationName = chosenStation.getName();
                    
                    switch (vehicleType) {
                        case 1 -> newVehicle = new Bicycle(description, stationName);
                        case 2 -> newVehicle = new Scooter(description, stationName);
                        case 3 -> newVehicle = new Skateboard(description, stationName);
                        default -> {
                            System.out.println("Invalid vehicle type.");
                            continue;
                        }
                    }

                    if (chosenStation.addVehicle(newVehicle)) {
                        System.out.println("Vehicle added successfully to " + chosenStation.getName());
                    } else {
                        System.out.println("Failed to add vehicle. Station may be at full capacity.");
                    }
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
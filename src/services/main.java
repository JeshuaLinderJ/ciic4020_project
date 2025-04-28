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
import java.time.LocalDateTime;
import models.Reservation;

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
            System.out.println("2 - Work with Vehicles");
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
                        case 2 -> {
                            System.out.print("Enter client/Owner ID to modify: ");
                            String modId = scanner.nextLine();
                            operatorManager.modifyOperator(modId, operatorMap);
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
                    System.out.println("2 - Remove a vehicle");
                    System.out.println("3 - Modify a vehicle");
                    System.out.println("4 - List all registered vehicles");
                    System.out.print("Your choice: ");
                    int stationMChoice = Integer.parseInt(scanner.nextLine());

                    System.out.println("\nWhere would you like to work with:");
                    System.out.println("1 - Stefani");
                    System.out.println("2 - Student Center");
                    System.out.println("3 - Biology");
                    System.out.println("4 - Business Administration");
                    System.out.println("5 - Chemical Engineering");
                    System.out.print("Your choice: ");
                    int stationChoice = Integer.parseInt(scanner.nextLine());

                    Station chosenStation = null;
                    switch (stationChoice) {
                        case 1 -> {
                            if (stationMChoice == 1) {
                                stefani.addVehicle(operatorMap);
                            } else if (stationMChoice == 2) {
                                stefani.removeVehicle(operatorMap);
                            } else if (stationMChoice == 3) {
                                stefani.modifyVehicle(operatorMap);
                            } else if (stationMChoice == 4) {
                                stefani.listVehicle(operatorMap);
                            }
                            chosenStation = stefani;
                        }
                        case 2 -> {
                            if (stationMChoice == 1) {
                                studentCenter.addVehicle(operatorMap);
                            } else if (stationMChoice == 2) {
                                studentCenter.removeVehicle(operatorMap);
                            } else if (stationMChoice == 3) {
                                studentCenter.modifyVehicle(operatorMap);
                            } else if (stationMChoice == 4) {
                                studentCenter.listVehicle(operatorMap);
                            }
                            chosenStation = studentCenter;
                        }
                        case 3 -> {
                            if (stationMChoice == 1) {
                                biology.addVehicle(operatorMap);
                            } else if (stationMChoice == 2) {
                                biology.removeVehicle(operatorMap);
                            } else if (stationMChoice == 3) {
                                biology.modifyVehicle(operatorMap);
                            } else if (stationMChoice == 4) {
                                biology.listVehicle(operatorMap);
                            }
                            chosenStation = biology;
                        }
                        case 4 -> {
                            if (stationMChoice == 1) {
                                businessAdministration.addVehicle(operatorMap);
                            } else if (stationMChoice == 2) {
                                businessAdministration.removeVehicle(operatorMap);
                            } else if (stationMChoice == 3) {
                                businessAdministration.modifyVehicle(operatorMap);
                            } else if (stationMChoice == 4) {
                                businessAdministration.listVehicle(operatorMap);
                            }
                            chosenStation = businessAdministration;
                        }
                        case 5 -> {
                            if (stationMChoice == 1) {
                                chemicalEngineering.addVehicle(operatorMap);
                            } else if (stationMChoice == 2) {
                                chemicalEngineering.removeVehicle(operatorMap);
                            } else if (stationMChoice == 3) {
                                chemicalEngineering.modifyVehicle(operatorMap);
                            } else if (stationMChoice == 4) {
                                chemicalEngineering.listVehicle(operatorMap);
                            }
                            chosenStation = chemicalEngineering;
                        }
                    }

                    if (chosenStation == null) {
                        System.out.println("Invalid station choice.");
                    }
                }
                
                case 3 -> {
                    boolean isMod = false;
                    String modId = null;
                    
                    System.out.println("\nReservation Options:");
                    System.out.println("1 - Make a reservation");
                    System.out.println("2 - Modify a reservation");
                    System.out.println("3 - Cancel a reservation");
                    System.out.println("4 - Check vehicle availability");
                    System.out.print("Your choice: ");
                    int resChoice = Integer.parseInt(scanner.nextLine());

                    System.out.println("\nWhere would you like to work with:");
                    System.out.println("1 - Stefani");
                    System.out.println("2 - Student Center");
                    System.out.println("3 - Biology");
                    System.out.println("4 - Business Administration");
                    System.out.println("5 - Chemical Engineering");
                    System.out.print("Your choice: ");
                    int stationChoice = Integer.parseInt(scanner.nextLine());

                    Station chosenStation = null;
                    switch (stationChoice) {
                        case 1 -> {
                            if (resChoice == 1) {
                                stefani.makeReservation(operatorMap, isMod, modId);
                            } else if (resChoice == 2) {
                                stefani.modifyReservation(operatorMap);
                            } else if (resChoice == 3) {
                                stefani.cancelReservation(operatorMap);
                            } else if (resChoice == 4) {
                                stefani.listVehicle(operatorMap);
                            }
                            chosenStation = stefani;
                        }
                        case 2 -> {
                            if (resChoice == 1) {
                                studentCenter.makeReservation(operatorMap, isMod, modId);
                            } else if (resChoice == 2) {
                                studentCenter.modifyReservation(operatorMap);
                            } else if (resChoice == 3) {
                                studentCenter.cancelReservation(operatorMap);
                            } else if (resChoice == 4) {
                                studentCenter.listVehicle(operatorMap);
                            }
                            chosenStation = studentCenter;
                        }
                        case 3 -> {
                            if (resChoice == 1) {
                                biology.makeReservation(operatorMap, isMod, modId);
                            } else if (resChoice == 2) {
                                biology.modifyReservation(operatorMap);
                            } else if (resChoice == 3) {
                                biology.cancelReservation(operatorMap);
                            } else if (resChoice == 4) {
                                biology.listVehicle(operatorMap);
                            }
                            chosenStation = biology;
                        }
                        case 4 -> {
                            if (resChoice == 1) {
                                businessAdministration.makeReservation(operatorMap, isMod, modId);
                            } else if (resChoice == 2) {
                                businessAdministration.modifyReservation(operatorMap);
                            } else if (resChoice == 3) {
                                businessAdministration.cancelReservation(operatorMap);
                            } else if (resChoice == 4) {
                                businessAdministration.listVehicle(operatorMap);
                            }
                            chosenStation = businessAdministration;
                        }
                        case 5 -> {
                            if (resChoice == 1) {
                                chemicalEngineering.makeReservation(operatorMap, isMod, modId);
                            } else if (resChoice == 2) {
                                chemicalEngineering.modifyReservation(operatorMap);
                            } else if (resChoice == 3) {
                                chemicalEngineering.cancelReservation(operatorMap);
                            } else if (resChoice == 4) {
                                chemicalEngineering.listVehicle(operatorMap);
                            }
                            chosenStation = chemicalEngineering;
                        }
                    }

                    if (chosenStation == null) {
                        System.out.println("Invalid station choice.");
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

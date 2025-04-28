package services;

import interfaces.StationManagement;
import java.io.Console;
import models.Reservation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import models.Station;
import java.util.Scanner;




public class StationManager implements StationManagement {

    //--------------------------------------------------
    // Instance Variables
    // This is the station manager at helps with all the registrations waitlist and creation of vehicles and putting them into 
    // the station such as 
    // Stephanie and so on it uses a hashmap in order to easily link clients to their reserved vehicles
    //--------------------------------------------------
    private Map<String, Station> stations; // this can be changed for an int if we want to have them be like 0-4 or something
    HashMap<String, Object> reservation = new HashMap<>();


     //--------------------------------------------------
    // Constructor
    //--------------------------------------------------
    public StationManager() {
        this.stations = new HashMap<>();
    };

    // @Override
    public Station getStation(String stationName) {
        return stations.get(stationName);
    };
    //Makes the reservations linking clients to their reserved vehicles if possible it is called by Maine
    //It has a switch to inside of it in order to facilitate the choices for different vehicles and different stations, called by main
        // @Override
        public void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end) {
            // TODO: Implement reservation logic
            System.out.println("Reservation made for operator " + OperatorID + " and vehicle " + vehicleID);

            Object vehicle = null;

            System.out.println("\nWhat type of vehicle do you want to reserver?");
                    System.out.println("1 - Bicycle");
                    System.out.println("2 - Scooter");
                    System.out.println("3 - Skateboard");
                    System.out.print("Your choice: ");
                    int vehicleType = Integer.parseInt(scanner.nextLine());

                    String type = switch (vehicleType) {
                        case 1 ->{
                            boolean foundBicycle = false;

                            for (Object item : inventory) {
                                if (item instanceof Bicycle) {
                                    foundBicycle = true;
                                    Bicycle bicycle = (Bicycle) item;  // Casting
                                    System.out.println(bicycle);


                                }
                            }
                    
                           
                            if (!foundBicycle) {
                                System.out.println("No Bicycle found in the inventory adding do you wish to be added to wait list(yes/no).");
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.print("Enter client ID: ");
                                    String Id = scanner.nextLine();

                                    if (client != null) {
                                        if (client.isClient() || client.isBoth())     
                                         waitlistbicy.put(Id);
                                    System.out.println("You have been added to the waitlist.");
                                    return;
                                } else {

                                    System.out.println("You have not been added to the waitlist.");
                                    return;
                                }
                            }

                            System.out.println("Do you wish to reserver one(yes/no))"){
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.println("type the owners Id, and for how long youll be suing it for(no more than 6hours).");

                                    System.out.println("Type the owner's ID: ");
                                    String ownerId = scanner.nextLine();
                                    
                                    boolean foundBicycleid = false; 
                                    for (Object item : inventory) {
                                        if (item instanceof Bicycle) {
                                            Bicycle bicycle = (Bicycle) item;  // Casting to Bicycle
                                            // Check if the input ownerId matches the bicycle's ownerId
                                            if (bicycle.getOwnerId().equals(ownerId)) {
                                                
                                                foundBicycleid = true;
                                                break;  // Exit the loop once we find and reserve the bicycle
                                            }
                                        }
                                    }
                                
                                    // If no matching bicycle is found
                                    if (!foundBicycleid) {
                                        System.out.println("No bicycle found with the owner ID: " + ownerId + "or its not availabel");
                                        break;
                                    }
                                    System.out.println("For how long will you be using it (in hours)? ");
                                    int usageTime = scanner.nextInt();
 
                                    // Check if the time is valid
                                    if (usageTime > 6) {
                                        System.out.println("The usage time is not valid. It cannot be more than 6 hours.");
                                    } else if (usageTime <= 0) {
                                        System.out.println("Please enter a valid usage time greater than 0.");
                                    } else {

                                    
                                        System.out.println("It will cost you: " + credit +"Credits. confirm(yes/no)");
                                        //add the cost function here and then mod a few lines down with the cost
                                        String userResponseconfirm = scanner.nextLine().toLowerCase();

                                        if (userResponse.equals("yes")) {
                                            System.out.print("Enter client ID: ");
                                            String Id = scanner.nextLine();


                                            Operator client = operatorMap.get(Id); 
                                            Operator owner = operatorMap.get(ownerId); 

                                         if (client != null) {
                                         if ((client.isClient() || client.isBoth()) && client.getRegistered() == 0 && client.getCredit() > cost)//cost added later { 
                                            reservation.put(client.getId(), bicycle);
                                            client.setRegistered(1);
                                            client.setCredit()// make it a negative for what he paid
                                            bicycle.setAvailability(false);
                                            owner.setProfit()//+ for what he paid

                                      } else {
                                            System.out.println("The ID provided does not belong to a client or both or you alreadya are reserveing one.");
                                              }
                                                } 
                                                else {
                                            System.out.println("Owner ID not found.");
                                          }
                                         break;  // have to make sure this dosent contione ro the station stuff maybe make it a loop later or some
                                    }                            
                                    }
                                }
                                }
                            }
                        case 2 ->{
                            boolean foundScooter = false;

                            for (Object item : inventory) {
                                if (item instanceof Scooter) {
                                    foundScooter = true;
                                    Scooter scooter = (Scooter) item;  // Casting
                                    System.out.println(scooter);


                                }
                            }
                    
                           
                            if (!foundScooter) {
                                System.out.println("No Scooter found in the inventory adding do you wish to be added to wait list(yes/no).");
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.print("Enter client ID: ");
                                    String Id = scanner.nextLine();

                                    if (client != null) {
                                        if (client.isClient() || client.isBoth())     
                                        waitlistscoo.put(Id);//change
                                    System.out.println("You have been added to the waitlist.");
                                    return;
                                } else {

                                    System.out.println("You have not been added to the waitlist.");
                                    return;
                                }
                            }

                            System.out.println("Do you wish to reserver one(yes/no))"){
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.println("type the owners Id, and for how long youll be suing it for(no more than 6hours).");

                                    System.out.println("Type the owner's ID: ");
                                    String ownerId = scanner.nextLine();
                                    
                                    boolean foundScooter = false; 
                                    for (Object item : inventory) {
                                        if (item instanceof Scooter) {
                                            Bicycle scooter = (Scooter) item;  // Casting to Scooter
                                            // Check if the input ownerId matches the Scooter's ownerId
                                            if (scooter.getOwnerId().equals(ownerId)) {
                                                
                                                foundScooter = true;
                                                break;  // Exit the loop once we find and reserve the Scooter
                                            }
                                        }
                                    }
                                
                                    // If no matching Scooter is found
                                    if (!foundScooter) {
                                        System.out.println("No Scooter found with the owner ID: " + ownerId + "or its not availabel");
                                        break;
                                    }
                                    System.out.println("For how long will you be using it (in hours)? ");
                                    int usageTime = scanner.nextInt();
 
                                    // Check if the time is valid
                                    if (usageTime > 6) {
                                        System.out.println("The usage time is not valid. It cannot be more than 6 hours.");
                                    } else if (usageTime <= 0) {
                                        System.out.println("Please enter a valid usage time greater than 0.");
                                    } else {

                                    
                                        System.out.println("It will cost you: " + credit +"Credits. confirm(yes/no)");
                                        //add the cost function here and then mod a few lines down with the cost
                                        String userResponseconfirm = scanner.nextLine().toLowerCase();

                                        if (userResponse.equals("yes")) {
                                            System.out.print("Enter client ID: ");
                                            String Id = scanner.nextLine();


                                            Operator client = operatorMap.get(Id); 
                                            Operator owner = operatorMap.get(ownerId); 

                                         if (client != null) {
                                         if ((client.isClient() || client.isBoth()) && client.getRegistered() == 0 && client.getCredit() > cost)//cost added later { 
                                            reservation.put(client.getId(), scooter);
                                            client.setRegistered(1);
                                            client.setCredit()// make it a negative for what he paid
                                            bicycle.setAvailability(false);
                                            owner.setProfit()//+ for what he paid

                                      } else {
                                            System.out.println("The ID provided does not belong to a client or both or you alreadya are reserveing one.");
                                              }
                                                } 
                                                else {
                                            System.out.println("Owner ID not found.");
                                          }
                                         break;  // have to make sure this dosent contione ro the station stuff maybe make it a loop later or some
                                    }                            
                                    }
                                }
                                }
                            };//later repet the same stuff as before or above
                        case 3 -> {
                            boolean foundSkateboard = false;

                            for (Object item : inventory) {
                                if (item instanceof Skateboard) {
                                    foundSkateboard = true;
                                    Skateboard skateboard = (Skateboard) item;  // Casting
                                    System.out.println(skateboard);


                                }
                            }
                    
                           
                            if (!foundSkateboard) {
                                System.out.println("No Skateboard found in the inventory adding do you wish to be added to wait list(yes/no).");
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.print("Enter client ID: ");
                                    String Id = scanner.nextLine();

                                    if (client != null) {
                                        if (client.isClient() || client.isBoth())     
                                        waitlistskate.put(Id);//change
                                    System.out.println("You have been added to the waitlist.");
                                    return;
                                } else {

                                    System.out.println("You have not been added to the waitlist.");
                                    return;
                                }
                            }

                            System.out.println("Do you wish to reserver one(yes/no))"){
                                String userResponse = scanner.nextLine().toLowerCase();

                                if (userResponse.equals("yes")) {
                                    System.out.println("type the owners Id, and for how long youll be suing it for(no more than 6hours).");

                                    System.out.println("Type the owner's ID: ");
                                    String ownerId = scanner.nextLine();
                                    
                                    boolean foundSkateboard = false; 
                                    for (Object item : inventory) {
                                        if (item instanceof Skateboard) {
                                            Skateboard skateboard = (Skateboard) item;  // Casting to Skateboard
                                            // Check if the input ownerId matches the Skateboard's ownerId
                                            if (skateboard.getOwnerId().equals(ownerId)) {
                                                
                                                foundSkateboard = true;
                                                break;  // Exit the loop once we find and reserve the Skateboard
                                            }
                                        }
                                    }
                                
                                    // If no matching Skateboard is found
                                    if (!foundSkateboard) {
                                        System.out.println("No Skateboard found with the owner ID: " + ownerId + "or its not availabel");
                                        break;
                                    }
                                    System.out.println("For how long will you be using it (in hours)? ");
                                    int usageTime = scanner.nextInt();
 
                                    // Check if the time is valid
                                    if (usageTime > 6) {
                                        System.out.println("The usage time is not valid. It cannot be more than 6 hours.");
                                    } else if (usageTime <= 0) {
                                        System.out.println("Please enter a valid usage time greater than 0.");
                                    } else {

                                    
                                        System.out.println("It will cost you: " + credit +"Credits. confirm(yes/no)");
                                        //add the cost function here and then mod a few lines down with the cost
                                        String userResponseconfirm = scanner.nextLine().toLowerCase();

                                        if (userResponse.equals("yes")) {
                                            System.out.print("Enter client ID: ");
                                            String Id = scanner.nextLine();


                                            Operator client = operatorMap.get(Id); 
                                            Operator owner = operatorMap.get(ownerId); 

                                         if (client != null) {
                                         if ((client.isClient() || client.isBoth()) && client.getRegistered() == 0 && client.getCredit() > cost)//cost added later { 
                                            reservation.put(client.getId(), skateboard);
                                            client.setRegistered(1);
                                            client.setCredit()// make it a negative for what he paid
                                            bicycle.setAvailability(false);
                                            owner.setProfit()//+ for what he paid

                                      } else {
                                            System.out.println("The ID provided does not belong to a client or both or you alreadya are reserveing one.");
                                              }
                                                } 
                                                else {
                                            System.out.println("Owner ID not found.");
                                          }
                                         break;  // have to make sure this dosent contione ro the station stuff maybe make it a loop later or some
                                    }                            
                                    }
                                }
                                }
                            };
                        default -> "Unknown";
                    };
                    
                    
//Modifies a pre-existing reservation making sure that the client is able to get a reservation that better
// suits they’re current needs, called by main
        @Override
        public void modifyReservation(String reservationID, Reservation updated) {
            // TODO: Implement modification logic

            System.out.print("Enter client ID: ");
             String Id = scanner.nextLine();
            if (reservation.containsKey(Id)) {
                reservatio.put( Id, makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end));

            } else {
                System.out.println("resevation with ID " + studentID + " does not exist.");
            }
                    
            System.out.println("Reservation " + reservationID + " modified.");
        }

    //Modifies a pre-existing reservation making sure that the client is able to get Get their money back and give it to the next person
    // in line if we had time to implement it, called by main
        @Override
        public void cancelReservation(String reservationID) {
            
            System.out.println("Reservation " + reservationID + " canceled.");

            System.out.print("Enter client ID: ");
             String Id = scanner.nextLine();

            if (reservation.containsKey(Id)){
            
                reservation.remove(Id);
                System.out.println("reservation with ID " + studentID + " has been deleted.");
            } else {
                System.out.println("No reservation found with ID: " + studentID);
            }
        }
    
        //Shows you the available vehicles or at least it should’ve, called by main
        // @Override
        public void getVehicleAvailability(String station, LocalDateTime time) {
            // TODO: Implement vehicle availability check
            System.out.println("Checking availability at station " + station + " for time " + time);
        }
    
        // Was meant to be the area where people were added to the waitlist, but it was done somewhere else.
        //  It hasn’t been deleted due to the fact that the interface requires it for the implementation for now. called by main
        @Override
        public void addToWaitlist(String OperatorID, String vehicleType, String station) {
            // TODO: Implement waitlist logic
            System.out.println("Added operator " + OperatorID + " to waitlist for vehicle type " + vehicleType + " at station " + station);
        }


//Helps in the creation of a vehicle with a switch statement inside of it 
//in order to facilitate we have to chocking which vehicle you want to create called by main.
        // @Override
        void addVehicle(  LocalDateTime time, HashMap<String, Operator> operatorMap){

            Scanner scanner = new Scanner(System.in);

            Object vehicle = null;

            System.out.println("\nWhat type of vehicle do you want to add?");
                    System.out.println("1 - Bicycle");
                    System.out.println("2 - Scooter");
                    System.out.println("3 - Skateboard");
                    System.out.print("Your choice: ");
                    int vehicleType = Integer.parseInt(scanner.nextLine());

                    String type = switch (vehicleType) {
                        case 1 -> {
                            System.out.println("Enter a description for the vehicle:");
                            String description = scanner.nextLine();

                            System.out.println("Enter the owner's ID:");
                            String ownerID = scanner.nextLine();

                            // Check if the owner exists and if they are an owner or both
                            Operator owner = operatorMap.get(ownerID); 

                            if (owner != null) {
                                if (owner.isOwner() || owner.isBoth()) { 
                                    vehicle = new Bicycle(description, name, ownerID);//i dont know if this->name works better here, add eventually 
                                    AddVehicle(vehicle);
                                } else {
                                    System.out.println("The ID provided does not belong to an owner or both.");
                                            }
                            } else {
                                System.out.println("Owner ID not found.");
                            }
                            break;  // have to make sure this dosent continue to the station stuff maybe make it a loop later or something
                        }
                        case 2 -> "Scooter";//later repet the same stuff as before or above
                        case 3 -> "Skateboard";
                        default -> "Unknown";
                    };

                   





//Modify a pre-existing vehicle called by Main
                    @Override
                    public  void modifyVehicle( HashMap<String, Operator> operatorMap, ) {

                        System.out.println("Enter the owner's ID:");
                            String ownerID = scanner.nextLine();

        // Check if the owner exists and if they are an owner or both
        Operator owner = operatorMap.get(ownerID); 

        if (owner != null) {
            if (owner.isOwner() || owner.isBoth())
                
                // if (operatorMap.containsKey(studentID)) {
                //     Operator operator = createOperatorFromInput();
                
                //     //the id should be removed from the suff or something since it being used as the key
                //     operatorMap.put(studentID, operator);
                // } else {
                //     System.out.println("Operator with ID " + studentID + " does not exist.");
                // }
                //         }
                
                //removes a pre-existing vehicle called by Main
                    @Override
                    public  void removeVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap){

                        RemoveVehicle();

      
                //shows you all the registerd vehicles called by Main
                    @Override
                    public void listVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap) {
                
                printInventory();

                    }
                }
            }
        }
    }
}
}

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
    // public void addStation(Station station) {
    //     stations.put(station.getName(), station);
    // };
    
    // @Override
    // public void removeStation(String stationName) {
    //     stations.remove(stationName);
    // };
    

    //--------------------------------------------------
    // Interface Methods Implementation
    //--------------------------------------------------
    // @Override // commented to get rid of error
    // @Override
    public Station getStation(String stationName) {
        return stations.get(stationName);
    };
    
        // @Override
        public void makeReservation(HashMap<String, Operator> operatorMap, boolean mod, String MODid) {
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

                                            if(!mod){
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
                                            else{
                                            reservation.put(MODid, bicycle);
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

                                            if(!mod){
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
                                            else{
                                            reservation.put(MODid, scooter);
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
                                            if(!mod){
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
                                                else{
                                                    reservation.put(MODid, skateboard);
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
    
        @Override
        public void modifyReservation(HashMap<String, Operator> operatorMap) {
            
            boolean ismode = true;
            System.out.print("Enter client ID: ");
             String Id = scanner.nextLine();
            if (reservation.containsKey(Id)) {
                reservatio.put( Id, makeReservation( operatorMap, ismode , Id));
            } else {
                System.out.println("resevation with ID " + studentID + " does not exist.");
            }
                    
            System.out.println("Reservation " + reservationID + " modified.");
        }
    
        @Override
        public void cancelReservation( HashMap<String, Operator> operatorMap ) {
            // TODO: Implement cancellation logic
           // System.out.println("Reservation " + reservationID + " canceled.");

            System.out.print("Enter client ID: ");
             String Id = scanner.nextLine();

            if (reservation.containsKey(Id)){
            
                reservation.remove(Id);
                System.out.println("reservation with ID " + studentID + " has been deleted.");
            } else {
                System.out.println("No reservation found with ID: " + studentID);
            }
        }
    
        // @Override
        public void getVehicleAvailability( HashMap<String, Operator> operatorMap ) {
            // TODO: Implement vehicle availability check
            System.out.println("Checking availability at station " + station + " for time " + time);
        }
    
        @Override
        public void addToWaitlist( HashMap<String, Operator> operatorMap ) {
            // TODO: Implement waitlist logic
            System.out.println("Added operator " + OperatorID + " to waitlist for vehicle type " + vehicleType + " at station " + station);
        }

        // @Override
        void addVehicle( HashMap<String, Operator> operatorMap){

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
                                    Object vehicle = new Bicycle(description, name, ownerID);//i dont know if this->name works better here, add eventually 
                                    AddVehicle(vehicle);
                                } else {
                                    System.out.println("The ID provided does not belong to an owner or both.");
                                            }
                            } else {
                                System.out.println("Owner ID not found.");
                            }
                            break;  // have to make sure this dosent continue to the station stuff maybe make it a loop later or something
                        }
                        case 2 -> {
                            System.out.println("Enter a description for the vehicle:");
                            String description = scanner.nextLine();

                            System.out.println("Enter the owner's ID:");
                            String ownerID = scanner.nextLine();

                            // Check if the owner exists and if they are an owner or both
                            Operator owner = operatorMap.get(ownerID); 

                            if (owner != null) {
                                if (owner.isOwner() || owner.isBoth()) { 
                                    Object vehicle = new Scooter(description, name, ownerID);//i dont know if this->name works better here, add eventually 
                                    AddVehicle(vehicle);
                                } else {
                                    System.out.println("The ID provided does not belong to an owner or both.");
                                            }
                            } else {
                                System.out.println("Owner ID not found.");
                            }
                            break;  // have to make sure this dosent continue to the station stuff maybe make it a loop later or something
                        };//later repet the same stuff as before or above
                        case 3 ->{
                            System.out.println("Enter a description for the vehicle:");
                            String description = scanner.nextLine();

                            System.out.println("Enter the owner's ID:");
                            String ownerID = scanner.nextLine();

                            // Check if the owner exists and if they are an owner or both
                            Operator owner = operatorMap.get(ownerID); 

                            if (owner != null) {
                                if (owner.isOwner() || owner.isBoth()) { 
                                    Object vehicle = new Skateboard(description, name, ownerID);//i dont know if this->name works better here, add eventually 
                                    AddVehicle(vehicle);
                                } else {
                                    System.out.println("The ID provided does not belong to an owner or both.");
                                            }
                            } else {
                                System.out.println("Owner ID not found.");
                            }
                            break;;
                        default -> "Unknown";
                    };


                    @Override
                    public  void modifyVehicle( HashMap<String, Operator> operatorMap ) {

                        System.out.println("Enter the owner's ID:");
                            String ownerID = scanner.nextLine();

        // Check if the owner exists and if they are an owner or both
        Operator owner = operatorMap.get(ownerID); 

        if (owner != null) {
            if (owner.isOwner() || owner.isBoth())
                    @Override
                    public  void removeVehicle(HashMap<String, Operator> operatorMap){

                        RemoveVehicle();

      
                
                    @Override
                    public void listVehicle(HashMap<String, Operator> operatorMap){
                
                printInventory();

                    }
                }
            }
        }
    }
}
        }
    }

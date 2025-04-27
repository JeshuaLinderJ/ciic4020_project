package services;

import interfaces.StationManagement;
import models.Reservation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import models.Station;




public class StationManager implements StationManagement {

    //--------------------------------------------------
    // Instance Variables
    //--------------------------------------------------
    private Map<String, Station> stations; // this can be changed for an int if we want to have them be like 0-4 or something


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
    @Override
    public Station getStation(String stationName) {
        return stations.get(stationName);
    };
    
        @Override
        public void makeReservation(String OperatorID, String vehicleID, LocalDateTime start, LocalDateTime end) {
            // TODO: Implement reservation logic
            System.out.println("Reservation made for operator " + OperatorID + " and vehicle " + vehicleID);
        }
    
        @Override
        public void modifyReservation(String reservationID, Reservation updated) {
            // TODO: Implement modification logic
            System.out.println("Reservation " + reservationID + " modified.");
        }
    
        @Override
        public void cancelReservation(String reservationID) {
            // TODO: Implement cancellation logic
            System.out.println("Reservation " + reservationID + " canceled.");
        }
    
        @Override
        public void getVehicleAvailability(String station, LocalDateTime time) {
            // TODO: Implement vehicle availability check
            System.out.println("Checking availability at station " + station + " for time " + time);
        }
    
        @Override
        public void addToWaitlist(String OperatorID, String vehicleType, String station) {
            // TODO: Implement waitlist logic
            System.out.println("Added operator " + OperatorID + " to waitlist for vehicle type " + vehicleType + " at station " + station);
        }

        @Override
        void addVehicle(  LocalDateTime time, HashMap<String, Operator> operatorMap){

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
        break;  // have to make sure this dosent contione ro the station stuff maybe make it a loop later or some
    }
                        };
                        case 2 -> "Scooter";//later repet the same stuff as before or above
                        case 3 -> "Skateboard";
                        default -> "Unknown";
                    };

                   






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
                





                    @Override
                    public  void removeVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap){

                        RemoveVehicle();

      
                
                    @Override
                    public void listVehicle(LocalDateTime time, HashMap<String, Operator> operatorMap) {
                
                printInventory();

                    }
                }
            }
        }
    }

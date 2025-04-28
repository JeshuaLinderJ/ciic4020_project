package services;

import java.util.HashMap;
import models.Operator;
import models.Station;
import models.stations.Biology;
import models.stations.Business;
import models.stations.ChemicalEngineering;
import models.stations.Stefani;
import models.stations.StudentCenter;

/**
 * Takes care of system stuff, mostly just coordinates between other parts.
 * I moved all the logic from Main.java over here to clean things up.
 * 
 * TODO: Maybe refactor some of this later, kinda rushed it
 */
public class SystemController {
    // UI stuff and managers
    private UIManager ui;
    private ServiceHoursManager svcHoursMgr;
    private OperatorManager opMgr;
    private HashMap<String, Operator> operatorMap;
    
    // All our stations
    private Station stefani;
    private Station studentCenter;
    private Station biology;
    private Station businessAdm;
    private Station chemEng;
    
    /**
     * Sets everything up
     */
    public SystemController() {
        this.ui = new UIManager();
        this.svcHoursMgr = ServiceHoursManager.getInstance();
        this.opMgr = new OperatorManager();
        this.operatorMap = new HashMap<>();
        
        // make all the stations
        this.stefani = new Stefani();
        this.studentCenter = new StudentCenter();
        this.biology = new Biology();
        this.businessAdm = new Business();
        this.chemEng = new ChemicalEngineering();
    }
    
    // Gets everything going
    public void initialize() {
        ui.showWelcomeMessage();
        ui.promptTestMode();
    }
    
    // Main program loop
    public void run() {
        boolean keepRunning = true;
        
        while (keepRunning) {
            ui.displayMainMenu();
            int choice = ui.getValidIntInput("Your choice: ", 0, 9);
            
            // Check if we can even do this right now
            if (!svcHoursMgr.isWithinServiceHours() && (choice >= 1 && choice <= 3)) {
                ui.showServiceHoursMessage();
                continue;
            }
            
            switch (choice) {
                case 1 -> handleClientManagement();
                case 2 -> handleVehicleManagement();
                case 3 -> handleReservationManagement();
                case 4 -> handleWaitlistManagement();
                case 8 -> toggleTestMode();
                case 9 -> svcHoursMgr.printServiceStatus();
                case 0 -> keepRunning = false;
            }
        }
        
        System.out.println("Exiting program. Goodbye!");
        ui.close();
    }
    
    // Client stuff - add/modify/etc
    private void handleClientManagement() {
        ui.displayClientMenu();
        int clientChoice = ui.getValidIntInput("Your choice: ", 1, 4);
        
        switch (clientChoice) {
            case 1 -> opMgr.addOperator(operatorMap);
            case 2 -> {
                String modId = ui.getStringInput("Enter client/Owner ID to modify: ");
                opMgr.modifyOperator(modId, operatorMap);
            }
            case 3 -> {
                String delId = ui.getStringInput("Enter client/Owner ID to delete: ");
                opMgr.deleteOperator(delId, operatorMap);
            }
            case 4 -> opMgr.listRegisteredOperators(operatorMap);
        }
    }
    
    // Vehicle management - add vehicles, remove, etc
    private void handleVehicleManagement() {
        ui.displayStationManagementMenu();
        int stationChoice1 = ui.getValidIntInput("Your choice: ", 1, 4);
        
        ui.displayStationSelectionMenu();
        int stationChoice2 = ui.getValidIntInput("Your choice: ", 1, 5);
        
        Station station = getStationByChoice(stationChoice2);
        if (station != null) {
            switch (stationChoice1) {
                case 1 -> station.addVehicle(operatorMap);
                case 2 -> station.removeVehicle(operatorMap);
                case 3 -> station.modifyVehicle(operatorMap);
                case 4 -> station.listVehicle(operatorMap);
            }
        }
    }
    
    // Handles reservations
    private void handleReservationManagement() {
        boolean isMod = false;
        String modId = null; // not used unless we're modifying
        
        ui.displayReservationMenu();
        int resChoice = ui.getValidIntInput("Your choice: ", 1, 4);
        
        ui.displayStationSelectionMenu();
        int stationChoice = ui.getValidIntInput("Your choice: ", 1, 5);
        
        Station station = getStationByChoice(stationChoice);
        if (station != null) {
            switch (resChoice) {
                case 1 -> station.makeReservation(operatorMap, isMod, modId);
                case 2 -> station.modifyReservation(operatorMap);
                case 3 -> station.cancelReservation(operatorMap);
                case 4 -> station.listVehicle(operatorMap);
            }
        }
    }
    
    // Waitlist handling
    private void handleWaitlistManagement() {
        // gotta check hours first!!
        if (!svcHoursMgr.isWithinServiceHours()) {
            ui.showServiceHoursMessage();
            return;
        }
        
        ui.displayWaitlistMenu();
        int waitlistChoice = ui.getValidIntInput("Your choice: ", 1, 4);
        
        switch (waitlistChoice) {
            case 1 -> viewAllWaitlists();
            case 2 -> addToWaitlist();
            case 3 -> viewStationWaitlist();
            case 4 -> confirmWaitlistReservation();
        }
    }
    
    // Shows waitlists for all stations
    private void viewAllWaitlists() {
        System.out.println("\n===== Waitlist Status Across All Stations =====");
        
        displayStationWaitlist("Stefani Building", stefani);
        displayStationWaitlist("Student Center", studentCenter);
        displayStationWaitlist("Biology Building", biology);
        displayStationWaitlist("Business Admin Building", businessAdm);
        displayStationWaitlist("Chemical Engineering Building", chemEng);
        
        System.out.println("\n==============================================");
    }
    
    /**
     * Shows waitlist stats for a station
     * I'm pretty sure I could make this code more efficient but it works fine
     */
    private void displayStationWaitlist(String stationName, Station station) {
        System.out.println("\n" + stationName + " Waitlists:");
        
        // get the waitlists
        var bikeList = station.getWaitlistBicycle();
        var scooterList = station.getWaitlistScooter();
        var skateList = station.getWaitlistSkateboard();
        
        // show counts
        System.out.println("  Bicycle waitlist: " + bikeList.size() + " people waiting");
        System.out.println("  Scooter waitlist: " + scooterList.size() + " people waiting");
        System.out.println("  Skateboard waitlist: " + skateList.size() + " people waiting");
        
        // Show details if any waitlists have people
        if (!bikeList.isEmpty() || !scooterList.isEmpty() || !skateList.isEmpty()) {
            System.out.println("\n  Waitlisted Clients:");
            
            if (!bikeList.isEmpty()) {
                System.out.println("  Bicycle waitlist:");
                int pos = 1;
                for (String clientId : bikeList) {
                    Operator client = operatorMap.get(clientId);
                    String clientName = (client != null) ? client.getName() : "Unknown Client";
                    System.out.println("    " + pos + ". " + clientName + " (ID: " + clientId + ")");
                    pos++;
                }
            }
            
            if (!scooterList.isEmpty()) {
                System.out.println("  Scooter waitlist:");
                int pos = 1;
                for (String clientId : scooterList) {
                    Operator client = operatorMap.get(clientId);
                    String clientName = (client != null) ? client.getName() : "Unknown Client";
                    System.out.println("    " + pos + ". " + clientName + " (ID: " + clientId + ")");
                    pos++;
                }
            }
            
            if (!skateList.isEmpty()) {
                System.out.println("  Skateboard waitlist:");
                int pos = 1;
                for (String clientId : skateList) {
                    Operator client = operatorMap.get(clientId);
                    String clientName = (client != null) ? client.getName() : "Unknown Client";
                    System.out.println("    " + pos + ". " + clientName + " (ID: " + clientId + ")");
                    pos++;
                }
            }
        }
    }
    
    // Adds a client to a waitlist
    private void addToWaitlist() {
        ui.displayStationSelectionMenu();
        int stationChoice = ui.getValidIntInput("Your choice: ", 1, 5);
        
        Station station = getStationByChoice(stationChoice);
        if (station != null) {
            station.addToWaitlist(operatorMap);
        }
    }
    
    // View a single station's waitlist
    private void viewStationWaitlist() {
        ui.displayStationSelectionMenu();
        int stationChoice = ui.getValidIntInput("Your choice: ", 1, 5);
        
        Station station = getStationByChoice(stationChoice);
        if (station != null) {
            String stationName = station.getName();
            displayStationWaitlist(stationName, station);
        }
    }
    
    // Confirm a reservation from the waitlist
    private void confirmWaitlistReservation() {
        String clientID = ui.getStringInput("Enter client ID to confirm reservation: ");
        
        // Check if client exists
        Operator client = operatorMap.get(clientID);
        if (client == null) {
            System.out.println("[x] Client ID not found in the system.");
            return;
        }
        
        if (!client.isClient() && !client.isBoth()) {
            System.out.println("[x] User with ID " + clientID + " is not registered as a client.");
            return;
        }
        
        // Find out which station they're waitlisted at
        ui.displayStationSelectionMenu();
        int stationChoice = ui.getValidIntInput("Your choice: ", 1, 5);
        
        Station station = getStationByChoice(stationChoice);
        if (station == null) {
            System.out.println("[x] Invalid station selection.");
            return;
        }
        
        // Try to confirm it
        boolean confirmed = station.confirmWaitlistReservation(clientID, operatorMap);
        
        if (!confirmed) {
            System.out.println("[x] Failed to confirm waitlist reservation. Either the 5-minute time window has expired, or you are not first in line on the waitlist.");
            System.out.println("    Please join the waitlist again if you still want to reserve a vehicle.");
        }
    }
    
    // Test mode toggle
    private void toggleTestMode() {
        svcHoursMgr.setTestModeEnabled(!svcHoursMgr.isTestModeEnabled());
        System.out.println("Test mode " + (svcHoursMgr.isTestModeEnabled() ? "enabled" : "disabled") + ".");
        svcHoursMgr.printServiceStatus();
    }
    
    // Gets a station based on the menu choice
    private Station getStationByChoice(int choice) {
        return switch (choice) {
            case 1 -> stefani;
            case 2 -> studentCenter;
            case 3 -> biology;
            case 4 -> businessAdm;
            case 5 -> chemEng;
            default -> null;
        };
    }
}
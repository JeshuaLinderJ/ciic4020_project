package services;

import java.util.Scanner;

/**
 * Handles all the UI stuff - menus, user input, etc.
 * Moved this from Main class to keep things cleaner.
 */
public class UIManager {
    private Scanner scan; // shortened from scanner
    private ServiceHoursManager hoursMgr;
    
    // Constructor - just basic setup
    public UIManager() {
        this.scan = new Scanner(System.in);
        this.hoursMgr = ServiceHoursManager.getInstance();
    }
    
    // Show welcome screen
    public void showWelcomeMessage() {
        System.out.println("======================================================");
        System.out.println("  Electric Vehicle Reservation System");
        System.out.println("======================================================");
        System.out.println("Service hours: 7:00 AM - 6:00 PM, Monday to Friday");
        hoursMgr.printServiceStatus();
    }
    
    // Ask about test mode if needed
    public void promptTestMode() {
        if (!hoursMgr.isWithinServiceHours()) {
            System.out.println("\nWanna enable test mode to bypass service hours? (yes/no)");
            String resp = scan.nextLine().trim().toLowerCase();
            if (resp.equals("yes")) {
                hoursMgr.setTestModeEnabled(true);
                System.out.println("Test mode on! You can do operations anytime now.");
            } else {
                System.out.println("Test mode off. System will enforce service hours.");
                System.out.println("You can still look around but most operations won't work.");
            }
        }
    }
    
    // Main menu display
    public void displayMainMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1 - Work with Clients");
        System.out.println("2 - Work with Vehicles");
        System.out.println("3 - Work with Reservations");
        System.out.println("4 - Manage Waitlists");
        System.out.println("8 - Toggle test mode (current: " + (hoursMgr.isTestModeEnabled() ? "ON" : "OFF") + ")");
        System.out.println("9 - Check service hours status");
        System.out.println("0 - Quit");
    }
    
    // Client menu options
    public void displayClientMenu() {
        System.out.println("\nClient Management:");
        System.out.println("1 - Add a client");
        System.out.println("2 - Modify a client");
        System.out.println("3 - Delete a client");
        System.out.println("4 - List all registered clients");
    }
    
    // Station/vehicle management menu
    public void displayStationManagementMenu() {
        System.out.println("\nStation Management:");
        System.out.println("1 - Add a vehicle");
        System.out.println("2 - Remove a vehicle");
        System.out.println("3 - Modify a vehicle");
        System.out.println("4 - List all registered vehicles");
    }
    
    // Station selection menu
    public void displayStationSelectionMenu() {
        System.out.println("\nWhere would you like to work with:");
        System.out.println("1 - Stefani");
        System.out.println("2 - Student Center");
        System.out.println("3 - Biology");
        System.out.println("4 - Business Administration");
        System.out.println("5 - Chemical Engineering");
    }
    
    // Reservation menu
    public void displayReservationMenu() {
        System.out.println("\nReservation Options:");
        System.out.println("1 - Make a reservation");
        System.out.println("2 - Modify a reservation");
        System.out.println("3 - Cancel a reservation");
        System.out.println("4 - Check vehicle availability");
    }
    
    // Waitlist menu
    public void displayWaitlistMenu() {
        System.out.println("\nWaitlist Management:");
        System.out.println("1 - View all waitlists");
        System.out.println("2 - Add client to waitlist");
        System.out.println("3 - View waitlist for specific station");
        System.out.println("4 - Confirm waitlist reservation");
    }
    
    // Get number input with validation
    // This checks for valid range too
    public int getValidIntInput(String prompt, int min, int max) {
        int input;
        boolean valid = false;
        
        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scan.nextLine().trim());
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a number! Try again.");
                input = min - 1; // just some invalid value to keep loop going
            }
        } while (!valid);
        
        return input;
    }
    
    // Get text input - simple wrapper
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }
    
    // Show message when outside service hours
    public void showServiceHoursMessage() {
        System.out.println("Sorry! Can't do that outside our hours (7:00 AM - 6:00 PM, Mon-Fri)");
        System.out.println("   Turn on test mode if you need to bypass this restriction.");
    }
    
    // Cleanup
    public void close() {
        scan.close(); // avoid resource leak
    }
}
package services;

/**
 * MAIN APPLICATION ENTRY POINT
 * 
 * This class serves as the entry point for the Electric Vehicle Reservation System.
 * It has been refactored to only instantiate the SystemController and start the application.
 */
public class Main {
    public static void main(String[] args) {
        // Create and initialize the system controller
        SystemController controller = new SystemController();
        
        // Initialize the system
        controller.initialize();
        
        // Run the main system loop
        controller.run();
    }
}


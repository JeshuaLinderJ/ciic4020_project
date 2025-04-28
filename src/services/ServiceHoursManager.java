package services;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * Handles service hours restrictions
 * 
 * Makes sure operations only happen during allowed hours
 * (7am-6pm, Mon-Fri) unless test mode is on
 */
public class ServiceHoursManager {
    
    // Singleton pattern - only one instance allowed
    private static ServiceHoursManager instance;
    
    // When we're open
    private static final LocalTime OPEN_TIME = LocalTime.of(7, 0); // 7am
    private static final LocalTime CLOSE_TIME = LocalTime.of(18, 0);  // 6pm
    
    // For testing & development
    private boolean testMode = false;
    
    // Private constructor - can't make new instances directly
    private ServiceHoursManager() {
        // nothing to do here really
    }
    
    // Get the single instance (create if needed)
    public static ServiceHoursManager getInstance() {
        if (instance == null) {
            instance = new ServiceHoursManager();
        }
        return instance;
    }
    
    /**
     * Checks if current time is during our service hours
     * Test mode bypasses this completely
     */
    public boolean isWithinServiceHours() {
        // Test mode? Then we're always open!
        if (testMode) {
            return true;
        }
        
        // Get current time info
        ZonedDateTime now = ZonedDateTime.now();
        DayOfWeek today = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime();
        
        // Is it a weekday? (mon-fri)
        boolean isWeekday = today.getValue() <= 5;
        
        // Is it between opening & closing time?
        boolean rightTime = !currentTime.isBefore(OPEN_TIME) && !currentTime.isAfter(CLOSE_TIME);
        
        // Both have to be true
        return isWeekday && rightTime;
    }
    
    /**
     * Turn test mode on/off
     */
    public void setTestModeEnabled(boolean enabled) {
        this.testMode = enabled;
    }
    
    /**
     * Is test mode currently on?
     */
    public boolean isTestModeEnabled() {
        return testMode;
    }
    
    /**
     * Prints current status - in hours or not
     */
    public void printServiceStatus() {
        if (testMode) {
            System.out.println("!!! TEST MODE ON - All operations allowed anytime !!!");
            return;
        }
        
        if (isWithinServiceHours()) {
            System.out.println("Within service hours - all operations available");
        } else {
            System.out.println("CLOSED - Outside of service hours (7:00 AM - 6:00 PM, Mon-Fri)");
            System.out.println("    Need to do operations now? Enable test mode.");
        }
    }
}
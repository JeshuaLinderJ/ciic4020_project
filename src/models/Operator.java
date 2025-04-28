package models;

import java.time.*;
import java.util.Scanner;


// public class Operator {
//     private String name;
//     private String id;
//     private String UprEmail;
//     private boolean isOwner;
//     private boolean isClient;
//     private boolean isBoth;
//     private String phoneNum;

    //make branch later, if user show the things they have in waitlist and if ownerd shiw vichels they own 
    
     // Constructor
//     public Operator(String name, String id, String UprEmail, boolean isOwner, boolean isClient, boolean isBoth, String phoneNum) {
//         this.name = name;
//         this.id = id;
//         this.UprEmail = UprEmail;
//         this.isOwner = isOwner;
//         this.isClient = isClient;
//         this.isBoth = isBoth;
//         this.phoneNum = phoneNum;
//     }

//     // Getters
//     public String getName() { return name; }
//     public String getId() { return id; }
//     public String getUprEmail() { return UprEmail; }
//     public String getPhoneNum() { return phoneNum; }
//     public boolean isOwner() { return isOwner; }
//     public boolean isClient() { return isClient; }
//     public boolean isBoth() { return isBoth; }

//     // Time check method
//     public static boolean isWithinServiceHours() {
//         ZonedDateTime now = ZonedDateTime.now();
//         DayOfWeek day = now.getDayOfWeek();
//         LocalTime start = LocalTime.of(7, 0);
//         LocalTime end = LocalTime.of(18, 0);
//         LocalTime currentTime = now.toLocalTime();

//         boolean isWeekday = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
//         boolean isWithinTime = !currentTime.isBefore(start) && !currentTime.isAfter(end);

//         return isWeekday && isWithinTime;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getId() {
//         return id;
//     }

//     //same
//     public String UprEmail() {
//         return UprEmail;
//     }

//     public String PhoneNum() {
//         return PhoneNum;
//     }

//     public boolean isOwner() {
//         return isOwner;
//     }

//     public boolean isclient() {
//         return isclient;
//     }




// }

// ----------------------------------------------------------
// OPERATOR MODEL
//
// This class represents users of the system as defined in
// plan-of-attack.md sections 2 (Users) and 5 (Operator Functionality).
//
// Needs to be expanded to include:
//   • Email and phone contact information
//   • User type (Client, Owner, or Both)
//   • Balance tracking (expenses or income)
//   • References to owned vehicles (limited to 2 max)
//
// Core functionality:
//   • Store user identification and contact details
//   • Track user roles and permissions
//   • Manage financial aspects of vehicle rentals
//   • Maintain relationship between users and their vehicles


//this is for later
//make branch later, if user show the things they have in waitlist and if ownerd shiw vichels they own 

package models;

//varibles for the clients/owners
public class Operator {
    private String name;
    private String id;
    private String UprEmail;
    private boolean isOwner;
    private boolean isClient;
    private boolean isBoth;
    private String phoneNum;
    private double credit;
    private int reserved;
    private int registered;
    private double profit;

    ////creats the object for the clients/owners
    public Operator(String name, String id, String UprEmail, boolean isOwner, boolean isClient, boolean isBoth, String phoneNum) {
        this.name = name;
        this.id = id;
        this.UprEmail = UprEmail;
        this.isOwner = isOwner;
        this.isClient = isClient;
        this.isBoth = isBoth;
        this.phoneNum = phoneNum;

        // Default values for financial tracking
        this.credit = 0.0;
        this.reserved = 0;
        this.registered = 0;
        this.profit = 0.0;
    }

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public String getUprEmail() { return UprEmail; }
    public String getPhoneNum() { return phoneNum; }
    public boolean isOwner() { return isOwner; }
    public boolean isClient() { return isClient; }
    public boolean isBoth() { return isBoth; }
    public double getCredit() { return credit; }
    public int getReserved() { return reserved; }
    public int getRegistered() { return registered; }
    public double getProfit() { return profit; }

    // Setters
    public void setCredit(double credit) { this.credit = credit; }
    public void setReserved(int reserved) { this.reserved = reserved; }
    public void setRegistered(int registered) { this.registered = registered; }
    public void setProfit(double profit) { this.profit += profit; }

    //the display methode for Operator
    public void display() {
        System.out.println("\nOperator Created:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Email: " + UprEmail);
        System.out.println("Phone: " + phoneNum);
        System.out.println("Is Owner: " + isOwner);
        System.out.println("Is Client: " + isClient);
        System.out.println("Is Both: " + isBoth);
        System.out.println("Credit: $" + credit);
        System.out.println("Reservations Made: " + reserved);
        System.out.println("Items Registered: " + registered);
        System.out.println("Profit: $" + profit);
    }
}
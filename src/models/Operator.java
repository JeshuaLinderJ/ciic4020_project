package models;

public class Operator {
    private String name;
    private String id;

    public Operator(String name, String id) {
        this.name = name;
        this.id = id;
    };

    public String getName() {
        return name;
    };

    public String getId() {
        return id;
    };
};

// ----------------------------------------------------------
// OPERATOR MODEL
//
// This class represents users of the system as defined in
// plan-of-attack.md sections 2 (Users) and 5 (Operator Functionality).
//
// Needs to be expanded to include:
//   - Email and phone contact information
//   - User type (Client, Owner, or Both)
//   - Balance tracking (expenses or income)
//   - References to owned vehicles (limited to 2 max)
//
// Core functionality:
//   - Store user identification and contact details
//   - Track user roles and permissions
//   - Manage financial aspects of vehicle rentals
//   - Maintain relationship between users and their vehicles
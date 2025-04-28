[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3ADzlwbL)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=19336179&assignment_repo_type=AssignmentRepo)

# Electric Vehicle Reservation System

A comprehensive system for managing electric vehicle rentals across campus stations.

## Team

Jeshua Linder Jiménez    |   jeshua.linder@upr.edu
Sebastian M. Seguí     |   sebastian.segui@upr.edu 

## Project Overview

This system allows operators to manage the reservation of electric vehicles (bicycles, scooters, and skateboards) across five campus stations. The system handles user management, vehicle inventory, reservations, and waitlists.

### Features

- **User Management**: Add, modify, and delete users (clients and owners)
- **Vehicle Management**: Add, modify, and delete vehicles at stations
- **Reservation System**: Create, modify, and cancel reservations
- **Waitlist Management**: Handle situations when vehicles are unavailable
- **Service Hour Restrictions**: Operations limited to 7:00 AM - 6:00 PM, Monday-Friday

## Project Structure

```
ciic4020_project/
├── src/                        # Source code
│   ├── interfaces/             # Core interfaces
│   │   ├── OperatorManagement.java
│   │   ├── ReservationOperations.java
│   │   ├── StationManagement.java
│   │   ├── TransactionHistory.java
│   │   ├── UndoAction.java
│   │   └── Vehicle.java
│   ├── models/                 # Data models
│   │   ├── Operator.java       # User representation
│   │   ├── Reservation.java    # Reservation data
│   │   ├── Station.java        # Base station class
│   │   ├── stations/           # Specific campus locations
│   │   │   ├── Biology.java
│   │   │   ├── Business.java
│   │   │   ├── ChemicalEngineering.java
│   │   │   ├── Stefani.java
│   │   │   └── StudentCenter.java
│   │   └── vehicles/           # Vehicle types
│   │       ├── Bicycle.java
│   │       ├── Scooter.java
│   │       └── Skateboard.java
│   └── services/               # Business logic
│       ├── Main.java           # Entry point
│       ├── OperatorManager.java
│       ├── ReservationManager.java
│       ├── ServiceHoursManager.java
│       ├── StationManager.java
│       ├── SystemController.java
│       └── UIManager.java
├── bin/                        # Compiled classes
├── documentation/              # Project documentation
│   ├── instructions.txt        # Assignment requirements
│   └── plan-of-attack.md       # Development plan
└── README.md                   # This file
```

## Core Components

### 1. System Controller

The `SystemController` serves as the central coordinator of the entire system, connecting all components together and managing the program's flow.

### 2. User Interface

The `UIManager` handles all user interaction, presenting menus and collecting input through a console interface.

### 3. Service Hours Management

The `ServiceHoursManager` enforces the system's operating hours (7:00 AM - 6:00 PM, Monday-Friday) with an optional test mode to bypass these restrictions.

### 4. User Management

The `OperatorManager` handles user creation, modification, and deletion. Users can be clients, owners, or both.

### 5. Vehicle System

Three concrete vehicle classes implement the `Vehicle` interface:
- `Bicycle` - 3 credits first hour, 2 credits additional hours
- `Scooter` - 2 credits first hour, 1 credit additional hours
- `Skateboard` - 1 credit first hour, 0.5 credits additional hours

### 6. Station System

Five campus stations (each extending the base `Station` class):
- Stefani Building (capacity: 40)
- Student Center (capacity: 90)
- Biology Building (capacity: 35)
- Chemical Engineering Building (capacity: 45)
- Business Administration Building (capacity: 45)

### 7. Reservation System

The `Station` class handles reservations, including creation, modification, cancellation, and waitlist management.

## Data Structures Implementation

The system implements all required data structures:

1. **Set** (HashSet): Stores vehicles at each station
2. **LinkedList**: Stores the reservation history and implements Queue interface for waitlists
3. **HashMap**: Maps operator IDs to Operator objects
4. **Queue**: Manages waitlists when vehicles are unavailable
5. **Scanner**: Handles user input throughout the system

## Business Rules

- Service hours: 7:00 AM - 6:00 PM, Monday-Friday
- Maximum 2 vehicles per owner
- One reservation per client at a time
- Maximum reservation duration: 6 hours
- Each station has a fixed capacity

## How to Run

1. Compile the project:
```
./compile.sh
```

2. Run the application:
```
./build.sh
```

3. Follow the on-screen instructions to navigate the system

## Test Mode

The system includes a test mode that can be enabled to bypass service hour restrictions for development and testing purposes.

## Future Enhancements

- Graphical user interface
- Database integration for persistent storage
- Mobile application for client reservations
- Advanced analytics and reporting
- Payment processing integration
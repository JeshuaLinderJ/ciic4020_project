# ciic4020_project
Sistema de Reservación de Vehículos Eléctricos Particulares

Jeshua Linder Jiménez    |   jeshua.linder@upr.edu
Sebastian M. Seguí     |   sebastian.segui@upr.edu 

***

src/
├── interfaces/
│   ├── OperatorManagement.java
│   ├── ReservationOperations.java
│   ├── StationManagement.java
│   ├── TransactionHistory.java
│   ├── UndoAction.java
│   └── Vehicle.java
└── models/
    ├── Operator.java
    ├── Reservation.java
    ├── Station.java
    └── 

(I'll change this when we've really begun implementing functions that get the ball rolling)

***

# Branching Strategy I'd Like To Do (preferably)

## Main Branches
    main - Production-ready code
    development - Integration branch for ongoing development

## Feature Branches
###### Create feature branches for each component using the naming convention:

Examples:

feature/vehicles/bicycle-implementation
feature/reservation/scheduling-system
feature/users/authentication

- There could also be debugging branches such as debug/reservation/repeat-output

***

# Division of Work (subject to change as structure evolves)

## Vehicle System
- Implement the Vehicle interface
- Create concrete classes for Bicycle, Scooter, and Skateboard
- Implement usage cost calculations

## User Management
- Complete Operator model
- Implement OperatorManagement interface
- Create user types (Client, Owner, Both)

## Station Management
- Enhance Station model
- Implement StationManagement interface
- Track vehicle inventory by station

## Reservation System
- Complete Reservation model
- Implement ReservationOperations interface
- Develop scheduling algorithms

## Transaction & History
- Implement TransactionHistory interface
- Create payment and balance tracking
- Develop logging system

*** 

## Workflow

###### We don't need some crazy code review process, just text changes, through the desired branch system, it shouldn't be difficult to backtrack mistakes or pushes to development branch once pulled from specific feature branch

***

## Responsibility

##### It's detailed in the plan-of-attack, but it seems like the data structures go well with each, let me know what you think:

- Set (Vehicle inventory) | Vehicle System
- LinkedList (Transaction logs) | Transaction & History
- HashMap (Client-reservation mapping) | Reservation System
- Stack (Undo functionality) | Reservation System
- Queue (Waitlist management) | Station Management
***

# 1. Vehicles

## Core Requirements:
	•	3 Types:
	    •	Bicycle
	    •	Scooter
	    •	Skateboard
    
    •	Each vehicle must include:
	    •	Unique ID
	    •	Type (enum recommended)
	    •	Brief description
	    •	Available usage schedule
	    •	Location (station name)
	    •	(Optional: Owner info)

## Usage Costs:

Vehicle Type	First Hour/Fraction	Additional Hours/Fraction
Bike	3 credits	2 credits
Scooter	2 credits	1 credit
Skateboard	1 credit	0.5 credits

### Constraints:
	•	A user can only register up to 2 vehicles for rent.
	•	A user can only reserve 1 vehicle at a time.
	•	Maximum reservation length: 6 hours.

***

# 2. Users

### Core Fields:
	•	Name
	•	Student Number
	•	UPR Email
	•	Phone Number
	•	User types:
	•	Client
	•	Owner
	•	Both

### System Should Track:
	•	Registered people
	•	Their role(s)
	•	Balance of expenses or income
	•	Vehicles they own

***

# 3. Stations

## Locations & Capacities:

### Station	Capacity

Stefani Building: 40
Student Center: 90
Biology Building: 35
Chemical Engineering Building: 45
Business Admin Building: 45

### Responsibilities:
	•	Manage vehicle inventory
	•	Track vehicle location
	•	Manage reservations at each station
	•	Track waitlist for full periods

***

# 4. Reservations

### Core Features:
	•	Operator can:
	•	Add, modify, cancel reservations
	•	View availability
	•	Show available vehicles and their cost before booking
	•	Offer waitlist if none available
	•	Confirm availability before reserving
	•	Calculate and display total cost
	•	Assign vehicle upon confirmation

### Constraints:
	•	Only between 7:00 AM – 6:00 PM, Mon–Fri
	•	Duration: up to 6 hours
	•	One reservation per user at a time

### Upon Cancellation:
	•	Next user on waitlist gets notified and assigned the vehicle

***

# 5. Operator Functionality
	•	Add/modify/delete:
	•	Users
	•	Vehicles
	•	Reservations
	•	View:
	•	All users and their balances
	•	All vehicle inventory + ownership
	•	Reservation transactions (logs)
	•	Vehicles at each station

***

# 6. Required Data Structures

You must use the following:

1. Structure-Purpose
2. Set	Store available vehicles
3. LinkedList	Store reservation history / transaction log
4. HashMap	Map clients to their reservations
5. Stack	Implement undo for reservation/cancellation
6. Queue	Manage waitlists when vehicles are unavailable



***

# 7. Design Notes
	•	Document each class:
	•	What data structures are used
	•	Why they were chosen
	•	Document each method:
	•	What it does
	•	Parameters and return value
	•	Who calls it

***


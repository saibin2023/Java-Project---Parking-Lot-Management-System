# Java-Project---Parking-Lot-Management-System
Java Project – How to Build a Simple Parking Lot Management System from Kunal Nalawade

https://www.freecodecamp.org/news/java-project-parking-lot-management-system/

# Java Classes

# Vehicle class:

type, registration, color (All are of string type)

# Slot class:

type (string)

vehicle (Vehicle): type of vehicle parked in the slot

ticketId (string): ticket id assigned to the vehicle parked in this slot, initially null.

# ParkingLot class:

Fields:

parkingLotId (string)

slots (List<List>): This is a list of all the slots in the parking lot. The list of lists represents slots on multiple floors. The floors and slots are numbered according to the list index.

Constructor: ParkingLot(parkingLotId, nfloors, noOfSlotsPerFlr)

Methods:

parkVehicle(type, regNo, color): takes all the parameters of a vehicle, assigns a slot, and returns the ticket

unPark(ticketId): takes the ticket id and removes the vehicle from the slot

getNoOfOpenSlots(type): returns the number of slots for vehicle type

displayOpenSlots(type): displays all open slots for vehicle type

displayOccupiedSlots(type): displays all occupied slots for vehicle type

# Test the App
ParkingLotDemo

# Application Workflow

When a vehicle pulls into the parking lot, the system takes in the vehicle details and looks for an available slot. If it finds an open slot, it assigns that slot to the vehicle and returns a ticket.

This is handled by the parkVehicle() method. If a slot is not available, then the method prints an error message.

Now, if the vehicle wants to unpark, it has to show the ticket. The system parses the ticket, finds out which slot the vehicle is parked in and frees up the slot. The unPark() method takes in the ticket and frees up the corresponding slot.


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


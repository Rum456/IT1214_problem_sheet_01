import java.util.*;

// 1. Define a Vehicle class
class Vehicle {
    private String licensePlate;
    private String ownerName;
    private int hoursParked;

   
    public Vehicle(String licensePlate, String ownerName, int hoursParked) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.hoursParked = hoursParked;
    }

    
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getHoursParked() {
        return hoursParked;
    }

   
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setHoursParked(int hoursParked) {
        this.hoursParked = hoursParked;
    }

    
    public String toString() {
        return "License Plate: " + licensePlate + ", Owner: " + ownerName + ", Hours Parked: " + hoursParked;
    }
}

// 2. Define a ParkingLot class
class ParkingLot {
    private Vehicle[] parkedVehicles;
    private int currentVehicles;
    private final int MAX_CAPACITY = 5;

    public ParkingLot() {
        parkedVehicles = new Vehicle[MAX_CAPACITY];
        currentVehicles = 0;
    }

    // 3. Implement methods in ParkingLot

    // o Park a new vehicle
    public boolean parkVehicle(Vehicle vehicle) {
        if (currentVehicles < MAX_CAPACITY) {
            parkedVehicles[currentVehicles] = vehicle;
            currentVehicles++;
            System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " parked successfully.");
            return true;
        } else {
            System.out.println("Parking lot is full. Cannot park vehicle with license plate " + vehicle.getLicensePlate());
            return false;
        }
    }

    // o Remove a vehicle by license plate
    public boolean removeVehicle(String licensePlate) {
        for (int i = 0; i < currentVehicles; i++) {
            if (parkedVehicles[i].getLicensePlate().equals(licensePlate)) {
                System.out.println("Vehicle with license plate " + licensePlate + " found and being removed.");
                // Shift remaining vehicles to avoid gaps
                for (int j = i; j < currentVehicles - 1; j++) {
                    parkedVehicles[j] = parkedVehicles[j + 1];
                }
                parkedVehicles[currentVehicles - 1] = null; // Clear the last slot
                currentVehicles--;
                System.out.println("Vehicle with license plate " + licensePlate + " removed successfully.");
                return true;
            }
        }
        System.out.println("Vehicle with license plate " + licensePlate + " not found in the parking lot.");
        return false;
    }

    // o Display details of all parked vehicles
    public void displayParkedVehicles() {
        if (currentVehicles == 0) {
            System.out.println("\nParking lot is empty.");
            return;
        }
        System.out.println("\n--- Parked Vehicles ---");
        for (int i = 0; i < currentVehicles; i++) {
            System.out.println((i + 1) + ". " + parkedVehicles[i]);
        }
        System.out.println("-----------------------");
    }
}

 class ParkingTracker {
    public static void main(String[] args) {
    
        ParkingLot myParkingLot = new ParkingLot();

       
        myParkingLot.parkVehicle(new Vehicle("ABC123", "John Doe", 2));
        myParkingLot.parkVehicle(new Vehicle("XYZ789", "Jane Smith", 4));
        myParkingLot.parkVehicle(new Vehicle("LMN456", "Bob Brown", 1));

        // o Remove the vehicle with license "XYZ789".
        myParkingLot.removeVehicle("XYZ789");

        // o Display all parked vehicles.
        myParkingLot.displayParkedVehicles();
    }
}
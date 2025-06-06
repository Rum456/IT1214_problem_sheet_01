import java.util.*;

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

class ParkingLot {
    private Vehicle[] parkedVehicles;
    private int currentVehicles;
    private final int MAX_CAPACITY = 5;

    public ParkingLot() {
        parkedVehicles = new Vehicle[MAX_CAPACITY];
        currentVehicles = 0;
    }

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

    public boolean removeVehicle(String licensePlate) {
        for (int i = 0; i < currentVehicles; i++) {
            if (parkedVehicles[i].getLicensePlate().equals(licensePlate)) {
                System.out.println("Vehicle with license plate " + licensePlate + " found and being removed.");
                for (int j = i; j < currentVehicles - 1; j++) {
                    parkedVehicles[j] = parkedVehicles[j + 1];
                }
                parkedVehicles[currentVehicles - 1] = null; 
                currentVehicles--;
                System.out.println("Vehicle with license plate " + licensePlate + " removed successfully.");
                return true;
            }
        }
        System.out.println("Vehicle with license plate " + licensePlate + " not found in the parking lot.");
        return false;
    }

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

        myParkingLot.removeVehicle("XYZ789");
        myParkingLot.displayParkedVehicles();
    }
}

import java.util.*; 
// Design appropriate classes to represent tickets
class Ticket {
    private int ticketNumber;
    private String customerName;
    private int seatNumber;

    // Constructor
    public Ticket(int ticketNumber, String customerName, int seatNumber) {
        this.ticketNumber = ticketNumber;
        this.customerName = customerName;
        this.seatNumber = seatNumber;
    }

    // Getter methods
    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    // Setter methods (optional, depending on mutability requirements)
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Ticket No: " + ticketNumber + ", Customer: " + customerName + ", Seat: " + seatNumber;
    }
}

// Design appropriate classes to represent the booking system
class BookingSystem {
    private Ticket[] bookedTickets;
    private boolean[] seatAvailability; // true if seat is available, false if booked
    private int currentBookings;
    private final int MAX_SEATS = 10;

    public BookingSystem() {
        bookedTickets = new Ticket[MAX_SEATS];
        seatAvailability = new boolean[MAX_SEATS + 1]; // Seat numbers from 1 to MAX_SEATS
        Arrays.fill(seatAvailability, true); // Initialize all seats as available
        currentBookings = 0;
    }

    // o Book a ticket for a customer, assigning a seat number.
    // If all seats are booked, the booking should fail gracefully.
    public boolean bookTicket(int ticketNumber, String customerName, int seatNumber) {
        // Input validation for seat number
        if (seatNumber < 1 || seatNumber > MAX_SEATS) {
            System.out.println("Booking failed: Invalid seat number. Please choose a seat between 1 and " + MAX_SEATS + ".");
            return false;
        }

        // Check if all seats are booked
        if (currentBookings >= MAX_SEATS) {
            System.out.println("Booking failed: All seats are currently booked.");
            return false;
        }

        // Check for seat availability
        if (!seatAvailability[seatNumber]) {
            System.out.println("Booking failed: Seat " + seatNumber + " is already taken.");
            return false;
        }

        // Check for duplicate ticket number (basic validation)
        if (findTicket(ticketNumber) != null) {
            System.out.println("Booking failed: Ticket number " + ticketNumber + " already exists.");
            return false;
        }

        // Find an empty slot in the bookedTickets array
        int emptySlot = -1;
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] == null) {
                emptySlot = i;
                break;
            }
        }

        if (emptySlot != -1) {
            Ticket newTicket = new Ticket(ticketNumber, customerName, seatNumber);
            bookedTickets[emptySlot] = newTicket;
            seatAvailability[seatNumber] = false; // Mark seat as unavailable
            currentBookings++;
            System.out.println("Ticket " + ticketNumber + " booked successfully for " + customerName + " at Seat " + seatNumber + ".");
            return true;
        } else {
            // This case should ideally not be reached if currentBookings < MAX_SEATS is checked,
            // but acts as a safeguard.
            System.out.println("Booking failed: An unexpected error occurred finding an empty slot.");
            return false;
        }
    }

    // Helper method to find a ticket by ticket number
    private Ticket findTicket(int ticketNumber) {
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null && bookedTickets[i].getTicketNumber() == ticketNumber) {
                return bookedTickets[i];
            }
        }
        return null; // Ticket not found
    }

    // o Cancel a ticket by ticket number, freeing the seat.
    public boolean cancelTicket(int ticketNumber) {
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null && bookedTickets[i].getTicketNumber() == ticketNumber) {
                int freedSeatNumber = bookedTickets[i].getSeatNumber();
                bookedTickets[i] = null; // Free the slot in the array
                seatAvailability[freedSeatNumber] = true; // Mark seat as available
                currentBookings--;
                System.out.println("Ticket " + ticketNumber + " for Seat " + freedSeatNumber + " cancelled successfully.");
                return true;
            }
        }
        System.out.println("Cancellation failed: Ticket number " + ticketNumber + " not found.");
        return false;
    }

    // o Display all booked tickets with their details.
    public void displayBookedTickets() {
        if (currentBookings == 0) {
            System.out.println("\nNo tickets are currently booked.");
            return;
        }
        System.out.println("\n--- Current Bookings ---");
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null) {
                System.out.println(bookedTickets[i]);
            }
        }
        System.out.println("------------------------");
    }

    // Optional: display seat availability
    public void displaySeatAvailability() {
        System.out.println("\n--- Seat Availability ---");
        for (int i = 1; i <= MAX_SEATS; i++) {
            System.out.println("Seat " + i + ": " + (seatAvailability[i] ? "Available" : "Booked"));
        }
        System.out.println("-------------------------");
    }
}

class MovieTicketBooking {
    public static void main(String[] args) {
        // In your main method:
        // Create a BookingSystem instance.
        BookingSystem cinemaBooking = new BookingSystem();

        // Book tickets for three customers with ticket numbers 1, 2, and 3,
        // assigned to seats 1, 2, and 3 respectively.
        System.out.println("--- Initial Bookings ---");
        cinemaBooking.bookTicket(1, "Alice Johnson", 1);
        cinemaBooking.bookTicket(2, "Bob Williams", 2);
        cinemaBooking.bookTicket(3, "Charlie Davis", 3);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        // Cancel the ticket with ticket number 2.
        System.out.println("\n--- Cancelling Ticket 2 ---");
        cinemaBooking.cancelTicket(2);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        // Attempt to book a new ticket for a customer at seat 2 (which is now free).
        System.out.println("\n--- Booking new ticket for Seat 2 ---");
        cinemaBooking.bookTicket(4, "Diana Prince", 2);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        // Attempt to book a ticket for an already booked seat
        System.out.println("\n--- Attempting to book already taken seat 1 ---");
        cinemaBooking.bookTicket(5, "Eve Green", 1);

        // Attempt to book when all seats are full (fill up remaining seats first)
        System.out.println("\n--- Filling up remaining seats ---");
        cinemaBooking.bookTicket(5, "Frank White", 4);
        cinemaBooking.bookTicket(6, "Grace Hall", 5);
        cinemaBooking.bookTicket(7, "Henry King", 6);
        cinemaBooking.bookTicket(8, "Ivy Lee", 7);
        cinemaBooking.bookTicket(9, "Jack Miller", 8);
        cinemaBooking.bookTicket(10, "Karen Nelson", 9);
        cinemaBooking.bookTicket(11, "Liam O'Connell", 10);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        System.out.println("\n--- Attempting to book when full ---");
        cinemaBooking.bookTicket(12, "Mia Patter", 1); // This should fail as full
    }
}
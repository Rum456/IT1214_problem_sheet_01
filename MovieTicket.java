import java.util.*; 

class Ticket {
    private int ticketNumber;
    private String customerName;
    private int seatNumber;

   
    public Ticket(int ticketNumber, String customerName, int seatNumber) {
        this.ticketNumber = ticketNumber;
        this.customerName = customerName;
        this.seatNumber = seatNumber;
    }

     public int getTicketNumber() {
        return ticketNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String toString() {
        return "Ticket No: " + ticketNumber + ", Customer: " + customerName + ", Seat: " + seatNumber;
    }
}

class BookingSystem {
    private Ticket[] bookedTickets;
    private boolean[] seatAvailability;
    private int currentBookings;
    private final int MAX_SEATS = 10;

    public BookingSystem() {
        bookedTickets = new Ticket[MAX_SEATS];
        seatAvailability = new boolean[MAX_SEATS + 1]; 
        Arrays.fill(seatAvailability, true); 
        currentBookings = 0;
    }


    public boolean bookTicket(int ticketNumber, String customerName, int seatNumber) {
        if (seatNumber < 1 || seatNumber > MAX_SEATS) {
            System.out.println("Booking failed: Invalid seat number. Please choose a seat between 1 and " + MAX_SEATS + ".");
            return false;
        }

    
        if (currentBookings >= MAX_SEATS) {
            System.out.println("Booking failed: All seats are currently booked.");
            return false;
        }

   
        if (!seatAvailability[seatNumber]) {
            System.out.println("Booking failed: Seat " + seatNumber + " is already taken.");
            return false;
        }

       
        if (findTicket(ticketNumber) != null) {
            System.out.println("Booking failed: Ticket number " + ticketNumber + " already exists.");
            return false;
        }

      
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
            seatAvailability[seatNumber] = false; 
            currentBookings++;
            System.out.println("Ticket " + ticketNumber + " booked successfully for " + customerName + " at Seat " + seatNumber + ".");
            return true;
        } else {
            System.out.println("Booking failed: An unexpected error occurred finding an empty slot.");
            return false;
        }
    }

    private Ticket findTicket(int ticketNumber) {
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null && bookedTickets[i].getTicketNumber() == ticketNumber) {
                return bookedTickets[i];
            }
        }
        return null; 
    }

    public boolean cancelTicket(int ticketNumber) {
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null && bookedTickets[i].getTicketNumber() == ticketNumber) {
                int freedSeatNumber = bookedTickets[i].getSeatNumber();
                bookedTickets[i] = null; 
                seatAvailability[freedSeatNumber] = true; 
                currentBookings--;
                System.out.println("Ticket " + ticketNumber + " for Seat " + freedSeatNumber + " cancelled successfully.");
                return true;
            }
        }
        System.out.println("Cancellation failed: Ticket number " + ticketNumber + " not found.");
        return false;
    }

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
        BookingSystem cinemaBooking = new BookingSystem();

        System.out.println("--- Initial Bookings ---");
        cinemaBooking.bookTicket(1, "Alice Johnson", 1);
        cinemaBooking.bookTicket(2, "Bob Williams", 2);
        cinemaBooking.bookTicket(3, "Charlie Davis", 3);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        System.out.println("\n--- Cancelling Ticket 2 ---");
        cinemaBooking.cancelTicket(2);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        System.out.println("\n--- Booking new ticket for Seat 2 ---");
        cinemaBooking.bookTicket(4, "Diana Prince", 2);
        cinemaBooking.displayBookedTickets();
        cinemaBooking.displaySeatAvailability();

        System.out.println("\n--- Attempting to book already taken seat 1 ---");
        cinemaBooking.bookTicket(5, "Eve Green", 1);

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
        cinemaBooking.bookTicket(12, "Mia Patter", 1); 
    }
}

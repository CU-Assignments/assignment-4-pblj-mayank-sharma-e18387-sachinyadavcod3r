import java.util.HashSet;
import java.util.Set;
class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private static final Set<Integer> bookedSeats = new HashSet<>();
    private static final Object lock = new Object();
    static class BookingThread extends Thread {
        private String name;
        private int seatNumber;
        private boolean isVIP;
        public BookingThread(String name, int seatNumber, boolean isVIP) {
            this.name = name;
            this.seatNumber = seatNumber;
            this.isVIP = isVIP;
        }
        public void run() {
            synchronized (lock) {
                if (bookedSeats.contains(seatNumber)) {
                    System.out.println(name + ": Seat " + seatNumber + " is already booked.");
                } else if (seatNumber <= 0 || seatNumber > TOTAL_SEATS) {
                    System.out.println(name + ": Invalid seat number.");
                } else {
                    bookedSeats.add(seatNumber);
                    System.out.println(name + ": Seat " + seatNumber + " booked successfully.");
                }
            }
        }
    }
    public static void main(String[] args) {
        BookingThread vip1 = new BookingThread("VIP 1", 5, true);
        BookingThread regular1 = new BookingThread("Regular 1", 3, false);
        BookingThread vip2 = new BookingThread("VIP 2", 5, true);
        BookingThread regular2 = new BookingThread("Regular 2", 7, false);
        BookingThread regular3 = new BookingThread("Regular 3", 1, false);
        BookingThread invalidSeat = new BookingThread("Invalid Seat", 11, false);
        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY);
        vip1.start();
        regular1.start();
        vip2.start();
        regular2.start();
        regular3.start();
        invalidSeat.start();
        try {
            vip1.join();
            regular1.join();
            vip2.join();
            regular2.join();
            regular3.join();
            invalidSeat.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nBooked Seats: " + bookedSeats);
    }
}

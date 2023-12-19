

public class Cinema {
    private static final int NUM_HALLS = 5;
    private static final int NUM_ROWS = 10;
    private static final int NUM_SEATS = 20;

    private int[][][] seatingArrangement; // 3D array to represent the seating arrangement

    public Cinema() {
        seatingArrangement = new int[NUM_HALLS][NUM_ROWS][NUM_SEATS];
        initializeSeating();
    }

    private void initializeSeating() {
        for (int hall = 0; hall < NUM_HALLS; hall++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                for (int seat = 0; seat < NUM_SEATS; seat++) {
                    seatingArrangement[hall][row][seat] = 0; // Initialize all seats as available (0)
                }
            }
        }
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (seatingArrangement[hallNumber - 1][row - 1][seat - 1] == 1) {
                System.out.println("Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " is already booked.");
            } else {
                seatingArrangement[hallNumber - 1][row - 1][seat - 1] = 1; // Book the seat
                System.out.println("Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " booked successfully.");
            }
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (seatingArrangement[hallNumber - 1][row - 1][seat - 1] == 0) {
                System.out.println("Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " is not booked.");
            } else {
                seatingArrangement[hallNumber - 1][row - 1][seat - 1] = 0; // Cancel the booking
                System.out.println("Booking for Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " cancelled.");
            }
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < NUM_ROWS; row++) {
            int consecutiveSeats = 0;
            for (int seat = 0; seat < NUM_SEATS; seat++) {
                if (seatingArrangement[hallNumber - 1][row][seat] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        return true; // Required number of consecutive seats found
                    }
                } else {
                    consecutiveSeats = 0; // Reset consecutive seats count
                }
            }
        }
        return false; // No consecutive seats found
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Seating Arrangement for Hall " + hallNumber + ":");
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int seat = 0; seat < NUM_SEATS; seat++) {
                System.out.print(seatingArrangement[hallNumber - 1][row][seat] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        // Example usage
        int hallNumber = 1;
        int row = 3;
        int[] seatsToBook = {5, 6, 7};

        cinema.bookSeats(hallNumber, row, seatsToBook);
        cinema.printSeatingArrangement(hallNumber);

        cinema.cancelBooking(hallNumber, row, seatsToBook);
        cinema.printSeatingArrangement(hallNumber);

        int numSeatsToCheck = 3;
        if (cinema.checkAvailability(hallNumber, numSeatsToCheck)) {
            System.out.println("There are " + numSeatsToCheck + " consecutive seats available in Hall " + hallNumber + ".");
        } else {
            System.out.println("No consecutive seats available in Hall " + hallNumber + ".");
        }
    }
}

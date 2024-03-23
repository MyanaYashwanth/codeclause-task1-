package moviebooking;
import java.util.*;

//Movie class to represent each movie
class Movie {
 private String title;
 private String time;
 private int totalSeats;
 private boolean[][] seatAvailability; // Matrix to represent seat availability

 public Movie(String title, String time, int totalSeats) {
     this.title = title;
     this.time = time;
     this.totalSeats = totalSeats;
     this.seatAvailability = new boolean[10][totalSeats / 10]; // Assuming 10 rows and totalSeats / 10 columns
     initializeSeats();
 }

 private void initializeSeats() {
     for (int i = 0; i < 10; i++) {
         for (int j = 0; j < totalSeats / 10; j++) {
             seatAvailability[i][j] = true; // Mark all seats as available initially
         }
     }
 }

 public String getTitle() {
     return title;
 }

 public String getTime() {
     return time;
 }

 public int getTotalSeats() {
     return totalSeats;
 }

 public boolean isSeatAvailable(int row, int column) {
     return seatAvailability[row][column];
 }

 public void bookSeat(int row, int column) {
     if (isSeatAvailable(row, column)) {
         seatAvailability[row][column] = false; // Mark seat as booked
         System.out.println("Seat [" + (row + 1) + "," + (column + 1) + "] booked successfully!");
     } else {
         System.out.println("Sorry, the selected seat [" + (row + 1) + "," + (column + 1) + "] is already booked.");
     }
 }
}

//Theater class to manage movies and bookings
class Theater {
 private List<Movie> movies;

 public Theater() {
     movies = new ArrayList<>();
 }

 public void addMovie(Movie movie) {
     movies.add(movie);
 }

 public void displayMovies() {
     System.out.println("Available Movies:");
     for (int i = 0; i < movies.size(); i++) {
         Movie movie = movies.get(i);
         System.out.println((i + 1) + ". " + movie.getTitle() + " at " + movie.getTime());
     }
 }

 public void displayMovieDetails(int index) {
     Movie movie = movies.get(index);
     System.out.println("Movie: " + movie.getTitle());
     System.out.println("Time: " + movie.getTime());
     System.out.println("Available Seats: " + movie.getTotalSeats());
 }

 public void displayAvailableSeats(int index) {
     Movie movie = movies.get(index);
     System.out.println("Available seats for " + movie.getTitle() + ":");
     for (int i = 0; i < 10; i++) {
         for (int j = 0; j < movie.getTotalSeats() / 10; j++) {
             if (movie.isSeatAvailable(i, j)) {
                 System.out.print("[" + (i + 1) + "," + (j + 1) + "] ");
             } else {
                 System.out.print("[X] "); // X represents booked seat
             }
         }
         System.out.println();
     }
 }

 public boolean bookTicket(int movieIndex, int[] rows, int[] columns) {
     if (movieIndex >= 0 && movieIndex < movies.size()) {
         Movie selectedMovie = movies.get(movieIndex);
         boolean allSeatsBooked = true;
         for (int i = 0; i < rows.length; i++) {
             int row = rows[i] - 1;
             int column = columns[i] - 1;
             if (row >= 0 && row < 10 && column >= 0 && column < selectedMovie.getTotalSeats() / 10) {
                 if (selectedMovie.isSeatAvailable(row, column)) {
                     selectedMovie.bookSeat(row, column);
                 } else {
                     allSeatsBooked = false;
                     System.out.println("Seat [" + (row + 1) + "," + (column + 1) + "] is already booked.");
                 }
             } else {
                 System.out.println("Invalid seat selection.");
                 allSeatsBooked = false;
             }
         }
         return allSeatsBooked;
     } else {
         System.out.println("Invalid movie selection.");
         return false;
     }
 }
}
 public class Bookingsystem {
	public static void main(String[] args) {
	     Scanner scanner = new Scanner(System.in);
	     Theater theater = new Theater();

	     // Add sample movies
	     theater.addMovie(new Movie("razakar", "10:00 AM", 100));
	     theater.addMovie(new Movie("yodha", "1:00 PM", 80));
	     theater.addMovie(new Movie("godzilla x king", "4:00 PM", 120));

	     // Display available movies
	     theater.displayMovies();

	     // User selects a movie
	     System.out.println("Enter the number of the movie you want to watch:");
	     int selectedMovieIndex = scanner.nextInt() - 1;
	     scanner.nextLine(); // Consume newline
	     theater.displayMovieDetails(selectedMovieIndex);

	     // Display available seats
	     theater.displayAvailableSeats(selectedMovieIndex);

	     // Book tickets
	     System.out.println("Do you want to book a ticket? (Y/N)");
	     String choice = scanner.nextLine().toUpperCase();
	     if (choice.equals("Y")) {
	         System.out.println("Enter the number of seats you want to book:");
	         int numOfSeats = scanner.nextInt();
	         int[] selectedRows = new int[numOfSeats];
	         int[] selectedColumns = new int[numOfSeats];
	         System.out.println("Enter the row and column number of each seat you want to book (e.g., 2 5):");
	         for (int i = 0; i < numOfSeats; i++) {
	             selectedRows[i] = scanner.nextInt();
	             selectedColumns[i] = scanner.nextInt();
	         }
	         if (theater.bookTicket(selectedMovieIndex, selectedRows, selectedColumns)) {
	             System.out.println("Tickets booked successfully!");
	         } else {
	             System.out.println("Ticket booking failed.");
	         }
	     } else {
	         System.out.println("Thank you for visiting!");
	     }

	     scanner.close();
	 }
	

}



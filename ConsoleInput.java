//
//import edu.neu.ccs.console.ConsoleAware;
//
///**
// * @author Alfred Ababio
// * @version 1
// * Class for user input to console
// */
//public class ConsoleInput implements ConsoleAware {
//    
//    /**
//     * Main method so one can run the ConsoleInput
//     * @param args cmd read in as a list of inputs
//     */
//    public static void main(String[] args) {
//        TicketSales ts = new TicketSales();
//        ts.initCinema("cinema.txt");
//        ts.processOrders("orders.txt");
//        demandPurpose(ts);
//    }
//    
//    /**
//     * Will prompt you with 4 choices
//     * @param t TicketSales to get logs and stuff from
//     */
//    private static void demandPurpose(TicketSales t) {
//        console.out.println("Main Menu");
//        console.out.println("[1] Buy A Ticket");
//        console.out.println("[2] Request a Sales Report");
//        console.out.println("[3] Request a Manager Report");
//        console.out.println("[4] Initialize Data");
//        console.out.println("[0] Exit");
//        int a = console.in.demandInt("Please select an option");
//        if (a == 1) {
//            ConsoleInput.demandPurchase(t);
//            ConsoleInput.demandPurpose(t);
//        }
//        else if (a == 2) {
//            console.out.println(t.reportSales());
//            ConsoleInput.demandPurpose(t);
//        }
//        else if (a == 3) {
//            console.out.println(t.managerReport());
//            t.log += t.consoleManageReport();
//            ConsoleInput.demandPurpose(t);
//        }
//        else if (a == 4) {
//            int b = ConsoleInput.chooseData();
//            if (b == 1) {
//                Movie mv = ConsoleInput.demandMovie();
//                t.movies.add(mv);
//            }
//            else if (b == 2) {
//                Theater th = ConsoleInput.demandTheater();
//                t.theaters.add(th);
//            }
//            else if (b == 3) {
//                ConsoleInput.demandShowing(t);
//            }
//            else if (b == 4) {
//                Ticket tix = ConsoleInput.demandTicket();
//                t.priceGuide.add(tix);
//
//            }
//            else {
//                console.out.println("Invalid choice, try again");
//                ConsoleInput.chooseData();
//            }
//            ConsoleInput.demandPurpose(t);
//        }
//        else if (a == 0) {
//            System.exit(a);
//        }
//        else {
//            console.out.println("Invalid choice, try again.");
//            ConsoleInput.demandPurpose(t);
//        }
//    }
//
//    /**
//     * If you choose to buy a ticket, you get all of this.
//     * Will prompt for showing, tickets and then add to the 
//     * sales log and the showings internal log
//     * @param t TicketSales that holds the cinema data
//     */
//    private static void demandPurchase(TicketSales t) {
//        console.out.println("Showings");
//        for (int d = 0; d < t.shows.size(); d++) {
//            console.out.println("[" + (d + 1) + "] " + 
//                    t.shows.get(d).toString());
//        }
//        if (!t.shows.isEmpty()) {
//            int show = getRealShowing(t);
//            int totalPrice = 0;
//            int totalSeats = 0;
//            String ticketOrder = "";
//            for (int e = 0; e < t.priceGuide.size(); e++) {
//                int ticketTotal = 0;  
//                ticketTotal += console.in.demandInt("How many " +
//                    t.priceGuide.get(e).name +
//                    " tickets? (0 for no tickets)");
//                ticketOrder += ticketTotal + ",";
//                totalPrice += ticketTotal * t.priceGuide.get(e).price;
//                totalSeats += ticketTotal;
//            }
//            Showing q = t.shows.get(show);
//            if (q.theater.seatsLeft >= totalSeats) {
//                t.shows.get(show).theater.seatsLeft -= totalSeats;
//                console.out.println("Your total is: " + totalPrice);
//                t.log += (t.movies.indexOf(q.movie) + 1) + "," 
//                        + (t.theaters.indexOf(q.theater) + 1) + ","
//                        + q.start + "," + ticketOrder + totalPrice + "\n";
//                t.shows.get(show).orders.add(ticketOrder);
//            }
//            else {
//                console.out.println("Sorry, you wanted " +
//                        (totalSeats - t.shows.get(show).theater.seatsLeft) +
//                        " more seats than we have available.");
//                t.log += (t.movies.indexOf(q.movie) + 1) + "," 
//                        + (t.theaters.indexOf(q.theater) + 1) + ","
//                        + q.start + "," + ticketOrder + "0\n";
//            }
//        }
//        else {
//            console.out.println("There are no Shows");
//        }
//    }
//    
//
//    /**
//     * The Main Menu prompts
//     * Will prompt you with the 4 options
//     * @return int choice at main menu
//     */
//    private static int chooseData() {
//        console.out.println("What kind of data are you initializing?");
//        console.out.println("[1] Movie");
//        console.out.println("[2] Theater");
//        console.out.println("[3] Showing");
//        console.out.println("[4] Ticket");
//        return console.in.demandInt("Your choice: ");
//    }
//    
//    /**
//     * This will demand a theater and make a ticket out of it
//     * while asserting that the price is positive
//     * @return Theater that is correctly made
//     */
//    private static Theater demandTheater() {
//        return new Theater(console.in.demandString("Theater name: "),
//                ConsoleInput.demandTheaterCapacity());
//    }
//    
//    /**
//     * Asserts that the theater capacity is positive
//     * @return int positive int for theater capacity
//     */
//    private static Integer demandTheaterCapacity() {
//        Integer cap = console.in.demandInt("Theater capacity: ");
//        if (cap <= 0) {
//            cap = ConsoleInput.demandTheaterCapacity();
//        }
//        return cap;
//    }
//
//    /**
//     * This will demand a movie and make a ticket out of it
//     * while asserting that the price is positive
//     * @return Movie that is correctly made
//     */
//    private static Movie demandMovie() {
//        return new Movie(console.in.demandString("Movie name: "),
//               ConsoleInput.demandMovieLength());        
//    }
//    
//    /**
//     * Asserts that the movie length is positive
//     * @return int positive int for movie length
//     */
//    private static Integer demandMovieLength() {
//        Integer len = console.in.demandInt("Movie length: ");
//        if (len <= 0) {
//            len = ConsoleInput.demandMovieLength();
//        }
//        return len;
//    }
//
//    /**
//     * This will demand a ticket and make a ticket out of it
//     * while asserting that the price is positive
//     * @return Ticket that is correctly made
//     */
//    private static Ticket demandTicket() {
//        return new Ticket(console.in.demandString("Ticket name: "),
//                ConsoleInput.demandTicketPrice());
//    }
//    
//    /**
//     * Asserts that the ticket price is positive
//     * @return int positive int for ticket price
//     */
//    private static Integer demandTicketPrice() {
//        Integer pri = console.in.demandInt("Ticket price: ");
//        if (pri < 0) {
//            pri = ConsoleInput.demandTicketPrice();
//        }
//        return pri;
//    }
//
//    /**
//     * Will make a Showing out of the data in t
//     * @param t TicketSales that holds all of the cinema data in it
//     */
//    private static void demandShowing(TicketSales t) {
//        if ((!t.movies.isEmpty()) || (!t.theaters.isEmpty())) {
//            console.out.println("\nMovies");
//            for (int a = 0; a < t.movies.size(); a++) {
//                console.out.println("[" + (a + 1) + "] " + 
//                     t.movies.get(a).toString());   
//            }
//            
//            int movie = ConsoleInput.getRealMovie(t);
//            
//            console.out.println("\nTheaters");
//            for (int b = 0; b < t.theaters.size(); b++) {
//                console.out.println("[" + (b + 1) + "] " + 
//                        t.theaters.get(b).toString());
//            }
//            
//            int theater = ConsoleInput.getRealTheater(t);
//            
//            console.out.println("Set a start time: ");
//            int h = ConsoleInput.demandStartHour();
//            int m = ConsoleInput.demandStartMinute();
//            int start = (h * 60) + m;
//            Showing s = new Showing(t.movies.get(movie),
//                    new Theater(t.theaters.get(theater)), start);
//            if (!s.conflicts(t.shows)) {
//                t.shows.add(s);
//            }
//            else {
//                console.out.println("Showing already exists!");
//            }
//        }
//        else {
//            console.out.println("No Movies... Exiting");
//            System.exit(0);
//        }
//    }
//    
//    /**
//     * Will make sure that the number input is valid and in range
//     * of the array of theaters
//     * @param t TicketSales that holds the cinema data
//     * @return int index of the valid theater
//     */
//    private static int getRealTheater(TicketSales t) {
//        int th = console.in.demandInt("Select a theater: ") - 1;
//        if (th < 0 || th > (t.theaters.size() - 1)) {
//            th = ConsoleInput.getRealTheater(t);
//        }
//        return th;
//    }
//
//    /**
//     * Will make sure that the number input is valid and in range
//     * of the array of movies
//     * @param t TicketSales that holds the cinema data
//     * @return int index of the valid movie
//     */
//    private static int getRealMovie(TicketSales t) {
//        int m = console.in.demandInt("Select a movie: ") - 1;
//        if (m < 0 || m > (t.movies.size() - 1)) {
//            m = ConsoleInput.getRealMovie(t);
//        }
//        return m;
//    }
//    
//    /**
//     * Will make sure that the number input is valid and in range
//     * of the array of showings
//     * @param t TicketSales that holds the cinema data
//     * @return int index of the valid showing
//     */
//    private static int getRealShowing(TicketSales t) {
//        int sh = console.in.demandInt("Choose a Showing: ") - 1;
//        if (sh < 0 || sh > (t.shows.size() - 1)) {
//            sh = ConsoleInput.getRealShowing(t);
//        }
//        return sh;
//    }
//
//    /**
//     * Will make sure that the minute demander only will accept a number
//     * in range of [0,59]
//     * @return int in range of [0,59]
//     */
//    private static int demandStartMinute() {
//        int m = console.in.demandInt("Minute (0 - 59): ");
//        if (m < 0 || m > 59) {
//            m = ConsoleInput.demandStartMinute();
//        }
//        return m;
//    }
//
//    /**
//     * Will make sure that the minute demander only will accept a number
//     * in range of [0,23]
//     * @return int in range of [0,23]
//     */
//    private static int demandStartHour() {
//        int h = console.in.demandInt("Hour (0 - 23): ");
//        if (h < 0 || h > 23) {
//            h = ConsoleInput.demandStartHour();
//        }
//        return h;
//    }
//}

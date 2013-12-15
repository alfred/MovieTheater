import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that holds the Theater and Movie objects and a Price Guide for the
 * movies. This class is able to Initialize a cinema, process orders, and report
 * sales.
 * 
 * @author Alfred Ababio
 * @version K12
 */
public class TicketSales {
    /** The list of movies */
    ArrayList<Movie> movies;
    /** The list of theaters */
    ArrayList<Theater> theaters;
    /** The list of actual screenings for the day */
    ArrayList<Showing> shows;
    /** The Map of Prices */
    ArrayList<Ticket> priceGuide;
    /** The Report Number */
    Integer repNum;
    /** Sales Report */
    String log;
    /** Error Log */
    String errLog;

    /**
     * Constructor for a TicketSales class
     */
    public TicketSales() {
        repNum = 1;
        log = "";
        errLog = "";
    }

    /**
     * This method will initialize an entire Movie Complex (the same as opening
     * the Theater for the day) with the data from the fileName in the String
     * 
     * @param fileName The name of the file to initialize the theater from
     */
    public void initCinema(String fileName) {
        /** Will read the file for me */
        ReadFile crf = new ReadFile(fileName);
        /** Reads to this ArrayList */
        ArrayList<String> stuffs = new ArrayList<String>();
        ArrayList<String> moviestr = new ArrayList<String>();
        ArrayList<String> theaterstr = new ArrayList<String>();
        ArrayList<String> showstr = new ArrayList<String>();

        try {
            stuffs = crf.openFile();
            
            moviestr = breakStuff(stuffs, "Theaters");
            this.movies = makeMovies(moviestr);
            stuffs.removeAll(moviestr);

            theaterstr = breakStuff(stuffs, "Shows");
            this.theaters = buildTheaters(theaterstr);
            stuffs.removeAll(theaterstr);

            showstr = breakStuff(stuffs, "Prices");
            this.shows = createShowings(showstr);
            stuffs.removeAll(showstr);

            this.priceGuide = setPrices(stuffs);
            
        } 
        catch (IOException e) {
            this.errLog += e.toString() + "<-- Read error messages." + "\n";
        }
    }
    
    /** 
     * This function will process the orders from a file
     * @param fileName The file to read orders from
     */
    public void processOrders(String fileName) {
        /** Will read the file for me */
        ReadFile crf = new ReadFile(fileName);
        /** Reads to this ArrayList */
        ArrayList<String> stuffs = new ArrayList<String>();
        
        try {
            stuffs = crf.openFile();
            // Now we have an ArrayList of every order.
            for (int i = 0; i < stuffs.size(); i++) {
                if (stuffs.get(i).equals("report")) {
                    this.log += this.reportOnce();
                }
                else {
                    this.salesReportHelper(stuffs.get(i));
                }
            }
        }
        catch (IOException e) {
            this.errLog += e.toString() + "<-- Read error messages." + "\n";
        }
    }
    
    /**
     * This function will essentially only print the sales
     * It does this by looking at the log and filtering out all the reports
     * @return String of only sales made
     */
    public String reportSales() {
        String[] hate = this.log.split("\n");
        String repor = "";
        for (int a = 0; a < hate.length; a++) {
            String[] c = hate[a].split(" ");
            if (c[0].equals("Report")) {
                a += this.shows.size();
            }
            // If the first thing was a number then it is
            // an order that should be added to this list
            String[] q = c[0].split(",");
            if (q[0].matches("\\d")) {
                repor += hate[a] + "\n";
            }
        }  
        return repor;
    }
    
    /**
     * Function to output all of the reports that the manager has
     * requested
     * @return String all of the reports the manager has requested.
     */
    public String managerReport() {
        String[] ood = this.log.split("\n");
        String bah = "";
        for (int a = 0; a < ood.length; a++) {
            String[] gdk = ood[a].split(" ");
            if (gdk[0].equals("Report")) {
                for (int b = 0; b <= this.shows.size(); b++) {
                    bah += ood[a + b] + "\n";
                }
                a += this.shows.size();
            }
        }
        return bah;
    }
    
        
     /**
      * Another manager report helper
      * @return String that is the report
      */
    public String consoleManageReport() {
        String csshhh = "Report " + repNum + "\n";
        for (Showing s : this.shows) {
            csshhh += this.showingReport(s) + "\n";
        }     
        this.repNum++;       
        return csshhh;
    }
    
    /**
     * Prints the log of everything that has happened since initialization
     * @return String the log of this TicketSales
     */
    public String logReport() {
        return this.errLog;
    }
    
    /**
     * A helper method for salesReport to add to the log correctly.
     * @param string The line of the order to process and log
     */
    private void salesReportHelper(String string) {
        String[] w = string.split(",");
        try {
            Showing s = new Showing(
                    this.movies.get(Integer.parseInt(w[0]) - 1),
                    this.theaters.get(Integer.parseInt(w[1]) - 1),
                    Integer.parseInt(w[2]));
            int k = this.shows.indexOf(s);
            
            /** Finds how many tickets total were purchased */
            int sum = 0;
            for (int a = 3; a < w.length; a++) {
                sum += Integer.parseInt(w[a]);
            }
            int g = this.shows.get(k).theater.seatsLeft;
            /** Subtract the seats left from the Theater */
            if (g >= sum) {
                this.shows.get(k).theater.seatsLeft -= sum;
                int total = 0;
                for (int h = 3; h < w.length; h++) {
                    total += Integer.parseInt(w[h]) * 
                            this.priceGuide.get((h - 3)).price;
                }
                
                // Update this log to show the purchase was complete
                this.log += string + "," + total + "\n";
                
                // Add the order to the theater log
                String o = "";
                for (int a = 3; a < w.length; a++) {
                    o += w[a] + ",";
                }
                o = o.substring(0, o.length() - 1);
                
                this.shows.get(k).orders.add(o);
            }
            /** Otherwise it's sold out */
            else {
                this.log += string + "," + "0" + "\n";

            }
        }
        catch (Exception e) {
            this.errLog += "Incorrectly formatted order: " + string + "\n";
        }
    }

    /**
     * This outputs one manager report
     * @return A manager report for the sales of every Show
     */
    private String reportOnce() {
        String s = "";
        s += "\n";
        s += "Report " + this.repNum.toString() + "\n";
        for (Showing show : this.shows) {
            s += this.showingReport(show) + "\n";
        }
        this.repNum++;
        return s;
    }

    /** 
     * Returns a report of a Showing
     * [Movie #,Theater #,Start,Ticket[0] #,...Ticket[n] #]
     * @param s Showing to take report of
     * @return String report of this showing
     */
    public String showingReport(Showing s) {
        String str = "";
        /** First get the Movie */
        str += s.movie.name + ",";
        
        /** Now get the Theater */
        str += s.theater.name + ",";
        
        /** Get the start time */
        String start = s.start.toString();
        str += start + ",";
        
        /** Get the maximum capacity for the Theater */
        str += s.theater.seatsMax + ",";
        
        /** Now lets pull the tickets */
        //Above initializes ArrayList with 0 values for each ticket type
        ArrayList<Integer> ot = new ArrayList<Integer>();
        for (int c = 0; c < this.priceGuide.size(); c++) {
            ot.add(0);
        }

        //Now let's add each ticket amount to the list so we total up the 
        //types of each ticket purchased.
        for (int a = 0; a < s.orders.size(); a++) {
            String[] w = s.orders.get(a).split(",");
            for (int z = 0; z < w.length; z++) {
                ot.set(z, Integer.parseInt(w[z]) + ot.get(z));
            }
        }
        
        //Turn the ArrayList back to a String
        String tix = "";
        for (int b = 0; b < ot.size(); b++) {
            tix += ot.get(b) + ",";
        }
        
        // Add the string of tickets to the output str
        str += tix;
        
        /** Getting the seats left */
        /**str += s.theater.seatsLeft.toString(); */
        
        str = str.substring(0, str.length() - 1);
        
        return str;
    }

    /**
     * This basically does sublist but by elements in the list
     * @param alist The list to be broken
     * @param term Broken up to this term
     * @return ArrayList<String> that is the list we wanted
     */
    public ArrayList<String> breakStuff(ArrayList<String> alist, String term) {
        int i = 0;
        ArrayList<String> strarr = new ArrayList<String>();
        while (!alist.get(i).equals(term)) {
            strarr.add(alist.get(i));
            i++;
        }
        return strarr;
    }

    /**
     * Turns every element except 0-th into a Movie object because 0 is "Movie"
     * The List must be formatted as [Movies, Name1:Time1, Name2:Time2, ...]
     * 
     * @param alist
     *            ArrayList<String> of Movies as Strings
     * @return ArrayList<Movie> with Strings turned into Movies
     */
    public ArrayList<Movie> makeMovies(ArrayList<String> alist) {
        ArrayList<Movie> mlist = new ArrayList<Movie>();
        for (int a = 1; a < alist.size(); a++) {
            try {
                mlist.add(Movie.fromString(alist.get(a)));
            } 
            catch (Exception e) {
                this.errLog += "Incorrectly formatted string: "
                        + alist.get(a) + "\n";
            }
        }
        return mlist;
    }

    /**
     * Builds the theater rooms from the string representation of them The List
     * must be formatted as [Theaters, Name1:Capacity1, Name2:Capacity2, ...]
     * 
     * @param alist
     *            The list of theaters
     * @return ArrayList<Theater> The list of theaters
     */
    public ArrayList<Theater> buildTheaters(ArrayList<String> alist) {
        ArrayList<Theater> tlist = new ArrayList<Theater>();
        for (int a = 1; a < alist.size(); a++) {
            try {
                tlist.add(Theater.fromString(alist.get(a)));
            } 
            catch (Exception e) {
                this.errLog += "Incorrectly formatted string: "
                        + alist.get(a) + "\n";
            }
        }
        return tlist;
    }

    /**
     * Creates a list of Showings from the String list given It does handles
     * conflicting Shows and will not add conflicts
     * 
     * @param alist
     *            The list of existing shows
     * @return ArrayList<Showing> the list of shows already in
     */
    public ArrayList<Showing> createShowings(ArrayList<String> alist) {
        ArrayList<Showing> slist = new ArrayList<Showing>();
        for (int a = 1; a < alist.size(); a++) {
            try {
                String[] w = alist.get(a).split(",");
                Showing s = new Showing(
                        this.movies.get(Integer.parseInt(w[0]) - 1),
                        new Theater(
                                this.theaters.get(Integer.parseInt(w[1]) - 1)),
                        Integer.parseInt(w[2]));
                if (!s.conflicts(slist)) {
                    slist.add(s);
                } 
                else {
                    this.errLog += "Conflicting Showing not added: "
                            + alist.get(a) + "\n";
                }
            } 
            catch (Exception e) {
                this.errLog += "Incorrectly formatted string: "
                        + alist.get(a) + "\n";
            }
        }
        return slist;
    }

    /**
     * This function reads in the prices and creates a nice lookup table of
     * prices to work with
     * 
     * @param slist
     *            The arrayList of strings to work with
     * @return HashMap<String, Integer> That will act as a table
     */
    public ArrayList<Ticket> setPrices(ArrayList<String> slist) {
        ArrayList<Ticket> hm = new ArrayList<Ticket>();
        for (int i = 1; i < slist.size(); i++) {
            try {
                String[] w = slist.get(i).split(":");
                hm.add(new Ticket(w[0], Integer.parseInt(w[1])));
            }
            catch (Exception e) {
                this.errLog += "Incorrectly formatted string: " +
                            slist.get(i) + "\n";
            }
        }
        return hm;
    }
    
}

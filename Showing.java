import java.util.ArrayList;

/**
 * Class to represent a Show
 * 
 * @author Alfred
 * @version 2
 */
public class Showing {
    /** The movie of this show */
    Movie movie;
    /** The theater the movie is playing in */
    Theater theater;
    /** The time the movie starts in minutes from midnight */
    Integer start;
    /** The orders for this Showing so far */
    ArrayList<String> orders;

    /**
     * Constructor for a Show
     * 
     * @param m
     *            The Movie that is playing
     * @param t
     *            The theater that the movie is playing in
     * @param s
     *            The intended movie start time
     */
    public Showing(Movie m, Theater t, Integer s) {
        this.movie = m;
        this.theater = t;
        this.start = s;
        this.orders = new ArrayList<String>();
    }

    /**
     * Will find if there is a showing conflict between this Showing
     * and the already existing list
     * @param slist The existing List of Showings to check against
     * @return boolean whether there is a conflict or not
     */
    public boolean conflicts(ArrayList<Showing> slist) {
        boolean conflict = false;
        for (int a = 0; a < slist.size(); a++) {
            Showing c = slist.get(a);
            conflict = (this.theater.equals(c.theater) &&
                    (c.start <= this.start) &&
                    (this.start <= c.start + c.movie.length));
        }
            
        return conflict;
    }
    

    
    /**
     * A brand new overridden equals method for Shows
     * 
     * @param o
     *            Object to be compared to this Show
     * @return boolean whether Shows are the same.
     */
    public boolean equals(Object o) {
        if (o instanceof Showing) {
            Showing that = (Showing) o;
            return this.hashCode() == that.hashCode();
        } 
        else {
            return false;
        }
    }

    /**
     * My overridden toString method that I need so badly
     * 
     * @return String the string form of this Showing
     */
    public String toString() {
        return "\n" + this.movie.toString() + "\n" + 
                this.theater.toString() + "\n" +
                "Starts at " + this.start.toString();
    }

    /**
     * My overridden hashCode method
     * @return int The hashcode of this Showing
     */
    public int hashCode() {
        return this.movie.hashCode() + 
                this.theater.hashCode() +
                this.start;
    }


}

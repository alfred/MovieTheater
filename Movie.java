
/** Class to represent a movie
 * 
 * @author Alfred
 * @version 1
 */
public class Movie {
    /** The name of the movie */
    String name;
    /** The length in minutes for this movie */
    Integer length;
    
    /**
     * The constructor for a Movie
     * @param name Name of the movie as a String
     * @param length In minutes from midnight
     */
    public Movie(String name, Integer length) {
        this.name = name;
        this.length = length;
    }
    
    /**
     * Turns a string representation of a movie into a Movie
     * @param m String representation of a movie
     * @return Movie form of the string
     * @throws RuntimeException Says the string wasn't formatted
     */
    public static Movie fromString(String m) {
        String[] w = m.split(":");
        Movie wm = new Movie(w[0], Integer.parseInt(w[1]));
        return wm; 
    }
    
    /**
     * Overriding equals method for a Movie
     * Compares the name of the movie and it's showtime
     * @param o to be compared to this Movie
     * @return boolean whether this Movie and that Object are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie that = (Movie)o;
            return this.hashCode() == that.hashCode();
        }
        else {
            return false;
        }
    }
    
    /**
     * Converts this Movie to a String
     * @return String that represents this Movie
     */
    public String toString() {
        return this.name + ":" + length.toString();
    }
    
    /**
     * Overriding the hashCode method for a Movie
     * @return int the hashCode of this Movie
     */
    public int hashCode() {
        return this.name.hashCode() + this.length;
    }
    
}

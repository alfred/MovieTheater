/** Class to represent a movie theater
 * @author Alfred Ababio
 * @version 2
 */
public class Theater {
    /** Name of this theater */
    String name;
    /** How many seats this theater has */
    Integer seatsMax;
    /** How many seats this showing has left */
    Integer seatsLeft;
    
    /** Constructor for a Theater
     * 
     * @param name The name of this theater
     * @param seats The max number of seats theater can fill
     */
    public Theater(String name, Integer seats) {
        this.name = name;
        this.seatsMax = seats;
        this.seatsLeft = seats;
    }
    
    /** Second constructor for a Theater
     * @param t Theater to make this Theater to match
     */
    public Theater(Theater t) {
        this.name = t.name;
        this.seatsMax = t.seatsMax;
        this.seatsLeft = t.seatsMax; 
    }
    
    /**
     * Converts a String to a Theater if it is formatted correctly
     * @param t String that is formatted as a theater
     * @return Theater if the String is formatted correctly
     * @throws RuntimeException Says the string wasn't formatted
     */
    public static Theater fromString(String t) {         
        String[] w = t.split(":");
        Theater wt = new Theater(w[0], Integer.parseInt(w[1]));
        return wt;
    }
    
    /**
     * Overriding the equals method
     * @param o Object to be compared to this Theater
     * @return boolean says whether this was equal to that
     */
    public boolean equals(Object o) {
        if (o instanceof Theater) {
            Theater that = (Theater)o;
            return this.hashCode() == that.hashCode();
        }
        else {
            return false;
        }
    }

    /**
     * Turns this Theater into a String
     * @return String representation of this Theater
     */
    public String toString() {
        return this.name + ":" + this.seatsLeft.toString();
    }
    /**
     * Overriding hashCode because I overrode equals
     * @return int that is the hashCode of this Theater
     */
    public int hashCode() {
        return this.name.hashCode() + this.seatsMax;
    }

}

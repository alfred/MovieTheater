/**
 * Class to represent a Ticket
 * @author Alfred Ababio
 * @version 2
 *
 */
public class Ticket {
    /** String to represent the name of the type of Ticket */
    String name;
    /** Integer to represent the price for this ticket */
    Integer price;
    
    /**
     * Constructor for a Ticket
     * @param name The name of this ticket type
     * @param price The price for this ticket
     */
    Ticket(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
    
    /**
     * Overridden equality method for Tickets
     * @param o Object to be compared against this Ticket
     * @return boolean if this and o are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Ticket) {
            Ticket that = (Ticket)o;
            return this.hashCode() == that.hashCode();
        }
        else {
            return false;
        }
    }
    
    /**
     * Turns this Ticket into a String
     * @return String representation of this ticket
     */
    public String toString() {
        return this.name + ":" + this.price.toString();
    }
    
    /**
     * Turns this Ticket into its hashCode
     * @return int hashCode of this String
     */
    public int hashCode() {
        return this.name.hashCode() + this.price;
    }
}

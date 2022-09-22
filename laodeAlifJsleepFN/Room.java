package laodeAlifJsleepFN;


/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Room
{
    // instance variables - replace the example below with your own
    private int x;
    public int size;
    public String name;
    public Facility facility;
    public Price price;
    /**
     * Constructor for objects of class Room
     */
    public Room(String name, int size, Price price, Facility facility)
    {
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;

    }


}

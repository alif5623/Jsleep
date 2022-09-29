package laodeAlifJsleepFN;


/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Room extends Serializable
{

    public int size;
    public String name;
    public Facility facility;
    public Price price;
    public BedType bedType;
    public City city;
    public String address;

    /*
     * Constructor for objects of class Room

    public Room(String name, int size, Price price, Facility facility)
    {
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;

    }

    */
    public Room(int id, String name, int size, Price price, Facility facility, City city, String address){
        super(id);
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
    }

    public String toString(){
        return "\nID: " + this.id + "\nName: " + this.name + "\nSize: " + this.size + "\nPrice: " + this.price +
                "\nFacility: " + this.facility + "\nCity: " + this.city + "\nAddress: " + this.address;
    }
}

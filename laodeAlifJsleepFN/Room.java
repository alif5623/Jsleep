package laodeAlifJsleepFN;


/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Room extends Serializable implements FileParser
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
        this.bedType = BedType.SINGLE;
    }

    public String toString(){
        return "\nName: " + this.name + "\nSize: " + this.size + this.price +
                "\nFacility: " + this.facility + "\nCity: " + this.city + "\nAddress: " + this.address;
    }
    public Object write(){
        return null;
    }
    public Boolean read(String a){
        return true;
    }
}

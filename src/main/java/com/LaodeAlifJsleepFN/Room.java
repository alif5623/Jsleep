package com.LaodeAlifJsleepFN;

import java.util.Date;
import java.util.ArrayList;
/**
 * Room object to store registered Room information
 *
 * @author Laode Alif Ma'sum
 */
public class Room extends Serializable {

    public int size;
    public int accountId;
    public String name;
    public ArrayList<Facility> facility;
    public Price price;
    public BedType bedType;
    public City city;
    public String address;
    public ArrayList <Date> booked = new ArrayList <Date>();

    /**
     * Constructor to assign parameter into class's instance variable
     * @param accountId is room owner's account id
     * @param name is room's name
     * @param size is room's size
     * @param price is room's price
     * @param facility is the list of room's facility
     * @param city is room's city location
     * @param address is room's address location
     * @param typeBed is room's available bed type
     */
    public Room(int accountId, String name, int size, Price price, ArrayList<Facility> facility, City city, String address, BedType typeBed){
        this.accountId = accountId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
        this.bedType = typeBed;
    }

    /**
     * Method to turn Room object into String
     * @return String format of Room
     */
    public String toString(){
        return "\nName: " + this.name + "\nbedType = " + this.bedType + "\nSize: " + this.size + this.price +
                "\nFacility: " + this.facility + "\nCity: " + this.city + "\nAddress: " + this.address;
    }

}

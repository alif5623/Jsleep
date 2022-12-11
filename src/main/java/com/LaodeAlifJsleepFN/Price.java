package com.LaodeAlifJsleepFN;


/**
 * Price class object to store room's price
 *
 * @author Laode Alif Ma'sum
 */
public class Price
{
    public double price;
    public double discount;

    /**
     * Constructor to assign parameter to instance variable
     * @param price is the price of the room
     */
    public Price(double price){
        this.price = price;
        this.discount = 0;
    }

    /**
     * Constructor to assign parameter to instance variable
     * @param price is the price of the room
     * @param discount is the discount percentage of the room
     */
    public Price(double price, double discount){
        this.price = price;
        this.discount = discount;
    }

    /**
     * Method to turn Price object into String format
     * @return string format of Price
     */
    public String toString(){
        return "\nPrice: " + this.price + "\nDiscount: " + this.discount;
    }


}

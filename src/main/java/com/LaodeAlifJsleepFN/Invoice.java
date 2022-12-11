package com.LaodeAlifJsleepFN;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Invoice here.
 *
 * @author Laode Alif Ma'sum
 */
public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
    public PaymentStatus status;
    public RoomRating rating;

    /**
     * Enumeration of room's rating
     */
    public enum RoomRating{
        NONE, BAD, NEUTRAL, GOOD
    }

    /**
     * Enumeration of payment status
     */
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }

    /**
     * Constructor to assign parameter to object variable when
     * object is created
     * @param buyerId is the account id of buter
     * @param renterId is the account id of renter
     */
    protected Invoice(int buyerId, int renterId)
    {
        this.buyerId = buyerId;
        this.renterId = renterId;
    }

    /**
     * Constructor to assign parameter to object variable when
     * object is created
     * @param buyer is buyer's account id
     * @param renter is renter's account id
     */
    public Invoice(Account buyer, Renter renter){
        this.buyerId = buyer.id;
        this.renterId = renter.id;
    }

    /**
     * Method to turn Invoice object into String format
     * @return String formatted of Invoice
     */
    public String print(){
        return "\nID: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId;
    }
}

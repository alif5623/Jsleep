package com.LaodeAlifJsleepFN;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Payment class to make payment that's been done by user
 */
public class Payment extends Invoice {
    public Date to;
    public Date from;
    private int roomId;

    /**
     * Constructor to assign the parameter
     * @param buyerId is buyer's account id
     * @param renterId is renter's account id
     * @param roomId is room's registered id
     * @param from is the date room renting started
     * @param to is the date room renting ended
     */
    public Payment(int buyerId, int renterId, int roomId, Date from, Date to) {
        super(buyerId, renterId);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }
    /*
    public Payment(Account buyer, Renter renter, int roomId, Date from, Date to) {
        super(buyer.id, renter.id);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }*/

    /**
     * Method to turn payment object into String format
     * @return string format of payment
     */
    public String print() {
        return "\nId: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId +
                "\nRoom ID: " + this.roomId + "\nFrom: " + this.from + "\nTo: " + to;
    }

    /**
     * method to get room id of selected payment
     * @return selected payment's room id
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * method to make booking of a room
     * @param from is the date that room booking started
     * @param to is the date that room booking ended
     * @param room is the room object that's been selected to book
     * @return true if room available, else false
     */
    public static boolean makeBooking(Date from, Date to, Room room) {
        if (availability(from, to, room)) {
            Calendar start = Calendar.getInstance();
            start.setTime(from);
            Calendar end = Calendar.getInstance();
            end.setTime(to);
           for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                room.booked.add(date);
            }
            return true;
        }
        return false;
    }

    /**
     *Method to check if room is available to book during the selected date
     * @param from  is the date when the room booking'll start
     * @param to is the date when the room booking'll end
     * @param room is the selected room object to book
     * @return false if selected date is in booked list, else true
     */
    public static boolean availability(Date from, Date to, Room room) {
        Calendar start = Calendar.getInstance();
        start.setTime(from);
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        System.out.println("from " + from);
        System.out.println("to " + to);
        if (start.after(end) || start.equals(end)) {
            return false;
        }
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if (room.booked.contains(date)) {
                return false;
            }
        }
        return true;
    }
}

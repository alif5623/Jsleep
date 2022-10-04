package laodeAlifJsleepFN;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Write a description of class Invoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
    public Calendar time;
    public PaymentStatus status;
    public RoomRating rating;
    SimpleDateFormat SDFormat = new SimpleDateFormat(" 'Formatted Date: ' MM/dd/yyyy");
    public enum RoomRating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
    protected Invoice(int id, int buyerId, int renterId)
    {
        super(id);
        this.id = id;
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.time.getTime();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.id = id;
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = Calendar.getInstance();
        this.time.getTime();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String print(){
        return "\nID: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId + "\nTime: " + this.time;
    }
}

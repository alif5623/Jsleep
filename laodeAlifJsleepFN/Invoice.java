package laodeAlifJsleepFN;


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
    public String time;
    public PaymentStatus status;
    public RoomRating rating;

    public enum RoomRating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        super(id);
        this.id = id;
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.id = id;
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String print(){
        return "\nID: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId + "\nTime: " + this.time;
    }
}

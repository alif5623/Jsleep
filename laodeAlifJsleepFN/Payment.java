package laodeAlifJsleepFN;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payment extends Invoice
{

    public Calendar to;
    public Calendar from;
    private int roomId;
    SimpleDateFormat SDFormat = new SimpleDateFormat("dd MMMM yyyy");
    public Payment(int id, int buyerId, int renterId, int roomId)
    {
        super(id, buyerId, renterId);
        this.id = id;
        this.buyerId = buyerId;
        this.renterId = renterId;
        //this.time = time
        this.roomId = roomId;
        this.from = Calendar.getInstance();
        this.to = Calendar.getInstance();
        this.from.getTime();
        this.to.add(Calendar.DATE, 2);

    }

    public Payment(int id, Account buyer, Renter renter, String time, int roomId)
    {
        super(id, buyer.id, renter.id);
        this.id = id;
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.from = Calendar.getInstance();
        this.to = Calendar.getInstance();
        this.from.getTime();
        this.to.add(Calendar.DATE, 2);
    }

    public String print(){
        return "\nId: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId + "\nTime: " + time +
                "\nRoom ID: " + this.roomId + "\nFrom: " + this.from + "\nTo: " + to;
    }
    public int getRoomId(){
        return this.roomId;
    }

    public String getDuration(){
        return SDFormat(this.from.getTime() + "-" + this.to.getTime()) ;
    }

    public String getTime(){
        return SDFormat(this.from.getTime());
    }

}

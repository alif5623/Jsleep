package laodeAlifJsleepFN;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Payment extends Invoice
{

    public Date to;
    public Date from;
    private int roomId;
    SimpleDateFormat SDFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Payment(int id, int buyerId, int renterId, int roomId, Date from, Date to)
    {
        super(id, buyerId, renterId);
        this.id = id;
        this.buyerId = buyerId;
        this.renterId = renterId;
        //this.time = time
        this.roomId = roomId;
        /*this.from = Calendar.getInstance();
        this.to = Calendar.getInstance();
        this.from.getTime();
        this.to.add(Calendar.DATE, 2);*/
        this.from = new Date();
        this.to = new Date();

    }

    public Payment(int id, Account buyer, Renter renter, String time, int roomId, Date from, Date to)
    {
        super(id, buyer.id, renter.id);
        this.id = id;
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        /*
        this.from = Calendar.getInstance();
        this.to = Calendar.getInstance();
        this.from.getTime();
        this.to.add(Calendar.DATE, 2);*/
        this.from = new Date();
        this.to = new Date();
    }

    public String print(){
        return "\nId: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId + "\nTime: " + time +
                "\nRoom ID: " + this.roomId + "\nFrom: " + this.from + "\nTo: " + to;
    }
    public int getRoomId(){
        return this.roomId;
    }
    /*
    public String getDuration(){
        String strfrom = SimpleDateFormat.format(this.from.getTime());
        String strto = SimpleDateFormat.format(this.to.getTime());
        return strfrom + "-" + strto;
       // return SDFormat.format(this.from.getTime() + "-" + this.to.getTime());
    } */

    public static boolean makeBooking(Date from, Date to, Room room){
        if(availability(from, to, room)){
            room.booked.add(from);
            room.booked.add(to);
            return true;
        }else{
            return false;
        }
  

}

    public String getTime(){
        return SDFormat.format(this.from.getTime());
    }

    public static boolean availability(Date from, Date to, Room room){
            if(to.before(from)){
                return false;
            }
            if(room.booked.isEmpty()){
                return true;
            }

            for(int i = 0; i < room.booked.size(); i++){
                if(to.after(room.booked.get(i)) && to.before(room.booked.get(i + 1))){
                    return false;
                }else if(from.after(room.booked.get(i)) && from.before(room.booked.get(i + 1))){
                    return false;
                }
            }
            return false;
        
    }


}

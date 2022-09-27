package laodeAlifJsleepFN;



public class Payment extends Invoice
{

    public String to;
    public String from;
    private int roomId;

    public Payment(int id, int buyerId, int renterId, String time, int roomId, String from, String to)
    {
        super(id, buyerId, renterId, time);
        this.id = id;
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }

    public Payment(int id, Account buyer, Renter renter, String time, int roomId, String from, String to)
    {
        super(id, buyer.id, renter.id, time);
        this.id = id;
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }

    public String print(){
        return "\nId: " + this.id + "\nBuyer: " + this.buyerId + "\nRenter: " + this.renterId + "\nTime: " + time +
                "\nRoom ID: " + this.roomId + "\nFrom: " + this.from + "\nTo: " + to;
    }
    public int getRoomId(){
        return this.roomId;
    }

}

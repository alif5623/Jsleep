package LaodeAlifJsleepFN;


public class Complaint extends Serializable
{
    public String desc;
    public String date;

    public Complaint(int id, String date, String desc)
    {
        //super(id);
       // this.id = id;
        this.date = date;
        this.desc = desc;
    }

    public String toString(){
        return "\nID: " + this.id + "\nDate: " + this.date + "\nDesc: " + this.desc;
     }


}

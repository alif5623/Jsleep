package laodeAlifJsleepFN;


/**
 * Write a description of class Rating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rating
{
    // instance variables - replace the example below with your own
    private long count;
    private long total;

    public Rating()
    {
        this.total = 0;
        this.count = 0;// initialise instance variables

    }
    public void insert(int rating){
        this.total += rating;
        this.count++;
    }
    public double getAverage(){
        if(count == 0){
            System.out.println("Count invalid!");
            return 0;
        }else{
            return total/=count;
        }
    }
    public long getCount(){
        return this.count;
    }
    public long getTotal(){
        return this.total;
    }

    public String toString(){
        return "\nTotal: " + this.total + "\nCount: " + this.count;
    }

}

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
    private int x;
    private long count;
    private long total;

    /**
     * Constructor for objects of class Rating
     */
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

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}

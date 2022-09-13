package laodeAlifJsleepFN;

public class JSleep
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class JSleep
     */
    public static void main (String [] args)
    {
        // initialise instance variables

    }
    public int getHotelId(){
        return 0;
    }
    public String getHotelName(){
        return "hotel";
    }
    public boolean isDiscount(){
        return true;
    }
    public float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if(beforeDiscount < afterDiscount){
            return 0.0f;
        }else{
            return ((beforeDiscount-afterDiscount)/beforeDiscount) * 100;
        }
    }
    public int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage > 100){
            return 0;
        }else{
            return ((100 - (int)discountPercentage)/100) * price;
        }
    }
    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        return discountedPrice / (1-(int)discountPercentage);
    }
    public float getAdminFeePercentage(){
        return 0.05f;
    }
    public int getAdminFee(int price){
        return ((int)getAdminFeePercentage() * price);
    }
    public int getTotalPrice(int price, int numberOfNight){
        return ((price*numberOfNight) + (5/100 * price * numberOfNight));
    }
}

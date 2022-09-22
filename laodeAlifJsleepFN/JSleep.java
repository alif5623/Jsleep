//Nama: Laode ALif Ma'sum Sidrajat Raja Ika
//NPM : 2106731213
package laodeAlifJsleepFN;

public class JSleep
{
    public static Room createRoom(){
        Price price = new Price(100000, 5);
        Room room = new Room("hotel", 30, price, Facility.AC);
        return room;
    }
    public static void main (String [] args)
    {
        // initialise instance variables
        Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility);

    }
 
    /*
    public static int getHotelId(){
        return 0;
    }
    public static String getHotelName(){
        return "hotel";
    }
    public static boolean isDiscount(){
        return true;
    }
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if(beforeDiscount < afterDiscount){
            return 0.0f;
        }else{
            return (((float)beforeDiscount-(float)afterDiscount)/(float)beforeDiscount) * 100;
        }
    }
    public static int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage >= 100){
            return 0;
        }else{
            return ((100 - (int)discountPercentage)/100) * price;
        }
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        return discountedPrice / (1-(int)discountPercentage);
    }
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    public static int getAdminFee(int price){
        return ((int)getAdminFeePercentage() * price);
    }
    public static int getTotalPrice(int price, int numberOfNight){
        return ((price*numberOfNight) + ((int)getAdminFee(price * numberOfNight)));
    }*/

}


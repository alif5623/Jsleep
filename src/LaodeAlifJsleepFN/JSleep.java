//Nama: Laode ALif Ma'sum Sidrajat Raja Ika
//NPM : 2106731213
package laodeAlifJsleepFN;
import java.sql.*;
import java.util.ArrayList;

public class JSleep
{
    /*
    public static Room createRoom(){
        Price price = new Price(100000, 5);
      //  Room room = new Room("hotel", 30, price, Facility.AC);
        Room room = new Room(1, "Alif", 2, price, Facility.AC, City.JAKARTA, "Rawamangun");
        return room;
    }*/
    public static void main (String [] args)
    {
       /* // initialise instance variables
        Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility);
        Payment testRoom = new Payment(1, 1, 1, "", 1, "", "");
        Invoice testInvoice = new Invoice(2,2,2, "");
        System.out.println(testRoom.print());
        System.out.println(testInvoice.print());
        
        Complaint testComplain = new Complaint(1, "23 August 2022", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Room testRoom = new Room(1, "Presidential Suite", 5, testPrice,
                Facility.FitnessCenter, City.DEPOK, "JL. Margonda Raya");
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        System.out.println(testComplain.toString());
        System.out.println(testRoom.toString());
        System.out.println(testAccount.toString());
        System.out.println(testPrice.toString());
        System.out.println(testRating.toString());
        Payment testPayment = new Payment(2, 2, 2,2);
        System.out.println(testPayment.getTime());
        System.out.println(testPayment.getDuration());
        Price[] unfilteredArray = new Price[5];
        for(int i=0;i < unfilteredArray.length;i++){
        int j = 5000;
        unfilteredArray[i] = new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(int i=0;i < unfilteredArray.length;i++){
        System.out.println(unfilteredArray[i].price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000,true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000,false));

        Room RoomA = JSleep.createRoom();
        Room RoomB = JSleep.createRoom();
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start = Date.valueOf("2022-8-15");
        Date end = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start, end,RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start2 = Date.valueOf("2022-8-18");
        Date end2 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start2, end2,RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18 untuk kamar berbeda");
        Date start3 = Date.valueOf("2022-8-18");
        Date end3 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start3, end3,RoomB));
        /*
        System.out.println("Membuat booking dari tanggal 20 hingga 15");
        Date start = Date.valueOf("2022-8-20");
        Date end = Date.valueOf("2022-8-15");
        System.out.println(Payment.makeBooking(start, end,RoomA));
        System.out.println("Hello from Intellij!");*/
        ArrayList<Room> RoomSerialized = new ArrayList<Room>();

        for (int i = 0; i < 5; i++){
            RoomSerialized.add(i, JSleep.createRoom());
            System.out.println(RoomSerialized.get(i).toString());
        }
    }

    public static Room createRoom(){
        Price price = new Price(100000, 0.5);
        Room room = new Room ("Hotel",30, price, Facility.AC, City.DEPOK, "Jalan Margonda Raya");
        return room;
    }
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
    }

}*/


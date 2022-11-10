//Nama: Laode ALif Ma'sum Sidrajat Raja Ika
//NPM : 2106731213
package com.LaodeAlifJsleepFN;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.dbjson.JsonDBEngine;
import com.google.gson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JSleep {
    class Country {
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    public static void main(String[] args) {
        JsonDBEngine.Run(JSleep.class);
        SpringApplication.run(JSleep.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
        // Renter testRegex = new Renter("Netlab_", "081234567890", "Jl Margonda Raya");
       // Renter testRegexFail = new Renter("netlab", "081", "Jalan");
        /*Account testRegex = new Account("Alif", "alif5623@yahoo.com", "Alif56234");
        Account testRegexFail = new Account("Alif", "alif2_@yahoo.com","Alif56234");
        System.out.println(testRegex.validate());
        System.out.println(testRegexFail.validate());
        try{
            String filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\LaodeAlifJsleepFN\\json\\account.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.add(new Account("name", "email", "password"));
            tableAccount.writeJson();
            System.out.println(tableAccount);
        }catch(Throwable t){
            t.printStackTrace();
        }
        try {
            String filePath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\LaodeAlifJsleepFN\\json\\account.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filePath);
            tableAccount.add(new Account("name", "email", "password"));
            JsonTable.writeJson(tableAccount, filePath);
            tableAccount.forEach(account -> System.out.println(account));
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < 10; i++){
            ThreadingObject thread = new ThreadingObject("Thread " + i);
        }
       /* try
        {
            String filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\LaodeAlifJsleepFN\\json\\randomRoomList.json";
            JsonTable<Room> tableRoom = new JsonTable<>(Room.class, filepath);
       //     List<Room> filterTableRoom = filterByCity(tableRoom, "medan", 0, 5);
            List<Room> filterTableRoom = filterByPrice(tableRoom, 100000, 250000);
         //   List<Room> filterTableRoom = filterByAccountId(tableRoom, 1, 0, 5);
            filterTableRoom.forEach(room -> System.out.println(room.toString()));
        }
        catch (Throwable t){
            t.printStackTrace();
        }*/
    }

    public static List<Room> filterByCity(List<Room> list, String search, int page, int pageSize){
        return Algorithm.<Room>paginate(list, page, pageSize, room->room.city.toString().toLowerCase().contains(search.toLowerCase()));
    }

    public static List<Room> filterByPrice(List<Room> list, double minPrice, double maxPrice){
        return Algorithm.<Room>collect(list, i->i.price.price <= maxPrice && i.price.price >= minPrice);
    }

    public static List<Room> filterByAccountId(List<Room> list, int accountId, int page, int pageSize){
        return Algorithm.<Room>paginate(list, page, pageSize,room -> room.accountId == accountId);
    }


    public static Room createRoom() {
        Price price = new Price(100000, 5);
        //  Room room = new Room("hotel", 30, price, Facility.AC);
        Room room = new Room(1, "Alif", 2, price, Facility.AC, City.JAKARTA, "Rawamangun");
        return room;
    }
}
/*
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

        System.out.println("Membuat booking dari tanggal 20 hingga 15");
        Date start = Date.valueOf("2022-8-20");
        Date end = Date.valueOf("2022-8-15");
        System.out.println(Payment.makeBooking(start, end,RoomA));
        System.out.println("Hello from Intellij!");
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
    */
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


package com.LaodeAlifJsleepFN.controller;
import com.LaodeAlifJsleepFN.*;
import com.LaodeAlifJsleepFN.Account;
import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.Payment;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }
    @PostMapping("/create")
    public Payment create(
        @RequestParam int buyerId,
        @RequestParam int renterId,
        @RequestParam int roomId,
        @RequestParam String from,
        @RequestParam String to
    ) {
        Account account = Algorithm.<Account>find(new AccountController().getJsonTable(), predicate -> predicate.id == buyerId);
        Room room = Algorithm.<Room>find(new RoomController().getJsonTable(), predicate -> predicate.id == roomId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        try {
            fromDate = sdf.parse(from);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date toDate = null;
        try {
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long timeDiff = Math.abs(toDate.getTime() - fromDate.getTime());
        double price = room.price.price * (TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS));
        if(fromDate.before(room.booked.get(roomId)) && toDate.after(room.booked.get(roomId)) && price <= account.balance ){
            Payment payment = new Payment(buyerId, renterId, roomId, fromDate, toDate);
            account.balance -= price;
          //  payment.makeBooking(fromDate, toDate, roomId);
            paymentTable.add(payment);
            return payment;
        }
        return null;

    }
    @PostMapping("/{id}/accept")
    public boolean accept(@RequestParam int id){
        return false;
    }
    @PostMapping("/{id}/cancel")
    public boolean cancel(@RequestParam int id){
        return false;
    }
    @PostMapping("/{id}/cancel")
    public boolean submit(@RequestParam int id){
        return false;
    }

}

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
    @Override
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
        Date toDate;
        try {
            fromDate = sdf.parse(from);
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //long timeDiff = Math.abs(toDate.getTime() - fromDate.getTime());
       // double price = room.price.price * (TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS));

        if(Payment.availability(fromDate, toDate, room) && room.price.price <= account.balance ){
            Payment payment = new Payment(buyerId, renterId, roomId, fromDate, toDate);
            account.balance -= room.price.price;
            payment.status = Invoice.PaymentStatus.WAITING;
            payment.makeBooking(fromDate, toDate, room);
            paymentTable.add(payment);
            return payment;
        }
        return null;

    }
    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(getJsonTable(), predicate -> predicate.id == id);
        if(payment.status == Invoice.PaymentStatus.WAITING){
          payment.status = Invoice.PaymentStatus.SUCCESS;
          return true;
        }
        return false;
    }
    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id){
        Payment findPayment = Algorithm.<Payment>find(getJsonTable(), payment -> payment.id == id);
        if(findPayment.status == Invoice.PaymentStatus.WAITING){
            Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == findPayment.buyerId);
            Room findRoom = Algorithm.<Room>find(RoomController.roomTable, room -> room.id == findPayment.getRoomId());
            findPayment.status = Invoice.PaymentStatus.FAILED;
            account.balance += findRoom.price.price;
            return true;
        }
        return false;
    }
    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id){
        return false;
    }
}

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
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Controller for any Payment related API
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    /**
     * Getter for paymentTable
     * @return paymentTable
     */
    @Override
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    /**
     * Post method to create a new Payment
     * @param buyerId is the buyer's ID
     * @param renterId is the room renter's ID
     * @param roomId is the rented room's ID
     * @param from is the date when the book'll start
     * @param to is the date when the book'll end
     * @return payment if payment is successfull, else null
     */
    @PostMapping("/create")
    public Payment create(
        @RequestParam int buyerId,
        @RequestParam int renterId,
        @RequestParam int roomId,
        @RequestParam String from,
        @RequestParam String to
    ) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == buyerId);
        Room findroom = Algorithm.<Room>find(RoomController.roomTable, room->room.id == roomId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate;
        Date toDate;
        try {
            fromDate = sdf.parse(from);
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("harga: " + findroom.price.price);
        System.out.println("balance : " +  account.balance);
        long duration = TimeUnit.DAYS.convert(toDate.getTime() - fromDate.getTime(), TimeUnit.MILLISECONDS);
        System.out.println("Duration : " + duration);
        double totalPrice = findroom.price.price * duration;
        System.out.println("Total Price: " + totalPrice);
        if(Payment.availability(fromDate, toDate, findroom) && totalPrice <= account.balance){
            Payment payment = new Payment(buyerId, renterId, roomId, fromDate, toDate);
            account.balance -= totalPrice;
            payment.status = Invoice.PaymentStatus.WAITING;
            payment.makeBooking(fromDate, toDate, findroom);
            paymentTable.add(payment);
            return payment;
        }
        return null;

    }

    /**
     * Getter to get on going payment
     * @param id current logged account
     * @return list of current logged account's on going payment
     */
    @GetMapping("/{id}/getMyOnGoingPayment")
    public List<Payment> getOnGoingPayment(
            @PathVariable int id
    ){
        Date date = new Date();
        return Algorithm.collect(getJsonTable(), (Predicate<Payment>) pred->pred.buyerId == id &&(pred.status == Invoice.PaymentStatus.WAITING || pred.status == Invoice.PaymentStatus.SUCCESS) && pred.to.after(date));
    }

    /**
     * Getter to get payment with WAITING status in paginated form
     * @param id current logged account
     * @param page page number
     * @param pageSize size of the page
     * @return paginated list of WAITING payment
     */
    @GetMapping("/{id}/getWaitingPayment")
    List<Payment> waitingPayment(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5")int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred->pred.renterId == id && pred.status == Invoice.PaymentStatus.WAITING);
    }
    /**
     * Getter to get payment with SUCCESS status in paginated form
     * @param id current logged account
     * @param page page number
     * @param pageSize size of the page
     * @return paginated list of SUCCESS payment
     */
    @GetMapping("/{id}/getCompletedPayment")
    List<Payment> sucessPayment(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5")int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred->pred.renterId == id && (pred.status == Invoice.PaymentStatus.SUCCESS || pred.status == Invoice.PaymentStatus.FAILED));
    }

    /**
     * Method to accept payment
     * @param id is the payment ID
     * @return true if payment successfully accepted, else false
     */
    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(getJsonTable(), predicate -> predicate.id == id);
        if(payment.status == Invoice.PaymentStatus.WAITING){
          payment.status = Invoice.PaymentStatus.SUCCESS;
          AccountController.accountTable.get(payment.buyerId).balance -= RoomController.roomTable.get(payment.renterId).price.price;
          AccountController.accountTable.get(payment.renterId).balance += RoomController.roomTable.get(payment.renterId).price.price;
          return true;
        }
        return false;
    }

    /**
     * Method to cancel payment
     * @param id is the payment ID
     * @return true if payment succesfully cancelled, else false
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id){
        Payment findPayment = Algorithm.<Payment>find(getJsonTable(), payment -> payment.id == id);
        if(findPayment.status == Invoice.PaymentStatus.WAITING){
            Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == findPayment.buyerId);
            Room findRoom = Algorithm.<Room>find(RoomController.roomTable, room -> room.id == findPayment.getRoomId());
            findRoom.booked.removeIf(date -> date.after(findPayment.from) && date.before(findPayment.to)); //ngehapus dari booked di room.json
            findRoom.booked.removeIf(date -> date.equals(findPayment.from));
            findPayment.status = Invoice.PaymentStatus.FAILED;
            account.balance += findRoom.price.price;
            return true;
        }
        return false;
    }

}

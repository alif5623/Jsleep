package com.LaodeAlifJsleepFN.controller;
import com.LaodeAlifJsleepFN.*;
import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.Price;
import com.LaodeAlifJsleepFN.Voucher;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController implements BasicGetController<Voucher>{
    @JsonAutowired(value = Voucher.class, filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\voucher.json")
    public static JsonTable<Voucher> voucherTable;
    @Override
    public JsonTable<Voucher> getJsonTable(){
        return voucherTable;
    }
    @GetMapping("{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        Voucher voucher = Algorithm.<Voucher>find(getJsonTable(), predicate -> predicate.id == id);
        return voucher.isUsed();
    }
    @GetMapping("{id}/canApply")
    boolean canApply(
            @PathVariable int id,
            @RequestParam double price
    ){
        Voucher voucher = Algorithm.<Voucher>find(getJsonTable(), predicate -> predicate.id == id);
        return voucher.canApply(new Price(price));
    }
    @GetMapping("/getAvailable")
    List<Voucher> getAvailble(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize
    ){
        List<Voucher> list = new ArrayList<Voucher>();
        for(Voucher i : getJsonTable()){
            if(!i.isUsed()){
                list.add(i);
            }
        }
        return Algorithm.<Voucher>paginate(list, page, pageSize, voucher -> !voucher.isUsed());
    }
}

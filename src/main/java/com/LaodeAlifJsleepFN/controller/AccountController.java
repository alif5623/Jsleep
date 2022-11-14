package com.LaodeAlifJsleepFN.controller;

import com.LaodeAlifJsleepFN.Account;
import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.Renter;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

// TODO sesuaikan dengan package Anda: package com.netlabJSleepGS.controller;


// TODO sesuaikan dengan package Anda: import com.netlabJSleepGS.Account;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class , filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\account.json")
    //@JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Account> accountTable;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[a-zA-Z]+)*\\.[a-zA-Z]+(?!\\s)$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!\\s)$";
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        if(!name.isBlank()){
            if(REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()){
                if(Algorithm.<Account>exists(getJsonTable(), acc -> acc.email.equals(email))) return null;
                String generatedPassword = null;
                try{
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                   /* for(int i = 0; i < bytes.length; i++){
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }*/
                    for(byte aByte : bytes){
                        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
                    }
                    generatedPassword = sb.toString();
                }
                catch(NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                Account newAccount = new Account(name, email, generatedPassword);
                accountTable.add(newAccount);
                return newAccount;
            }
        }
        return null;
    }
    @PostMapping("/{id}/registerRenter")
    Renter registerRenter(
            @PathVariable int id,
            @RequestParam String username,
            @RequestParam String address,
            @RequestParam String phoneNumber
            ){
        Account account = Algorithm.<Account>find(getJsonTable(), acc -> acc.id == acc.id );
        if(account.renter == null){
            account.renter = new Renter(username, address, phoneNumber);
            return account.renter;
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp(
            @PathVariable int id,
            @RequestParam double balance
    ){
        Account account = Algorithm.<Account>find(getJsonTable(), acc -> id == acc.id);
        if(account != null){
            account.balance += balance;
            return true;
        }
        return false;
    }
    @PostMapping("/login")
    Account login(
        @RequestParam String email,
        @RequestParam String password
    ){
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String finalGeneratedPassword = generatedPassword;
        return Algorithm.<Account>find(getJsonTable(), predicate -> predicate.email.equals(email) && predicate.password.equals(finalGeneratedPassword));
    }
    /*
    @GetMapping("/{id}")
    public String getById(@PathVariable int id) {
        return "account id " + id + " not found!";
    }*/
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

}

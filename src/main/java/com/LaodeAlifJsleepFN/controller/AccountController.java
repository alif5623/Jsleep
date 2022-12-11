package com.LaodeAlifJsleepFN.controller;

import com.LaodeAlifJsleepFN.*;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

// TODO sesuaikan dengan package Anda: package com.netlabJSleepGS.controller;


// TODO sesuaikan dengan package Anda: import com.netlabJSleepGS.Account;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for any Account related API
 */
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

    /**
     * Getter for account page
     * @return account page
     */
    @GetMapping
    String index() { return "account page"; }

    /**
     * Post method to register new account
     * @param name is the new account's name
     * @param email is the new account's email
     * @param password is the new account's password
     * @return newAccount if account successfully registered, else null
     */
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

    /**
     * Post method to register a new Renter
     * @param id is the account ID
     * @param username is the renter's username
     * @param address is the renter's address
     * @param phoneNumber is the renter's phone number
     * @return new registered renter if successful, else null
     */
    @PostMapping("/{id}/registerRenter")
    Renter registerRenter
    (
            @PathVariable int id,
            @RequestParam String username,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        Account account = Algorithm.<Account>find(getJsonTable(), obj -> obj.id == id);
        if(account.renter == null){
            Renter newRenter = new Renter(username, phoneNumber, address);
            account.renter = newRenter;
            return newRenter;
        }
        return null;
    }

    /**
     * Post method to top up account's balance
     * @param id is logged in account's id
     * @param balance is logged in account's balance
     * @return true if balance successfully added, else false
     */
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

    /**
     * Post method to login to a registered account
     * @param email is the entered email
     * @param password is the entered password
     * @return founded value of the entered account info
     */
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

    /**
     * Getter to get Account by ID
     * @param id is the account ID to be found in account.json
     * @return founded value of the entered account ID
     */
    @GetMapping("/{id}/getById")
    public Account getById(@PathVariable int id){
        return Algorithm.<Account>find(getJsonTable(), pred->pred.id == id);
    }

    /**
     * Getter to collect all registered account
     * @return list of all registered Account
     */
    @GetMapping("/getAllAccount")
    public List<Account> getAllAccount(){
        return Algorithm.collect(getJsonTable(), (Predicate<Account>) pred -> true);
    }

    /**
     * Getter for accountTable
     * @return accountTable
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

}

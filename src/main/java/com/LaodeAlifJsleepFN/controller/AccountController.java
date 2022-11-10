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
    public static final Pattern REGEX_PATTERN_PASSWORD = null;
    public static final Pattern REGEX_PATTERN_EMAIL = null;
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@(.+)$";
    public static final String REGEX_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)*[a-zA-Z\\d]{8,}$";
    @JsonAutowired(value = Account.class , filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\account.json")
    public static JsonTable<Account> accountTable;

    public static JsonTable<Account> getAccountTable() {
        return accountTable;
    }

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
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(email);
        boolean matchEmail = matcherEmail.find();

        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPassword = patternPassword.matcher(password);
        boolean matchFoundPassword = matcherPassword.find();
        Account findAccount = Algorithm.<Account> find(getJsonTable(), predicate -> predicate.email.equals(email));

        String generatedPassword = null;
        if(matchEmail && matchFoundPassword && !name.isBlank() && findAccount == null){
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
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
        return null;
    }
    @PostMapping("/registerRenter")
    Renter registerRenter(
            @PathVariable int id,
            @RequestParam String username,
            @RequestParam String address,
            @RequestParam String phoneNumber
            ){
        Account account = Algorithm.<Account>find(getJsonTable(), acc -> id == acc.id );
        if(account != null){
            account.renter = new Renter(username, address, phoneNumber);
        }
        return null;
    }

    @PostMapping("/{id}/topup")
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
        return Algorithm.<Account>find(accountTable, predicate -> predicate.email.equals(email) && predicate.password.equals(finalGeneratedPassword));
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id) {
        return "account id " + id + " not found!";
    }
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

}

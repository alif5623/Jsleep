package com.LaodeAlifJsleepFN;

import java.util.regex.*;

/**
 * Renter class object to store renter information
 *
 * @author Laode Alif Ma'sum
 */
public class Renter extends Serializable
{
    public String phoneNumber;
    public String address = "";
    public String username ;
    public static final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";
    public static final String REGEX_PHONE = "^[0-9]{9,12}$";

    /**
     * Constructor to assign parameter into instance variable
     * @param username is renter's registered username
     * @param phoneNumber is renter's registered phone number
     * @param address is renter's registered address
     */
    public Renter(String username, String phoneNumber, String address){
       this.username = username;
       this.phoneNumber = phoneNumber;
       this.address = address;
    }

    /**
     * Method to validate whether the username and phone number
     * is according to the regular expression
     *
     * @return true if username and phone number according to
     * regular expression, else false
     */
    public boolean validate(){
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        boolean matchPhone = matcherPhone.find();
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherName = patternName.matcher(username);
        boolean matchName = matcherName.find();
        return matchName && matchPhone;
    }

}

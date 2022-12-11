package com.LaodeAlifJsleepFN;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Account object to store registered
 * accounts on Jsleep
 */
public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public Renter renter;
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@(.+)$";
    public static final String REGEX_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)*[a-zA-Z\\d]{8,}$";
    public double balance;

    /**
     *Constructor
     * @param name is the name of account owner
     * @param email is the email of account owner (credentials for login)
     * @param password is the password registered by account owner(credentials for login)
     */
    public Account(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    /**
     * Method to convert account object into String format
     * @return the String format of account
     */
    public String toString(){
        return "\nID: " + this.id + "\nName: " + this.name + "\nEmail: " + this.email + "\nPassword: " + this.password;
    }

    /**
     * Method to validate whether the email and the password
     * is according to the regular expression
     * @return true if according to regular expression. Else, false
     */
    public boolean validate(){
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(email);
        boolean matchEmail = matcherEmail.find();
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPassword = patternPassword.matcher(password);
        boolean matchPassword = matcherPassword.find();
        return matchEmail && matchPassword;
    }
}

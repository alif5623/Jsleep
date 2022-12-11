package com.LaodeAlifJsleepFN;

/**
 * Voucher class object to store registered Voucher information
 */
public class Voucher extends Serializable
{
    public Type type;
    public double cut;
    public String name;
    public int code;
    public double minimum;
    private boolean used;

    /**
     * Constructor to assign parameter into instance variable
     * @param id is voucher's id
     * @param name is voucher's name
     * @param code is voucher's code
     * @param type is voucher's type
     * @param used is use to check if the voucher is used
     * @param minimum is minimum price requirement to use voucher
     * @param cut is voucher's price cut
     */
    public Voucher(int id, String name, int code, Type type, boolean used, double minimum, double cut){
        //super(id);
     //   this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.used = used;
        this.minimum = minimum;
        this.cut = cut;
    }

    /**
     * to check whether the voucher is used
     * @return true if used, else false
     */
    public boolean isUsed(){
        return this.used;
    }

    /**
     * to check whether the voucher can be apply
     * @param price is the price of room to be booked
     * @return true if price is higher than minimum price requirement,
     * else false
     */
    public boolean canApply(Price price){
        if(price.price > this.minimum && Boolean.FALSE.equals(this.used)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method to apply voucher
     * @param price is room's price that'll get cut by voucher code
     * @return
     */
    public double apply(Price price){
        this.used = true;
        if(this.type == Type.DISCOUNT){
            if(this.cut > 100){
                return 0;
            }else{
                return (100 - this.cut)/100 * price.price;
            }
        }else if(this.type == Type.REBATE){
            if(this.cut > price.price){
                this.cut = price.price;
            }
            return price.price - this.cut;
        }else{
            System.out.println("Type invalid!");
            return 0;
        }
    }

    public Object write(){
        return null;
    }
    public Boolean read(String a){
        return true;
    }
}

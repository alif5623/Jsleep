package laodeAlifJsleepFN;

import java.util.ArrayList;

public class Validate {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Validate
     */
    public Validate() {

    }

    public static ArrayList<Price> filter(Price[] list, int value, boolean less) {
        ArrayList<Price> ret = new ArrayList<Price>();
        ArrayList<Price> isLess = new ArrayList<Price>();
        ArrayList<Price> isMore = new ArrayList<Price>();
        int size = list.length; 
        for(int i = 0; i < size; i++){
            if (less == true) {
                isLess.add(list[i]);
                ret.addAll(isLess);
            }
            else{
                isMore.add(list[i]);
                ret.addAll(isMore);
            }
        }
        return ret;
    }
}

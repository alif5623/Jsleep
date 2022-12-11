package com.LaodeAlifJsleepFN;

/**
 * Threading class object to do multiThreading
 */
public class ThreadingObject extends Thread{
    /**
     * Constructor to assign thread's name
     * @param name is thread's name
     */
    public ThreadingObject(String name){
        super(name);
        this.start();
    }

    /**
     * method to print current running thread
     */
    @Override
    public void run(){
        System.out.println(currentThread().getName() + " is running");
        System.out.println("ID Thread " + currentThread().getId());
    }
}

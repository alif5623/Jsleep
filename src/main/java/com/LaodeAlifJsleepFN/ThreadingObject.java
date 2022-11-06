package com.LaodeAlifJsleepFN;


public class ThreadingObject extends Thread{
    public ThreadingObject(String name){
        super(name);
        this.start();
    }

    @Override
    public void run(){
        System.out.println(currentThread().getName() + " is running");
        System.out.println("ID Thread " + currentThread().getId());
    }
}

//Nama: Laode ALif Ma'sum Sidrajat Raja Ika
//NPM : 2106731213
package com.LaodeAlifJsleepFN;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.dbjson.JsonDBEngine;
import com.google.gson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class to launch spring
 * @author Laode Alif Ma'sum
 */
@SpringBootApplication
public class JSleep {
    /**
     * Main method to launch spring
     * @param args is an unused parameter since the app isn't launched
     *             in console line
     */
    public static void main(String[] args) {
        JsonDBEngine.Run(JSleep.class);
        SpringApplication.run(JSleep.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}


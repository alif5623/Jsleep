package com.LaodeAlifJsleepFN.controller;

import com.LaodeAlifJsleepFN.*;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController implements BasicGetController<Room>{
    @JsonAutowired(value = Room.class, filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\room.json")
    public static JsonTable<Room> roomTable;
    @Override
    public JsonTable<Room> getJsonTable(){
        return roomTable;
    }
    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, predicate -> predicate.id == id);
    }
    @PostMapping("/create")
    public Room create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int size,
            @RequestParam int price,
            @RequestParam Facility facility,
            @RequestParam City city,
            @RequestParam String address
    ){
        if(Algorithm.<Account>exists(AccountController.accountTable, acc -> acc.id == accountId && acc.renter != null)){
            Room newRoom = new Room(accountId, name, size, new Price(price), facility, city, address);
            roomTable.add(newRoom);
            return newRoom;
        }
        return null;
    }

    @GetMapping("/getAllRoom")
    List<Room> getAllRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12")int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred -> true);
    }

}

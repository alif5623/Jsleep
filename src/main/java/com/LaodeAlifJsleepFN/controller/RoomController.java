package com.LaodeAlifJsleepFN.controller;

import com.LaodeAlifJsleepFN.*;
import com.LaodeAlifJsleepFN.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for any Room related API
 */
@RestController
@RequestMapping("/room")
public class RoomController implements BasicGetController<Room>{
    @JsonAutowired(value = Room.class, filepath = "C:\\Users\\alif5\\Documents\\Kuliah\\Semester 3\\OOP(Praktikum)\\Project\\JSleep\\src\\main\\java\\com\\LaodeAlifJsleepFN\\json\\room.json")
    public static JsonTable<Room> roomTable;

    /**
     * Getter to get roomTable
     * @return roomTable
     */
    @Override
    public JsonTable<Room> getJsonTable(){
        return roomTable;
    }

    /**
     * Getter to get room's renter
     * @param id is the room's ID
     * @param page is the page number
     * @param pageSize is the size of the page
     * @return list of Renter
     */
    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, predicate -> predicate.id == id);
    }

    /**
     * Post method to create new Room
     * @param accountId is the current logged account's ID
     * @param name is the new room's name
     * @param size is the new room's size
     * @param price is the new room's price
     * @param facility is the list of new room's facility
     * @param city is the new room's city
     * @param address is the new room's address
     * @param typeBed is the new room's bed type
     * @return newRoom if room successfully created, else null
     */
    @PostMapping("/create")
    public Room create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int size,
            @RequestParam int price,
            @RequestParam ArrayList<Facility> facility,
            @RequestParam City city,
            @RequestParam String address,
            @RequestParam BedType typeBed
    ){
        if(Algorithm.<Account>exists(AccountController.accountTable, acc -> acc.id == accountId && acc.renter != null)){
            Room newRoom = new Room(accountId, name, size, new Price(price), facility, city, address, typeBed);
            roomTable.add(newRoom);
            return newRoom;
        }
        return null;
    }

    /**
     * Getter to get all registered Room in paginated form
     * @param page is the page number
     * @param pageSize is the size of the page
     * @return list of all paginated registered room
     */
    @GetMapping("/getPaginatedRoom")
    List<Room> getPaginatedRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12")int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred -> true);
    }

    @GetMapping("/getAllRoom")
    List<Room> getAllRoom(
    ){
        return Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> true);
    }
    /**
     * Getter to search room
     * @param roomName is the room's name to be searched
     * @param page is the page number
     * @param pageSize is the size of the page
     * @return paginated list of matched room's name
     */
    @GetMapping("/searchRoom")
    List<Room> searchRoom(
            @RequestParam String roomName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize

    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred -> pred.name.contains(roomName)); //contains biar bisa partial search
    }
    /**
     * Get method to get paginated and filtered room
     * @param page is the page number
     * @param pageSize is the size of the page
     * @param priceMin is the price minimum filter
     * @param priceMax is the price maximum filter
     * @param city is  the city filter
     * @param bed is the bed filter
     * @param sizeMin is the minimum size filter
     * @param sizeMax is the maximum size filter
     * @param facility is the list of facility filter
     * @return list of paginated and filtered room list, else null
     */
    @GetMapping("/getFilteredRoom")
    List<Room> getFilteredRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int pageSize,
            @RequestParam(required = false) City city,
            @RequestParam double priceMin,
            @RequestParam double priceMax,
            @RequestParam int sizeMin,
            @RequestParam int sizeMax,
            @RequestParam(required = false) BedType bed,
            @RequestParam(required = false) ArrayList<Facility> facility
    ){
        List<Room> filteredList = new ArrayList<>();
        if(priceMax != 0 && sizeMax == 0 && sizeMin == 0 && city == null && bed == null && facility == null){
            filteredList =  Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.price.price <= priceMax && pred.price.price >= priceMin);
        }else if(priceMax == 0 && priceMin == 0 && sizeMax == 0 && sizeMin == 0 && city != null && bed == null && facility == null){
            filteredList =  Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.city == city);
        }else if(priceMax == 0 && priceMin == 0 && sizeMax != 0 && city != null && bed == null && facility == null){
            filteredList = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.size <= sizeMax && pred.size >= priceMax);
        }else if(priceMax == 0 && priceMin == 0 && sizeMax == 0 && sizeMin == 0 && city == null && bed != null && facility == null){
            filteredList = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.bedType == bed);
        }else if(priceMax == 0 && priceMin == 0 && sizeMax == 0 && sizeMin == 0 && city == null && bed == null && facility != null){
            filteredList = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.facility.containsAll(facility));
        }
        return Algorithm.paginate(filteredList, page, pageSize, pred -> true);
    }

    /*
    @GetMapping("/getFilteredRoom")
    List<Room> getFilteredRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int pageSize,
            @RequestParam double priceMin,
            @RequestParam double priceMax,
            @RequestParam(required = false) City city,
            @RequestParam(required = false) BedType bed,
            @RequestParam int minSize,
            @RequestParam int maxSize,
            @RequestParam(required = false) ArrayList<Facility> facility
    ){


        List<Room> filteredRoom = null;

        if(priceMax != 0 && city == null && bed == null & minSize == 0 && maxSize == 0 && facility == null)
            return Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> (pred.price.price <= priceMax && pred.price.price >= priceMax));
        else if(priceMin == 0 && priceMax == 0 && city != null && bed == null && minSize == 0 && maxSize == 0)
            return Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.city == city && facility == null);

        return null;
        /*
        try{
            List<Room> filteredPrice = null;
            if(priceMax != 0 && city == null && bed == null && minSize == 0 && maxSize == 0 && facility == null){
                filteredRoom = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.price.price >= priceMin && pred.price.price <= priceMax);
            }else if(priceMin == 0 && priceMax == 0 && city != null && bed == null && minSize == 0 && maxSize == 0){
                filteredRoom = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.city == city && facility == null);
            }else if(priceMin == 0 && priceMax == 0 && city == null && bed != null && minSize == 0 && maxSize == 0){
                filteredRoom = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.bedType == bed);
            }else if(priceMin == 0 && priceMax == 0 && city == null && bed == null && maxSize != 0 && facility == null){
                filteredRoom = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.size >= minSize && pred.size <= maxSize);
            }else if(priceMin == 0 && priceMax == 0 && city == null && bed == null && minSize == 0 && maxSize == 0 && facility != null){
                filteredRoom = Algorithm.collect(getJsonTable(), (Predicate<Room>) pred -> pred.facility.containsAll(facility));
            }
            return  Algorithm.paginate(filteredRoom, page, pageSize, pred -> true);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }*/


}

package com.LaodeAlifJsleepFN.controller;

import com.LaodeAlifJsleepFN.Algorithm;
import com.LaodeAlifJsleepFN.JsonTable;
import com.LaodeAlifJsleepFN.Serializable;
import org.springframework.web.bind.annotation.*;
import com.LaodeAlifJsleepFN.*;
import java.util.List;
@RestController
@RequestMapping()
public interface BasicGetController <T extends Serializable>{
    public abstract JsonTable<T> getJsonTable();


    @GetMapping("/page")
    public default List<T> getPage(
            @RequestParam int page,
            @RequestParam int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, predicate -> true);
    }

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        Predicate<T> predicate = obj -> obj.id == id;
        return Algorithm.find(getJsonTable(), predicate);
    }
}

package com.example.hotel.controller;

import com.example.hotel.service.EarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/earnToday")
public class RoomController {
    @Autowired
    private EarnService earnService;

    @GetMapping("/{id}")
    public Map<LocalDate, Integer> earn(@PathVariable("id") Integer id){
        Map<LocalDate, Integer> earns=new TreeMap<>();
        earns.put(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), id), earnService.earn(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), id)));
        return earns;
    }
}

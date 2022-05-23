package com.example.hotel.service;

import com.example.hotel.model.Room;
import com.example.hotel.repository.BookingRepo;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EarnService {
    @Autowired
    private BookingRepo bookingRepo;

    public Integer earn(LocalDate date){
        List<Room> rooms=bookingRepo.findAll()
                .stream().filter(e->e.getToDate().isAfter(date)&&e.getFromDate().isBefore(date))
                .map(e->e.getRoom()).collect(Collectors.toList());
        Integer sum=rooms.stream().mapToInt(e->e.getPrice()).sum();
        return sum;

    }
}

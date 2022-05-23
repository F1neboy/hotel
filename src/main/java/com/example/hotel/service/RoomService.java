package com.example.hotel.service;

import com.example.hotel.model.Booking;
import com.example.hotel.model.Room;
import com.example.hotel.repository.BookingRepo;
import com.example.hotel.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired RoomRepo roomRepo;

    public List<Room> rooms(LocalDate toDate, LocalDate fromDate, int quantity, boolean view, boolean balc){
        List<Room> busyRooms= bookingRepo.findAll().stream()
                .filter(e->e.getToDate().isBefore(fromDate))
                .filter(c->c.getRoom().isOceanView()==view)
                .filter(c->c.getRoom().isBalcony()==balc)
                .filter(c->c.getRoom().getQuantity()>=quantity)
                .map(e->e.getRoom())
                .collect(Collectors.toList());
        return busyRooms;
    }
}

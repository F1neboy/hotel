package com.example.hotel.service;

import com.example.hotel.model.Booking;
import com.example.hotel.model.Client;
import com.example.hotel.model.Room;
import com.example.hotel.repository.BookingRepo;
import com.example.hotel.repository.ClientRepo;
import com.example.hotel.repository.RoomRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
@Slf4j
public class InitService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private RoomRepo roomRepo;

    @PostConstruct
    public void init(){
        Client cl1=clientRepo.save(new Client(0l, "Marek", "Kowalski", "markow", "123"));
        Client cl2=clientRepo.save(new Client(0l, "Tomasz", "Nowak", "tom", "123"));
        Client cl3=clientRepo.save(new Client(0l, "Agata", "Pasik", "pasi", "123"));
        Client cl4=clientRepo.save(new Client(0l, "Franek", "Lotka", "lota", "123"));
        Client cl5=clientRepo.save(new Client(0l, "Ola", "Poracka", "olpa", "123"));

        Room r1=roomRepo.save(new Room(0l, "Niebieski", true, true, 2, 300));
        Room r2=roomRepo.save(new Room(0l, "Fioletowy", true, false, 3, 250));
        Room r3=roomRepo.save(new Room(0l, "Zielony", false, false, 4, 350));
        Room r4=roomRepo.save(new Room(0l, "Czerwony", false, true, 2, 250));
        Room r5=roomRepo.save(new Room(0l, "Hibiskusowy", false, true, 4, 400));

        bookingRepo.save(new Booking(0l, cl1, r1, LocalDate.now().minusDays(5), LocalDate.now().plusDays(1)));
        bookingRepo.save(new Booking(0l, cl2, r2, LocalDate.now().minusDays(5), LocalDate.now().plusDays(1)));
        bookingRepo.save(new Booking(0l, cl3, r3, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3)));
        bookingRepo.save(new Booking(0l, cl4, r4, LocalDate.now().minusDays(1), LocalDate.now().plusDays(4)));
        bookingRepo.save(new Booking(0l, cl5, r5, LocalDate.now(), LocalDate.now().plusDays(5)));
    }
}

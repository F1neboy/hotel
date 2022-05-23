package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;
}

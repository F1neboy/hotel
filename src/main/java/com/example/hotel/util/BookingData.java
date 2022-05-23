package com.example.hotel.util;

import com.example.hotel.model.Booking;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringComponent
@Slf4j
public class BookingData {
    @Getter
    @Setter
    private Booking booking;
}

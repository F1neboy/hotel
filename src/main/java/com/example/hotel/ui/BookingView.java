package com.example.hotel.ui;

import com.example.hotel.util.BookingData;
import com.example.hotel.util.UserData;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/ui/booking")
public class BookingView extends VerticalLayout {
    @Autowired
    private BookingData bookingData;

    @Autowired
    private UserData userData;

    @PostConstruct
    public void init(){
        add(new H3("Pokój został zarezerwowany!"), new H4("Numer pokoju: "+bookingData.getBooking().getRoom().getId())
                , new H4("Od: "+bookingData.getBooking().getFromDate())
                , new H4("Do: "+bookingData.getBooking().getToDate())
                , new H4("Imię oraz nazwisko: "+ bookingData.getBooking().getClient().getImie()+" "+bookingData.getBooking().getClient().getNazwisko()));
        add(new Button("Wróc do menu", e->{
            UI.getCurrent().navigate(FindView.class);
        }));
    }
}

package com.example.hotel.ui;

import com.example.hotel.model.Booking;
import com.example.hotel.repository.BookingRepo;
import com.example.hotel.util.UserData;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/ui/myReservation")
public class ReservationView extends VerticalLayout {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserData userData;

    private final Grid<Booking> myBook=new Grid<>(Booking.class, false);
    private final Button ret=new Button("Wróć", e->{
        UI.getCurrent().navigate(FindView.class);
    });

    @PostConstruct
    public void init(){
        add(new H4("Jesteś zalogowany jako: "+ userData.getClient().getImie()+" "+userData.getClient().getNazwisko()));
        myBook.addColumn(b->b.getRoom().getId()).setHeader("Numer pokoju");
        myBook.addColumn(b->b.getRoom().getName()).setHeader("Nazwa pokoju");
        myBook.addColumn(b->b.getFromDate()).setHeader("Od kiedy");
        myBook.addColumn(b->b.getToDate()).setHeader("Do kiedy");
        myBook.setItems(bookingRepo.findByClientLogin(userData.getClient().getLogin()));
        add(myBook, ret);

    }
}

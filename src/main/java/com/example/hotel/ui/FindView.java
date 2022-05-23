package com.example.hotel.ui;

import com.example.hotel.model.Booking;
import com.example.hotel.model.Room;
import com.example.hotel.repository.BookingRepo;
import com.example.hotel.repository.ClientRepo;
import com.example.hotel.service.RoomService;
import com.example.hotel.util.BookingData;
import com.example.hotel.util.UserData;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static java.lang.Integer.parseInt;

@Route("/ui/find")
public class FindView extends VerticalLayout {


    @Autowired
    private BookingData bookingData;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private UserData userData;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingRepo bookingRepo;

    private final DatePicker fromDate= new DatePicker("Od kiedy: ");
    private final DatePicker toDate= new DatePicker("Do kiedy: ");
    private final Checkbox view=new Checkbox("Widok na ocean");
    private final Checkbox balcony=new Checkbox("Balkon");
    private final TextField quantity=new TextField("Ilość osób: ");
    private final Button search=new Button("Szukaj!", a -> find());
    private final Text info=new Text("Uzupełnij formularz aby znaleźć pokój");
    private final Button back=new Button("Cofnij do wyszukiwania", a -> initAgain());
    private final Button logout=new Button("Wyloguj", a-> logout());
    private final Button book=new Button("Rezerwuj!", a->book());
    private final Grid<Room> grid=new Grid<>(Room.class, false);
    private final Button myBook=new Button("Moje rezerwacje", e->{
        UI.getCurrent().navigate(ReservationView.class);
    });

    @PostConstruct
    public void init(){
        add(new H4("Jesteś zalogowany jako: "+ userData.getClient().getImie()+" "+userData.getClient().getNazwisko()),new HorizontalLayout (logout, myBook));
        add(info , fromDate, toDate, view, balcony, quantity, search);
    }
    public void initAgain(){
        remove(back, book, grid);
        grid.removeAllColumns();
        add(info , fromDate, toDate, view, balcony,quantity, search);
    }
    public void find(){
        remove(info, fromDate, toDate,view, balcony,quantity,search);
        grid.addColumn(b->b.getId()).setHeader("Numer pokoju");
        grid.addColumn(b->b.getName()).setHeader("Nazwa pokoju");
        grid.addColumn(b->b.getQuantity()).setHeader("Maksymalna liczba osób");
        grid.addColumn(b->b.getPrice()).setHeader("Cena pokoju za dobę");
        grid.setItems(roomService.rooms(toDate.getValue(), fromDate.getValue(), parseInt(quantity.getValue()), view.getValue(), balcony.getValue()));
        add(grid, book, back);
    }
    public void book(){
        bookingData.setBooking(new Booking(0l, clientRepo.findByLogin(userData.getClient().getLogin()), grid.asSingleSelect().getValue(), fromDate.getValue(), toDate.getValue()));
        bookingRepo.save(bookingData.getBooking());
        UI.getCurrent().navigate(BookingView.class);

    }
    public void logout(){
        userData.setClient(null);
        UI.getCurrent().navigate(LoginView.class);
    }
}

package com.example.hotel.ui;

import com.example.hotel.model.Client;
import com.example.hotel.repository.ClientRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/ui/create")
public class CreateView extends VerticalLayout {
    @Autowired
    private ClientRepo clientRepo;

    private final TextField imie= new TextField("Imię: ");
    private final TextField nazwisko= new TextField("Nazwisko: ");
    private final TextField login= new TextField("Login: ");
    private final PasswordField haslo= new PasswordField("Hasło: ");
    private final Button create=new Button("Utworz konto!", a->create());

    @PostConstruct
    public void init(){
        add(imie, nazwisko, login, haslo, create);
    }

    public void create(){
        if(clientRepo.findByLogin(login.getValue())==null) {
            clientRepo.save(new Client(clientRepo.count()+1, imie.getValue(), nazwisko.getValue(), login.getValue(), haslo.getValue()));
            Notification.show("Konto zostało utworzone!");
            UI.getCurrent().navigate(LoginView.class);
        }
        else{
            Notification.show("Ten login jest zajęty!");
            login.clear();
        }
    }
}

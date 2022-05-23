package com.example.hotel.ui;


import com.example.hotel.model.Client;
import com.example.hotel.repository.ClientRepo;
import com.example.hotel.util.UserData;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/ui/login")
public class LoginView extends VerticalLayout {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private UserData userData;

    private final TextField login=new TextField("Login: ");
    private final PasswordField password=new PasswordField("Hasło: ");
    private final Button createAccount=new Button("Utwórz konto", a -> createUser());
    private final Button log=new Button("Zaloguj", a -> login());

    @PostConstruct
    private void init(){
        add(login, password, new HorizontalLayout(log, createAccount));
    }

    private void login(){
        Client client=clientRepo.findByLogin(login.getValue());
        if(client!=null){
            if(client.getPass().equals(password.getValue())){
                userData.setClient(client);
                UI.getCurrent().navigate(FindView.class);
            }
            else
                Notification.show("Nie poprawne hasło!");
        }
        else{
            Notification.show("Nie poprawny login!");
            password.clear();
        }
    }

    private void createUser(){
        UI.getCurrent().navigate(CreateView.class);
    }

}

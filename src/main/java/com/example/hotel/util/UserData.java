package com.example.hotel.util;

import com.example.hotel.model.Client;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.Setter;

@SpringComponent
@UIScope
public class UserData {
    @Getter
    @Setter
    private Client client=null;
}

package com.example.hotel.repository;

import com.example.hotel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByLogin(String login);
}

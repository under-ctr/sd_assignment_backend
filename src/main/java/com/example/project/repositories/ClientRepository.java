package com.example.project.repositories;

import com.example.project.entitys.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {




    Optional<Client> findByNameAndPassword(String name, String password);
}


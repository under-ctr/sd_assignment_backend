package com.example.project.repositories;



import com.example.project.entitys.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<List<Device>>  findByClientId(Long clientId);
}

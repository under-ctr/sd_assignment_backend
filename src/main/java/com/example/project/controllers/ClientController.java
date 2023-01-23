package com.example.project.controllers;

import com.example.project.dto.UserDto;
import com.example.project.entitys.Client;
import com.example.project.entitys.Device;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.DeviceRepository;
import com.example.project.services.ClientService;
import com.example.project.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value = "/client")
@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    public final ClientService clientService;

    public final DeviceService deviceService;
    private final DeviceRepository deviceRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<UserDto> insertClient(@RequestBody Client client) {
        //clientRepository.save(client);
        //UserDto userDto = new UserDto();
        clientService.createClient(client);
        UserDto userDto = new UserDto(client.getId(), client.getName());


        return new ResponseEntity<>( userDto, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.findClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getClient(@PathVariable("id") Long clientId) {
        UserDto dto = clientService.findClientById(clientId);
        //Client client = clientService.fin
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //trebuie pus si id-ul in body ca sa functioneze updateul
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client){

        if(clientService.isPresent(client.getId())) {
            clientService.update(client);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long clientId){
        clientService.delete(clientId);

        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/add/{clientId}/{deviceId}/")
    public void addDevice( @PathVariable("clientId") Long clientId, @PathVariable("deviceId") Long deviceId){
        clientService.addDevice(clientId, deviceId);


    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/removeDevice/{clientId}/{deviceId}/")
    public void removeDevice( @PathVariable("clientId") Long clientId, @PathVariable("deviceId") Long deviceId){
        clientService.removeDevice(clientId, deviceId);


    }
}

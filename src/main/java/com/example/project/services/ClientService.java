package com.example.project.services;

import com.example.project.dto.UserDto;
import com.example.project.dto.builders.ClientBuilder;
import com.example.project.entitys.Client;
import com.example.project.entitys.Device;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.DeviceRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final DeviceService deviceService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         DeviceService deviceService, DeviceRepository deviceRepository){
        this.clientRepository = clientRepository;
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }

    public List<Client> findClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream().collect(Collectors.toList());
    }

    public UserDto findClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);

        }

        return ClientBuilder.toClientDTO(clientOptional.get());
    }

    public boolean isPresent(Long id){
        boolean itIs = clientRepository.findById(id).isPresent();
        if(itIs)
            return true;
        else {
            return false;
        }
    }
    public Client update( Client newInfo) {

        return clientRepository.save(newInfo);

    }

    public void delete (Long clientId){
        clientRepository.deleteById(clientId);
    }



    public Client createClient(Client client) {
        client.setName(client.getName());
        client.setAddress(client.getAddress());
        client.setPassword(client.getPassword());
        client.setRole(client.getRole());

        Client saveClient = clientRepository.save(client);

        return saveClient;
    }

    public void addDevice(Long clientId, Long deviceId){
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Client user = clientOptional.get();
        Device device = deviceService.findDeviceById(deviceId);
        device.setClientId(user.getId());
        device.setAssigned(true);
        deviceRepository.save(device);

    }

    public void removeDevice(Long clientId, Long deviceId){
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Client user = clientOptional.get();
        Device device = deviceService.findDeviceById(deviceId);
        if(user.getId() == device.getClientId()) {
            device.setClientId(null);
            device.setAssigned(false);
            deviceRepository.save(device);
        }
        else{
            LOGGER.error("No such device on client ", clientId);
        }
    }
    public Client findByNameAndPassword(String name, String password){
        Client client = clientRepository.findByNameAndPassword(name, password)
                .orElseThrow(() -> new ResourceNotFoundException("client not found"));

        return client;
    }
   /* public UserDto findClientById(Long id) {
        Optional<Client> prosumerOptional = ClientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            //throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }*/
}

package com.example.project.dto.builders;

import com.example.project.dto.UserDto;
import com.example.project.entitys.Client;

public class ClientBuilder {

    private ClientBuilder(){

    }

    public static UserDto toClientDTO(Client client) {
        return new UserDto(client.getId(), client.getName());
    }

  /*  public static UserDetailsDTO toClientDetailsDTO(Client client) {
        return new UserDetailsDTO( client.getName(), client.getAddress(), client.getRole(), client.getDeviceId());
    }*/
}

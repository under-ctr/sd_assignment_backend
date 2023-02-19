package com.example.project.controllers;



import com.example.project.entitys.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@Controller
public class ChatControler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message") // app/message
    @SendTo("/chatroom/public")
    private Message recivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    private Message recivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/{Username}/private
        return message;
    }
}

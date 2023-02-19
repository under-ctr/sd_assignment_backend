package com.example.project.services;


import com.example.project.entitys.Device;
import com.example.project.entitys.Message;
import com.example.project.entitys.SensorMesurment;
import com.example.project.entitys.Status;
import com.example.project.repositories.DeviceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.project.controllers.ChatControler;

import java.sql.Time;

@Component
public class RabbitLisen {
    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;
    private final SensorMesurmentService sensorMesurmentService;
    ChatControler chatControler;
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitLisen(SimpMessagingTemplate messagingTemplate,DeviceRepository deviceRepository, DeviceService deviceService, SensorMesurmentService sensorMesurmentService) {
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
        this.sensorMesurmentService = sensorMesurmentService;
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "hello")
    public void run(String message) {
        System.out.println(message);
        String[] splitStr = message.trim().split("\\s+");

        String value = splitStr[0];
        String dateTime = splitStr[1].toString();
        //Long deviceId = Long.parseLong(splitStr[2]);
        Long deviceId = Long.valueOf(4);
        Double val = Double.parseDouble(value);

        String[] splitTime = dateTime.trim().split("T");
        String exactTime = splitTime[1];
        //System.out.println(val + " " + deviceId +" " + exactTime);
        Device device = deviceService.findDeviceById(deviceId);//.orElseThrow(IllegalStateException::new);

        if(device.getMaxHourConsumption() < val){
            Message m = new Message("PC","","ai depasit","", Status.MESSAGE);
            notify(m);

            System.out.println("a depasit" + " " + device.getMaxHourConsumption());
            device.setMaxHourConsumption(32);
            deviceRepository.save(device);
            sensorMesurmentService.insertMesurment3(val, exactTime, deviceId);
            /*  Long LongVal = Long.parseLong(value);

            ;*/
            /*SensorMesurment sensorMesurment = new SensorMesurment(val, exactTime, deviceId);
            sensorMesurmentService.insertMesurment(sensorMesurment);*/
        }





    }

    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/message") // app/message
    @SendTo("/chatroom/public")
    public Message notify(@Payload Message message){
        System.out.println("--------------------SENT----------------------");
        //this.messagingTemplate.convertAndSend("/message", message);
        return message;
    }



}
//System.out.println(device.getAddress() + " " + device.getId());
        /*if (device.getMaxHourConsumption() < Float.parseFloat(message)) {
            System.out.println("a depasit" + " " + device.getMaxHourConsumption());*/
//device.setMaxHourConsumption(Float.parseFloat(message));
//SensorMesurment sensorMesurment = new SensorMesurment(val, splitTime[1], deviceId);
//sensorMesurmentService.insertMesurment(sensorMesurment);
       /* DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, timeFormatter);
        long timestamp = Timestamp.valueOf(dateTime).getTime();        long timestamp = Timestamp.valueOf(dateTime).getTime();*/
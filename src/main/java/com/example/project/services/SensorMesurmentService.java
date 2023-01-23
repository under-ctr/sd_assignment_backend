package com.example.project.services;


import com.example.project.entitys.SensorMesurment;
import com.example.project.repositories.SensorMesurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorMesurmentService {

    private final SensorMesurmentRepository sensorMesurmentRepository;

    @Autowired
    public SensorMesurmentService(SensorMesurmentRepository sensorMesurmentRepository) {
        this.sensorMesurmentRepository = sensorMesurmentRepository;
    }


    public SensorMesurment insertMesurment(SensorMesurment mesurmentDto) {
        mesurmentDto.setConsumption(mesurmentDto.getConsumption());
        mesurmentDto.setTimestamp(mesurmentDto.getTimestamp());
        mesurmentDto.setDeviceId(mesurmentDto.getDeviceId());

        SensorMesurment saveMesurment = sensorMesurmentRepository.save(mesurmentDto);

        return saveMesurment;
    }
    public SensorMesurment insertMesurment3(double consumption, String timestamp, Long deviceId) {
        SensorMesurment mesurment = new SensorMesurment(consumption,timestamp, deviceId);


        SensorMesurment saveMesurment = sensorMesurmentRepository.save(mesurment);

        return saveMesurment;
    }
}

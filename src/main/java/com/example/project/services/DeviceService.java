package com.example.project.services;


import com.example.project.entitys.Device;
import com.example.project.repositories.DeviceRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device createDevice(Device device) {
        device.setDescription(device.getDescription());
        device.setMaxHourConsumption(device.getMaxHourConsumption());
        device.setAssigned(device.isAssigned());
        device.setAddress(device.getAddress());
        device.setClientId(device.getClientId());
        Device saveDevice = deviceRepository.save(device);

        return saveDevice;
    }

    public List<Device> findDevice() {
        List<Device> devicetList = deviceRepository.findAll();
        return devicetList.stream().collect(Collectors.toList());
    }

    public Device findDeviceById(Long id) {
        Device deviceOptional = deviceRepository.findById(id).orElseThrow(IllegalStateException::new);

        return deviceOptional;
    }

    /*public boolean isPresent(Long id){
        boolean itIs = clientRepository.findById(id).isPresent();
        if(itIs)
            return true;
        else {
            return false;
        }
    }*/
    public Device update( Device newInfo) {

        return deviceRepository.save(newInfo);

    }

    public void delete (Long deviceId){
        deviceRepository.deleteById(deviceId);
    }

    public List<Device> getDeviceByClientId(Long clientId){
        List<Device> devices = deviceRepository.findByClientId(clientId).orElseThrow();

        return devices;
    }
}
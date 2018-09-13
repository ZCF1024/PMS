package com.company.pms.pmsservice.device.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.device.domain.Device;
import com.company.pms.pmsrepository.device.repository.DeviceRepository;
import com.company.pms.pmsservice.device.DeviceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceManagerImpl extends GenericManagerImpl<Device, Long> implements DeviceManager {

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.repository = this.deviceRepository;
    }

}

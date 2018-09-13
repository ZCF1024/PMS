package com.company.pms.controller.device;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.device.domain.Device;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsservice.device.DeviceManager;
import com.company.pms.pmsservice.house.HouseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/device")
public class DeviceAPIController extends GenericController<Device, Long, DeviceManager> {

    private final Logger logger = LoggerFactory.getLogger(DeviceAPIController.class);

    private DeviceManager deviceManager;

    @GetMapping("index")
    public String index() {
        return "Device Index";
    }

    @Autowired
    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
        this.manager = this.deviceManager;
    }
}

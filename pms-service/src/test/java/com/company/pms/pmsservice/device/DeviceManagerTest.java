package com.company.pms.pmsservice.device;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.device.domain.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceManagerTest {

    private DeviceManager deviceManager;

    @Test
    public void generator() {
        List<Device> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Device device = new Device();
            device.setName("name_" + i);
            device.setAddress("address_" + i);
            device.setState(i % 2 == 0 ? true : false);
            device.setEmployeeId((long) i);
            device.setHouseId((long) i);
            list.add(device);
        }
        this.deviceManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<Device> list = this.deviceManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Device> exportExcelUtil = new ExportExcelUtil<Device>();
        exportExcelUtil.exportExcel("设备信息", FieldUtils.DEVICE_FIELDS, list, out, null);
    }

    @Autowired
    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }
}
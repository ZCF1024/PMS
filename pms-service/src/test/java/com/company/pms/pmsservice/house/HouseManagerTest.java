package com.company.pms.pmsservice.house;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.house.domain.House;

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
public class HouseManagerTest {

    private HouseManager houseManager;

    @Test
    public void generator() {
        List<House> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            House house = new House();
            house.setPrice(i * 100.5);
            house.setAddress("address_" + i);
            house.setArea(i * 4.5);
            house.setState(i % 2 == 0 ? false : true);
            house.setCustomerId((long) i);
            house.setCommunity("瑞景河畔小区");
            house.setBuildingNumber(i);
            house.setFloorNumber(i);
            house.setHouseNumber(i);
            house.setHouseType("两室一厅一卫");
            list.add(house);
        }
        this.houseManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<House> list = this.houseManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<House> exportExcelUtil = new ExportExcelUtil<House>();
        exportExcelUtil.exportExcel("房屋信息", FieldUtils.HOUSE_FIELDS, list, out, null);
    }

    @Autowired
    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
    }
}
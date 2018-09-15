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
        for(int j = 0; j < 5; j++){
            for(int k = 0; k < 20; k++){
                for (int i = 0; i < 30; i++) {
                    House house = new House();
                    house.setPrice(i * 100.5);
                    house.setAddress("address_" + i);
                    house.setArea(i * 4.5);
                    house.setState(false);
                    if(i % 2 == 1){
                        house.setState(true);
                        house.setCustomerId((long) i);
                    }
                    switch (j){
                        case 0: house.setCommunity("瑞景河畔小区"); break;
                        case 1: house.setCommunity("碧桂园小区"); break;
                        case 2: house.setCommunity("景秀园小区"); break;
                        case 3: house.setCommunity("蔚蓝小区"); break;
                        case 4:house.setCommunity("合胜园小区"); break;
                    }
                    house.setBuildingNumber(k);
                    house.setFloorNumber(i);
                    house.setHouseNumber(100 + i);
                    int type = i % 4;
                    switch (type){
                        case 0: house.setHouseType("两室一厅一卫"); break;
                        case 1: house.setHouseType("三室一厅二卫"); break;
                        case 2: house.setHouseType("一室一厅一卫"); break;
                        case 3: house.setHouseType("两室一厅二卫"); break;
                        case 4:house.setHouseType("三室一厅三卫"); break;
                    }
                    list.add(house);
                }
            }
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
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
        for(int community = 0; community < 5; community++){
            for(int building = 1; building < 20; building++){
                for(int floor = 1; floor < 20; floor++){
                    for (int i = 0; i <= 8; i++) {
                        House house = new House();
                        house.setPrice(i * 100.5);
                        house.setAddress("address_" + i);
                        house.setArea(i * 4.5);
                        int state = i % 5;
                        house.setState(state);
                        if (state == 1 || state == 3){
                            house.setCustomerId((long) (100 * floor + (i/3 + 1) * 10 + (i%3 + 1)));
                        }
                        switch (community){
                            case 0: house.setCommunity("瑞景河畔小区"); break;
                            case 1: house.setCommunity("碧桂园小区"); break;
                            case 2: house.setCommunity("景秀园小区"); break;
                            case 3: house.setCommunity("蔚蓝小区"); break;
                            case 4: house.setCommunity("合胜园小区"); break;
                            default: break;
                        }
                        house.setBuildingNumber(building);
                        house.setFloorNumber(i);
                        // 每层有三个单元， 每个单元 3 户
                        //房号 = 楼层号 + 单元号 + 住户号
                        house.setHouseNumber(100 * floor + (i/3 + 1) * 10 + (i%3 + 1));
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
package com.company.pms.pmsservice.charge;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.charge.domain.Charge;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargeManagerTest {

    private ChargeManager chargeManager;

    @Test
    public void testFindAllByHouseId() {
        List<Charge> list = this.chargeManager.findAllByHouseId(1);
        for(Charge charge : list){
            System.out.println("id: " + charge.getId() + " houseId: " + charge.getHouseId());
        }
    }

    @Test
    public void testFindAllByDateCreatedBetween() {
        List<Charge> list = this.chargeManager.findAllByDateCreatedBetween(DateUtils.addHours(new Date(), -1), new Date());
        for(Charge charge : list){
            System.out.println("id: " + charge.getId() + " date: " + charge.getDateCreated());
        }
    }

    @Test
    public void testFindAllByHouseIdAndDateCreatedBetween() {
        List<Charge> list = this.chargeManager.findAllByHouseIdAndDateCreatedBetween(1, DateUtils.addHours(new Date(), -1), new Date());
        for(Charge charge : list){
            System.out.println("id: "+ charge.getId() + " houseId: " + charge.getHouseId() + " date: " + charge.getDateCreated());
        }
    }

    @Test
    public void testFindAllByState() {
        List<Charge> list = this.chargeManager.findAllByState(false);
        for(Charge charge : list){
            System.out.println("id: " + charge.getId() + " state: " + charge.getState());
        }
    }

    @Test
    public void testUpdateDeteled() {
        this.chargeManager.updateDeteled(1, true);
    }

    @Test
    public void generator() {
        List<Charge> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Charge charge = new Charge();
            charge.setChargeOfElectric((double) 0);
            charge.setConsumOfElectric(i * 100.5);
            charge.setChargeOfWater((double) 0);
            charge.setConsumOfWater(i * 1000.5);
            charge.setChargeOfProperty((double) ((i % 10) * 100));
            charge.setHouseId((long) i);
            charge.setState(i % 2 == 0 ? false : true);
            list.add(charge);
        }
        this.chargeManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<Charge> list = this.chargeManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Charge> exportExcelUtil = new ExportExcelUtil<Charge>();
        exportExcelUtil.exportExcel("缴费信息", FieldUtils.CHARGE_FIELDS, list, out, null);
    }

    @Autowired
    public void setChargeManager(ChargeManager chargeManager) {
        this.chargeManager = chargeManager;
    }
}
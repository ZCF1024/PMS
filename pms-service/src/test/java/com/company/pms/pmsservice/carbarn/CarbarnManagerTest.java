package com.company.pms.pmsservice.carbarn;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;

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
public class CarbarnManagerTest {

    private CarbarnManager carbarnManager;

    @Test
    public void testFindAllByCustomerId() {
        List<Carbarn> list = this.carbarnManager.findAllByCustomerId(2);
        for(Carbarn carbarn : list){
            System.out.println("id: " + carbarn.getId() + " customer_id: " + carbarn.getCustomerId());
        }
    }

    @Test
    public void testFindAllByPriceBetween() {
        List<Carbarn> list = this.carbarnManager.findAllByPriceBetween(100, 200);
        for(Carbarn carbarn : list){
            System.out.println("id: " + carbarn.getId() + " price: " + carbarn.getPrice());
        }
    }

    @Test
    public void testFindAllByAddressLike() {
        List<Carbarn> list = this.carbarnManager.findAllByAddressLike("address_1");
        for(Carbarn carbarn : list){
            System.out.println("id: " + carbarn.getId() + " address: " + carbarn.getAddress());
        }
    }

    @Test
    public void testFindAllByState() {
        List<Carbarn> list = this.carbarnManager.findAllByState(true);
        for(Carbarn carbarn : list){
            System.out.println("id: " + carbarn.getId() + " state: " + carbarn.getState());
        }
    }

    @Test
    public void testUpdateDeleted() {
        this.carbarnManager.updateDeleted((long) 1, true);
        this.carbarnManager.updateState(3, true);
    }

    @Test
    public void generator() {
        List<Carbarn> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Carbarn carbarn = new Carbarn();
            carbarn.setPrice(i * 100.5);
            carbarn.setAddress("address_" + i);
            carbarn.setArea(i * 4.5);
            carbarn.setState(i % 2 == 0 ? false : true);
            carbarn.setCustomerId((long) i);
            carbarn.setIntroduce("introduce_" + i);
            list.add(carbarn);
        }
        this.carbarnManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<Carbarn> list = this.carbarnManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Carbarn> exportExcelUtil = new ExportExcelUtil<Carbarn>();
        exportExcelUtil.exportExcel("车库信息", FieldUtils.CARBARN_FIELDS, list, out,null);
    }

    @Autowired
    public void setCarbarnManager(CarbarnManager carbarnManager) {
        this.carbarnManager = carbarnManager;
    }
}
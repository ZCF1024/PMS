package com.company.pms.pmsservice.complain;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.complain.domain.Complain;

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
public class ComplainManagerTest {

    private ComplainManager complainManager;

    @Test
    public void generator() {
        List<Complain> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Complain complain = new Complain();
            complain.setCustomerId((long) i);
            complain.setEmployeeId((long) i);
            complain.setDeviceId((long) i);
            complain.setReason("心情不好");
            list.add(complain);
        }
        this.complainManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<Complain> list = this.complainManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Complain> exportExcelUtil = new ExportExcelUtil<Complain>();
        exportExcelUtil.exportExcel("投诉信息", FieldUtils.COMPLAIN_FIELDS, list, out, null);
    }

    @Autowired
    public void setComplainManager(ComplainManager complainManager) {
        this.complainManager = complainManager;
    }

}
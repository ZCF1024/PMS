package com.company.pms.controller.carbarn;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;
import com.company.pms.pmsservice.carbarn.CarbarnManager;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/carbarn")
public class CarbarnAPIController extends GenericController<Carbarn, Long, CarbarnManager> {

    private final Logger logger = LoggerFactory.getLogger(CarbarnAPIController.class);

    private CarbarnManager carbarnManager;

    @GetMapping("price")
    public List<Carbarn> testFindAllByPriceBetween() {
        return this.carbarnManager.findAllByPriceBetween(100, 200);
    }

    @GetMapping("export")
    public ResponseEntity<byte[]> export(@RequestParam(name = "fields") String fields){

        String[] tmp= fields.split(",");
        List<Integer> fieldList = new ArrayList<>();
        for(String index : tmp){
            fieldList.add(Integer.valueOf(index));
        }
        try{
            List<Carbarn> list = this.carbarnManager.findAll(false);
            String fileName = SAVE_PATH + "车库信息表.xls";
            OutputStream out = new FileOutputStream(new File(fileName));

            ExportExcelUtil<Carbarn> exportExcel = new ExportExcelUtil<>();
            exportExcel.exportExcel("车库信息", FieldUtils.CARBARN_FIELDS, list, out, fieldList);

            //将导出的文件输出到响应的信息中（即为下载）
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 设置响应方式

            headers.setContentDispositionFormData("attachment",
                    URLEncoder.encode("车库信息表.xls", "UTF-8")); // 设置响应文件

            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileName)),
                    headers, HttpStatus.CREATED); // 把文件以二进制形式写回
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Autowired
    public void setCarbarnManager(CarbarnManager carbarnManager) {
        this.carbarnManager = carbarnManager;
        this.manager = this.carbarnManager;
    }
}

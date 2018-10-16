package com.company.pms.controller.complain;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.complain.domain.Complain;
import com.company.pms.pmsservice.complain.ComplainManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/complain")
public class ComplainAPIController  extends GenericController<Complain, Long, ComplainManager> {

    private final Logger logger = LoggerFactory.getLogger(ComplainAPIController.class);

    private ComplainManager complainManager;

    @GetMapping("index")
    public String index() {
        return "Complain Index";
    }

    @GetMapping("export")
    public ResponseEntity<byte[]> export(@RequestParam(name = "fields") String fields){

        String[] tmp= fields.split(",");
        List<Integer> fieldList = new ArrayList<>();
        for(String index : tmp){
            fieldList.add(Integer.valueOf(index));
        }
        try{
            List<Complain> list = this.complainManager.findAll(false);
            String fileName = SAVE_PATH + "投诉信息表.xls";
            OutputStream out = new FileOutputStream(new File(fileName));

            ExportExcelUtil<Complain> exportExcel = new ExportExcelUtil<>();
            exportExcel.exportExcel("投诉信息", FieldUtils.COMPLAIN_FIELDS, list, out, fieldList);

            //将导出的文件输出到响应的信息中（即为下载）
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 设置响应方式

            headers.setContentDispositionFormData("attachment",
                    URLEncoder.encode("投诉信息表.xls", "UTF-8")); // 设置响应文件

            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileName)),
                    headers, HttpStatus.CREATED); // 把文件以二进制形式写回
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
    
    @Autowired
    public void setComplainManager(ComplainManager complainManager) {
        this.complainManager = complainManager;
        this.manager = this.complainManager;
    }

}

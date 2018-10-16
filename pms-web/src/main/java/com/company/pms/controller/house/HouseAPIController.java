package com.company.pms.controller.house;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsservice.house.HouseManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/house")
public class HouseAPIController extends GenericController<House, Long, HouseManager> {

    private final Logger logger = LoggerFactory.getLogger(HouseAPIController.class);

    private HouseManager houseManager;

    @Value("${page_size}")
    private Integer pageSize;

    @GetMapping("index")
    public String index() {
        return "House Index";
    }

    @PostMapping("gethousebybuilding")
    public Map<String, Object> getHouses(String community, Integer buildingNumber){
        Map<String, Object> result = new HashMap<>();
        result.put("max", this.houseManager.getMaxFloorNumber(community, buildingNumber));
        result.put("house", this.houseManager.getHouseLocations(community, buildingNumber));
        return result;
    }

    @PostMapping("building")
    public List<Integer> building(String community){
        return this.houseManager.getBuildingNumbers(community);
    }

    @PostMapping("search/{page}")
    public Page<House> search(@RequestBody House house, @PathVariable Integer page){
        return this.houseManager.findAll(page, pageSize, house);
    }

    @PostMapping("delete")
    public Integer updateDelete(Long id) throws IOException {
        return this.houseManager.deleteOne(id);
    }

    @GetMapping("export")
    public ResponseEntity<byte[]> export(@RequestParam(name = "fields") String fields){

        String[] tmp= fields.split(",");
        List<Integer> fieldList = new ArrayList<>();
        for(String index : tmp){
            fieldList.add(Integer.valueOf(index));
        }
        try{
            List<House> list = this.houseManager.findAll(false);
            String fileName = SAVE_PATH + "房屋信息表.xls";
            OutputStream out = new FileOutputStream(new File(fileName));

            ExportExcelUtil<House> exportExcel = new ExportExcelUtil<>();
            exportExcel.exportExcel("房屋信息", FieldUtils.HOUSE_FIELDS, list, out, fieldList);

            //将导出的文件输出到响应的信息中（即为下载）
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 设置响应方式

            headers.setContentDispositionFormData("attachment",
                    URLEncoder.encode("房屋信息表.xls", "UTF-8")); // 设置响应文件

            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileName)),
                    headers, HttpStatus.CREATED); // 把文件以二进制形式写回
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Autowired
    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
        this.manager = this.houseManager;
    }
}

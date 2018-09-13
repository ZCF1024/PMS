package com.company.pms.controller.carbarn;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;
import com.company.pms.pmsservice.carbarn.CarbarnManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setCarbarnManager(CarbarnManager carbarnManager) {
        this.carbarnManager = carbarnManager;
        this.manager = this.carbarnManager;
    }
}

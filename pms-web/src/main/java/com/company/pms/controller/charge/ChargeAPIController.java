package com.company.pms.controller.charge;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.charge.domain.Charge;
import com.company.pms.pmsservice.charge.ChargeManager;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/charge")
public class ChargeAPIController extends GenericController<Charge, Long, ChargeManager> {

    private final Logger logger = LoggerFactory.getLogger(ChargeAPIController.class);

    private ChargeManager chargeManager;

    @GetMapping("index")
    public String index() {
        return "Charge Index";
    }

    @GetMapping("state")
    public List<Charge> testFindAllByState() {
        return this.chargeManager.findAllByState(false);
    }

    @Autowired
    public void setChargeManager(ChargeManager chargeManager) {
        this.chargeManager = chargeManager;
        this.manager = this.chargeManager;
    }
}

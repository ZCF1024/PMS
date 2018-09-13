package com.company.pms.controller.complain;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.complain.domain.Complain;
import com.company.pms.pmsservice.complain.ComplainManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/complain")
public class ComplainAPIController  extends GenericController<Complain, Long, ComplainManager> {

    private final Logger logger = LoggerFactory.getLogger(ComplainAPIController.class);

    private ComplainManager complainManager;

    @GetMapping("index")
    public String index() {
        return "Complain Index";
    }

    @Autowired
    public void setComplainManager(ComplainManager complainManager) {
        this.complainManager = complainManager;
        this.manager = this.complainManager;
    }

}

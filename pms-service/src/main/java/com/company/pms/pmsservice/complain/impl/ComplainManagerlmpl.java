package com.company.pms.pmsservice.complain.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.complain.domain.Complain;
import com.company.pms.pmsrepository.complain.repository.ComplainRepository;
import com.company.pms.pmsservice.complain.ComplainManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplainManagerlmpl extends GenericManagerImpl<Complain, Long> implements ComplainManager {

    private ComplainRepository complainRepository;

    @Autowired
    public void setComplainRepository(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
        this.repository = this.complainRepository;
    }

}

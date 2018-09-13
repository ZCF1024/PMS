package com.company.pms.pmsservice.charge.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.charge.domain.Charge;
import com.company.pms.pmsrepository.charge.repository.ChargeRepository;
import com.company.pms.pmsservice.charge.ChargeManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ChargeManagerImpl extends GenericManagerImpl<Charge, Long> implements ChargeManager {

    private ChargeRepository chargeRepository;

    @Override
    public List<Charge> findAllByHouseId(long houseId) {
        return this.chargeRepository.findAllByHouseIdAndDeleted(houseId, false);
    }

    @Override
    public List<Charge> findAllByDateCreatedBetween(Date date1, Date date2) {
        return this.chargeRepository.findAllByDateCreatedBetweenAndDeleted(date1, date2, false);
    }

    @Override
    public List<Charge> findAllByHouseIdAndDateCreatedBetween(long houseId, Date date1, Date date2) {
        return this.chargeRepository.findAllByHouseIdAndDateCreatedBetweenAndDeleted(houseId, date1, date2, false);
    }

    @Override
    public List<Charge> findAllByState(boolean state) {
        return this.chargeRepository.findAllByStateAndDeleted(state, false);
    }

    @Override
    @Transactional
    public void updateDeteled(long id, boolean deleted) {
        this.chargeRepository.updateDeteled(id, deleted);
    }

    @Autowired
    public void setChargeRepository(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
        this.repository = this.chargeRepository;
    }

}

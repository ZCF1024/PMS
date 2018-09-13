package com.company.pms.pmsservice.carbarn.impl;

import java.util.List;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;
import com.company.pms.pmsrepository.carbarn.repository.CarbarnRepository;

import com.company.pms.pmsservice.carbarn.CarbarnManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarbarnManagerImpl extends GenericManagerImpl<Carbarn, Long> implements CarbarnManager {

    private CarbarnRepository carbarnRepository;

    @Override
    public List<Carbarn> findAllByCustomerId(long customerId) {
        return this.carbarnRepository.findAllByCustomerIdAndDeleted(customerId, false);
    }

    @Override
    public List<Carbarn> findAllByPriceBetween(double lowest, double highest) {
        return this.carbarnRepository.findAllByPriceBetweenAndDeleted(lowest, highest, false);
    }

    @Override
    public List<Carbarn> findAllByAddressLike(String address) {
        return this.carbarnRepository.findAllByAddressLikeAndDeleted("%" + address + "%", false);
    }

    @Override
    public List<Carbarn> findAllByState(boolean state) {
        return this.carbarnRepository.findAllByStateAndDeleted(state, false);
    }

    @Override
    @Transactional
    public void updateDeleted(long id, boolean deleted) {
        this.carbarnRepository.updateDeleted(id, deleted);
    }

    @Override
    @Transactional
    public void updateState(long id, boolean state) {
        this.carbarnRepository.updateState(id, state);
    }

    @Autowired
    public void setCarbarnRepository(CarbarnRepository carbarnRepository) {
        this.carbarnRepository = carbarnRepository;
        this.repository = this.carbarnRepository;
    }
}

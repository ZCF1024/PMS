package com.company.pms.pmsservice.house.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsrepository.house.repository.HouseRepository;
import com.company.pms.pmsservice.house.HouseManager;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseManagerImpl extends GenericManagerImpl<House, Long> implements HouseManager {

    private HouseRepository houseRepository;

    @Override
    public Page<House> findAll(Integer page, Integer size, House house) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "dateCreated");
        Page<House> housePage = houseRepository.findAll(new Specification<House>() {
            @Override
            public Predicate toPredicate(Root<House> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null != house.getCommunity() && !"".equals(house.getCommunity())){
                    list.add(criteriaBuilder.equal(root.get("community").as(String.class), house.getCommunity()));
                }
                if(null != house.getBuildingNumber() && -999 != house.getBuildingNumber()){
                    list.add(criteriaBuilder.equal(root.get("buildingNumber").as(Integer.class), house.getBuildingNumber()));
                }
                if(null != house.getHouseType() && !"".equals(house.getHouseType())){
                    list.add(criteriaBuilder.equal(root.get("houseType").as(String.class), house.getHouseType()));
                }
                if(null != house.getHouseNumber()){
                    list.add(criteriaBuilder.equal(root.get("houseNumber").as(Integer.class), house.getHouseNumber()));
                }
                if(null != house.getState()){
                    list.add(criteriaBuilder.equal(root.get("state").as(Boolean.class), house.getState()));
                }
                list.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));
                Predicate[] predicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(predicates));
            }
        }, pageable);
        return housePage;
    }

    @Override
    public List<String> getCommunities() {
        return this.houseRepository.getCommunities();
    }

    @Override
    public List<Integer> getBuildingNumbers(String community) {
        return this.houseRepository.getBuildingNumbers(community);
    }

    @Override
    public List<Integer> getFloorNumbers(String community, Integer buildingNumber) {
        return this.houseRepository.getFloorNumbers(community, buildingNumber);
    }

    @Override
    public List<String> getHouseTypes() {
        return this.houseRepository.getHouseTypes();
    }

    @Autowired
    public void setHouseDao(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
        this.repository = this.houseRepository;
    }

}

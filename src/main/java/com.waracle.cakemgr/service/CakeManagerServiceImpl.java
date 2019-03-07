package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.repo.CakeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CakeManagerServiceImpl implements CakeManagerService {

    @Autowired
    private CakeRepo cakeRepo;

    public List<Cake> getAllCakes() {
        return cakeRepo.findAll();
    }

    public void saveCake(Cake cake) {
        cakeRepo.saveAndFlush(cake);
    }
}

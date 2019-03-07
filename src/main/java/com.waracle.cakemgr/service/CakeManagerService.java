package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.Cake;

import java.util.List;

public interface CakeManagerService {

    List<Cake> getAllCakes();

    void saveCake(Cake cake);

}

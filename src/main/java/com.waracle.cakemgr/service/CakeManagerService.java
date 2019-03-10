package com.waracle.cakemgr.service;

import com.waracle.cakemgr.representation.CakeDto;

import java.util.List;

public interface CakeManagerService {

    List<CakeDto> getAllCakes();

    void saveCake(CakeDto cakeDto);

}

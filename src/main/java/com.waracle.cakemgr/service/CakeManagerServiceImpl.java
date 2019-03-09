package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.repo.CakeRepo;
import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.representation.CakeToCakeDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CakeManagerServiceImpl implements CakeManagerService {

    @Autowired
    private CakeRepo cakeRepo;

    public List<CakeDto> getAllCakes() {
        return cakeRepo.findAll().stream().map(c -> CakeToCakeDtoMapper.INSTANCE.CakeToCakeDto(c)).collect(Collectors.toList());
    }

    public void saveCake(CakeDto cakeDto) {
        Cake cake = CakeToCakeDtoMapper.INSTANCE.CakeDtoToCake(cakeDto);
        cakeRepo.saveAndFlush(cake);
    }
}

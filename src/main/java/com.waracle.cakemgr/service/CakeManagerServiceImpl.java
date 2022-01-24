package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.repo.CakeRepo;
import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.representation.CakeToCakeDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeManagerServiceImpl implements CakeManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeManagerServiceImpl.class);
    @Autowired
    private CakeRepo cakeRepo;

    public List<CakeDto> getAllCakes() {
        LOGGER.info("Getting all cakes from repo");
        return cakeRepo.findAll().stream().map(c -> CakeToCakeDtoMapper.INSTANCE.CakeToCakeDto(c)).collect(Collectors.toList());
    }

    public void saveCake(CakeDto cakeDto) {
        LOGGER.info("Converting dto to entity");
        Cake cake = CakeToCakeDtoMapper.INSTANCE.CakeDtoToCake(cakeDto);
        LOGGER.info("Saving entity to repo");
        cakeRepo.saveAndFlush(cake);
    }
}

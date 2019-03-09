package com.waracle.cakemgr.representation;

import com.waracle.cakemgr.entity.Cake;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface CakeToCakeDtoMapper {

    CakeToCakeDtoMapper INSTANCE = Mappers.getMapper(CakeToCakeDtoMapper.class);

    @Mapping(source="desc", target="description")
    CakeDto CakeToCakeDto(Cake cake);

    @InheritInverseConfiguration
    Cake CakeDtoToCake(CakeDto cakeDto);

}

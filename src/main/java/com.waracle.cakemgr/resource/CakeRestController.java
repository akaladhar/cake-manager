package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.Assert.notNull;

@Api(value = "cakes", produces = "application/json", consumes = "application/json")
@RestController
@RequestMapping(value = "/cakes")
public class CakeRestController {

    @Autowired
    private CakeManagerService cakeManagerService;

    @RequestMapping(method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CakeDto> getCakes() {
        return cakeManagerService.getAllCakes();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewCake(@RequestBody CakeDto cakeDto) {
        assertCake(cakeDto);
        cakeManagerService.saveCake(cakeDto);
        return new ResponseEntity<>(
                "Cake created successfully",
                HttpStatus.CREATED);
    }

    private void assertCake(CakeDto cakeDto) {
        notNull(cakeDto, "Cake can not be empty");
        notNull(cakeDto.getTitle(), "Title can not be empty");
    }
}

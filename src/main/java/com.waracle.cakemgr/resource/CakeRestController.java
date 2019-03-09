package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeRestController.class);

    @Autowired
    private CakeManagerService cakeManagerService;

    @RequestMapping(method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CakeDto> getCakes() {

        LOGGER.info("Request received to get list of all cakes");
        return cakeManagerService.getAllCakes();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewCake(@RequestBody CakeDto cakeDto) {
        LOGGER.info("Request received to add new cake ");
        assertCake(cakeDto);
        cakeManagerService.saveCake(cakeDto);
        LOGGER.info("Cake saved successfully ");
        return new ResponseEntity<>(
                "Cake created successfully",
                HttpStatus.CREATED);
    }

    private void assertCake(CakeDto cakeDto) {
        LOGGER.info("Validating new cake info ");
        notNull(cakeDto, "Cake can not be empty");
        notNull(cakeDto.getTitle(), "Title can not be empty");
    }
}

package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.service.CakeManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "cakes", produces = "application/json", consumes = "application/json")
@RestController
@RequestMapping(value = "/cakes")
public class CakeRestController {

    @Autowired
    private CakeManagerService cakeManagerService;

    @RequestMapping(method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Cake> getCakes() {
        return cakeManagerService.getAllCakes();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewCake(@RequestBody Cake cake) {
        assertCake(cake);
        cakeManagerService.saveCake(cake);
        return new ResponseEntity<>(
                "Cake created successfully",
                HttpStatus.CREATED);
    }

    private void assertCake(Cake cake) {
        assert cake != null: "No info is present";
        assert cake.getTitle() != null: "Cake title can not be empty";
        assert cake.getDesc() != null: "Cake description can not be empty";
        assert cake.getImage() != null: "Cake image is not present";
    }
}

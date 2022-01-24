package com.waracle.cakemgr.resource;

import static org.springframework.util.Assert.notNull;

import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import io.swagger.annotations.Api;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
This controller serves REST end points
 */

@Api(value = "cakes", produces = "application/json", consumes = "application/json")
@RestController
@RequestMapping(value = "/cakes")
public class CakeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeRestController.class);

    @Autowired
    private CakeManagerService cakeManagerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CakeDto> getAllCakes() {

        LOGGER.info("Request received to get list of all cakes");
        return cakeManagerService.getAllCakes();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewCake(@RequestBody CakeDto cakeDto) throws MalformedURLException {
        LOGGER.info("Request received to add new cake ");
        assertCake(cakeDto);
        cakeManagerService.saveCake(cakeDto);
        final String result = "Cake created successfully";
        LOGGER.info(result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    private void assertCake(CakeDto cakeDto) throws MalformedURLException  {
        LOGGER.info("Validating new cake info ");
        notNull(cakeDto, "Cake can not be empty");
        notNull(cakeDto.getTitle(), "Title can not be empty");
        if (cakeDto.getImage() != null) {
            URL url = new URL(cakeDto.getImage());
        }
    }
}

package com.waracle.cakemgr.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.Application;
import com.waracle.cakemgr.representation.CakeDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * This is integration test class for REST controller end points
 */

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CakeRestControllerApplicationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetAllCakesRestEndpoint() throws Exception {

        List<CakeDto> cakeDtoList = getNumberOfCakesListed();
        assertTrue("Response is not correct",  cakeDtoList.size() >= 20);
    }

    @Test
    public void givenAnewCakeIsPosted_TotalCakeCountIncreasedByOne() throws Exception {

        List<CakeDto> preSaveList = getNumberOfCakesListed();

        CakeDto cakeDto = CakeDto.builder().title("My new Cake").description("Yummy").image("http://instagram.com").build();
        ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/cakes"),
                cakeDto, String.class);

        String result = response.getBody();

        assertEquals("Response is not correct", "Cake created successfully", result);
        assertEquals( HttpStatus.CREATED, response.getStatusCode());

        List<CakeDto> postSaveList = getNumberOfCakesListed();
        assertTrue("Response is not correct",  postSaveList.size() == preSaveList.size() + 1);

        assertTrue("List contains saved cake", postSaveList.stream().filter(c-> c.getTitle().equalsIgnoreCase("My new Cake")).count() == 1);

    }


    private List<CakeDto> getNumberOfCakesListed() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/cakes"),
                HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();

        List<CakeDto> cakesList = new ObjectMapper().readValue(responseBody, new TypeReference<List<CakeDto>>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        return cakesList;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}



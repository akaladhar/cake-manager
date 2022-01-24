package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;


public class CakeRestControllerTest {

    @Mock
    private CakeManagerService cakeManagerService;

    @InjectMocks
    private CakeRestController cakeRestController;

    @Test
    public void testGetAllCakes() {
        CakeDto cakeDto = CakeDto.builder().title("My new Cake").description("Yummy").image("instagram.com").build();

        List<CakeDto> cakeDtoList = Arrays.asList(cakeDto);
        when(cakeManagerService.getAllCakes()).thenReturn(cakeDtoList);

        List<CakeDto> cakeDtoListResult = cakeRestController.getAllCakes();
        assertTrue("Cake returned correctly", cakeDtoListResult.get(0).getTitle().equals("My new Cake"));
    }

    @Test
    public void testAddNewCake() throws Exception {
        CakeDto cakeDto = CakeDto.builder().title("My new Cake").description("Yummy").image("http://instagram.com").build();
        doNothing().when(cakeManagerService).saveCake(any(CakeDto.class));
        ResponseEntity<String> response = cakeRestController.addNewCake(cakeDto);

        assertEquals(response.getBody(), "Cake created successfully");
    }

    @Test
    public void testGivenInvalidNewCake_shouldReturnBadRequest() throws Exception {
        CakeDto cakeDto = CakeDto.builder().description("Yummy").image("instagram.com").build();
        cakeRestController.addNewCake(cakeDto);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cakeRestController.addNewCake(cakeDto);
        });
    }


    public void testGivenInvalidImageURL_throwMalformedURLException() throws Exception {
        CakeDto cakeDto = CakeDto.builder().title("test").description("Yummy").image("instagram.com").build();

        Exception exception = assertThrows(MalformedURLException.class, () -> {
            cakeRestController.addNewCake(cakeDto);
        });
    }

}

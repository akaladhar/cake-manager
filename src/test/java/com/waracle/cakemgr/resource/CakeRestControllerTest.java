package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
    public void testAddNewCake() {
        CakeDto cakeDto = CakeDto.builder().title("My new Cake").description("Yummy").image("instagram.com").build();
        doNothing().when(cakeManagerService).saveCake(any(CakeDto.class));
        ResponseEntity<String> response = cakeRestController.addNewCake(cakeDto);

        assertEquals(response.getBody(), "Cake created successfully");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGivenInvalidNewCake_shouldReturnBadRequest() {
        CakeDto cakeDto = CakeDto.builder().description("Yummy").image("instagram.com").build();
        cakeRestController.addNewCake(cakeDto);


    }

}

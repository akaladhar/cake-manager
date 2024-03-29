package com.waracle.cakemgr.resource;

import com.waracle.cakemgr.representation.CakeDto;
import com.waracle.cakemgr.service.CakeManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/*
This is integration test for CakeController class
 */

public class CakeControllerTest {
    @Mock
    private CakeManagerService cakeManagerService;

    @InjectMocks
    private CakeController cakeController;

    @Test
    public void testGetAllCakes() {
        CakeDto cakeDto = CakeDto.builder().title("My new Cake").description("Yummy").image("instagram.com").build();

        List<CakeDto> cakeDtoList = Arrays.asList(cakeDto);
        when(cakeManagerService.getAllCakes()).thenReturn(cakeDtoList);

        Model model = new ExtendedModelMap();


        String result = cakeController.getListOfCakes(model);

        assertTrue("Cakes attribute not present", model.containsAttribute("cakes"));

        assertTrue("Cakes attribute not present", result.equals("cakes"));

    }

}

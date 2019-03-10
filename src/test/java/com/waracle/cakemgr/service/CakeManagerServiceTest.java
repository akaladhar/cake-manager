package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.repo.CakeRepo;
import com.waracle.cakemgr.representation.CakeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CakeManagerServiceTest {

    @Mock
    private CakeRepo cakeRepo;

    @InjectMocks
    private CakeManagerServiceImpl cakeManagerService;

    @Test
    public void testSaveCake() {

        //It's a silly test
        final CakeDto cakeDto = new CakeDto();
        cakeDto.setImage("test");
        cakeDto.setTitle("Title");
        cakeDto.setDescription("Desc");

        Cake cake = new Cake();

        when(cakeRepo.saveAndFlush(argThat(new ArgumentMatcher<Cake>() {
            @Override
            public boolean matches(Cake argument) {
                return argument.getImage().equals(cakeDto.getImage()) &&
                        argument.getDesc().equals(cakeDto.getDescription()) &&
                        argument.getTitle().equals(cakeDto.getTitle());
            }
        }))).thenReturn(cake);

        cakeManagerService.saveCake(cakeDto);

        verify(cakeRepo, times(1)).saveAndFlush(argThat(new ArgumentMatcher<Cake>() {
            @Override
            public boolean matches(Cake argument) {
                return argument.getImage().equals(cakeDto.getImage()) &&
                        argument.getDesc().equals(cakeDto.getDescription()) &&
                        argument.getTitle().equals(cakeDto.getTitle());
            }
        }));
    }

    @Test
    public void testFindAllCakes() {

        final List<Cake> cakes = new ArrayList<>();
        final Cake cake = new Cake();
        cake.setImage("test");
        cake.setTitle("Title");
        cake.setDesc("Desc");
        cakes.add(cake);
        when(cakeRepo.findAll()).thenReturn(cakes);

        List<CakeDto> result = cakeManagerService.getAllCakes();

        assertEquals("List size must be 1", 1, result.size());
        assertTrue("Cake properties do not match", result.get(0).getDescription().equals(cake.getDesc()) &&
                result.get(0).getTitle().equals(cake.getTitle()) &&
                result.get(0).getImage().equals(cake.getImage()));
    }

}

package com.waracle.cakemgr.init;

import com.waracle.cakemgr.exception.BusinessException;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.repo.CakeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is to load initial data
 */

@Component
public class CakeLoaderApplicationRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeLoaderApplicationRunner.class);

    @Value("${cakemgr.json.url}")
    private String dataUrl;

    @Autowired
    private CakeRepo cakeRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            LOGGER.info("Getting data from remote host");
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            ResponseEntity<List<Cake>> response = restTemplate.exchange(
                    dataUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Cake>>() {
                    });
            List<Cake> cakes = response.getBody();

            LOGGER.info("Saving entities " + cakes.size());

            cakes.stream().forEach(c -> cakeRepo.saveAndFlush(c));

            LOGGER.info("Entities saved");
        }
        catch(Exception e) {
            LOGGER.error("Error while loading initial data "+e.getMessage());
            throw new BusinessException("Failed to load initial data "+e.toString());
        }
    }
}

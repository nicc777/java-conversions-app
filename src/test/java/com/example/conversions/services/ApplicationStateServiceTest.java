package com.example.conversions.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.conversions.ConversionsDemoApplication;

@SpringBootTest
@ContextConfiguration(classes = ConversionsDemoApplication.class)
public class ApplicationStateServiceTest {
    
    @Autowired 
    ApplicationStateService applicationStateService;

    @Test
    void isReadyTest() {
        assertTrue(applicationStateService.isReady());
    }

}

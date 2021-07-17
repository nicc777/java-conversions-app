package com.example.conversions.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ApplicationStateServiceTest {
    
    @Autowired 
    ApplicationStateService applicationStateService;

    @Test
    void isReadyTest() {
        assertTrue(applicationStateService.isReady());
    }

}

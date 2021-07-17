package com.example.conversions.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OsFunctionsTest {
    
    @Autowired
    OsFunctions osFunctions;

    @Test
    void getHostnameTest() {
        String result = osFunctions.getHostname();
        assertTrue(result != null);
    }

}

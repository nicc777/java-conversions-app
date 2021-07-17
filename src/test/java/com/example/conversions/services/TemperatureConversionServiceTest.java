package com.example.conversions.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TemperatureConversionServiceTest {
    
    @Autowired
    private TemperatureConversionService temperatureConversionService;

    @Test
    void celsiusToFahrenheitTest() {
        double degrees = 15.0;
        double expected_result = 59.0;
        assertEquals(temperatureConversionService.celsiusToFahrenheit(degrees), expected_result);
    }

    @Test
    void fahrenheitToCelsiusTest() {
        double degrees = 59.0;
        double expected_result = 15.0;
        assertEquals(temperatureConversionService.fahrenheitToCelsius(degrees), expected_result);
    }

}

package com.example.conversions.models;

import com.example.conversions.services.TemperatureConversionService;
import com.example.conversions.services.TemperatureConversionServiceImpl;


public enum ConversionStrategy { 

    CELSIUS_TO_FAHRENHEIT {
        @Override
        public
        Double convert(Double value) {
            TemperatureConversionService temperatureConversionService = new TemperatureConversionServiceImpl();
            return temperatureConversionService.celsiusToFahrenheit(value);
        }
    },

    FAHRENHEIT_TO_CELSIUS {
        @Override
        public
        Double convert(Double value) {
            TemperatureConversionService temperatureConversionService = new TemperatureConversionServiceImpl();
            return temperatureConversionService.fahrenheitToCelsius(value);
        }
    };

    public abstract Double convert(Double value);

}



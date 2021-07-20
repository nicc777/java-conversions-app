package com.example.conversions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {
    
    private String inputUnit;

    private Double inputValue;

    private Double convertedValue;

    private String convertedUnit;

}

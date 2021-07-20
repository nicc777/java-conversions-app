package com.example.conversions.controllers;

import javax.annotation.PostConstruct;

import com.example.conversions.models.ConversionRequestBody;
import com.example.conversions.models.ConversionStrategy;
import com.example.conversions.models.ConversionResponse;
import com.example.conversions.services.ApplicationStateService;
import com.example.conversions.services.TemperatureConversionService;
import com.example.conversions.utils.Generated;
import com.example.conversions.utils.OsFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;

@RestController
@RequestMapping("/conversions/v2")
@Slf4j
public class TempConvetController {

    @Autowired
    OsFunctions osFunctions;

    @Autowired
    TemperatureConversionService temperatureConversionService;

    @Autowired
	ApplicationStateService applicationStateService;

    @GetMapping("/liveness")
    @Generated
    public String liveness() {
        log.info("[" + osFunctions.getHostname() + "] liveness() called");
        return "ok";
    }

    @GetMapping("/readiness")
    @Generated
    public String readiness() {
        log.info("[" + osFunctions.getHostname() + "] readiness() called");
        if (!applicationStateService.isReady()){
            log.error("[" + osFunctions.getHostname() + "] readiness() - service not ready");
            throw new ServiceNotReadyException();
        }
        return "ok";
    }

    @PostMapping("/convert")
    public ConversionResponse convertAny(@RequestBody ConversionRequestBody conversionRequestBody) {
        if (!applicationStateService.isReady())
            throw new ServiceNotReadyException();
        try {
            log.info("Raw request body: " + conversionRequestBody.toString());
            String requestedStrategy = conversionRequestBody.getSourceUnit().toUpperCase() + "_TO_" + conversionRequestBody.getDestinationUnit().toUpperCase();
            ConversionStrategy conversionStrategy = ConversionStrategy.valueOf(requestedStrategy);
            Double result = conversionStrategy.convert(Double.parseDouble(conversionRequestBody.getValue()));
            log.info("[" + osFunctions.getHostname() + "] " + conversionRequestBody.getValue() + " " + conversionRequestBody.getSourceUnit().toLowerCase() + " is " + result + " in " + conversionRequestBody.getDestinationUnit().toLowerCase());
            return new ConversionResponse(conversionRequestBody.getSourceUnit(), Double.parseDouble(conversionRequestBody.getValue()), result, conversionRequestBody.getDestinationUnit());
        } catch(Exception e) {
            log.error("EXCEPTION: " + ExceptionUtils.getStackTrace(e));
        }
        throw new InvalidRequestException();
    }

    @PostConstruct
    public void postConstruct(){
        try {
            applicationStateService.prepareReadyState();
        } catch (InterruptedException e) {
            log.error("EXCEPTION: ", e);
        }
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason="The service is not in a ready state")
    @Generated
    public class ServiceNotReadyException extends RuntimeException {}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="The conversion request is not yet supported")
    @Generated
    public class InvalidRequestException extends RuntimeException {}

}

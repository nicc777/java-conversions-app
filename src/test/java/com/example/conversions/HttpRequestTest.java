/**
 * Refer to https://spring.io/guides/gs/testing-web/ for setting up controller tests
 */

package com.example.conversions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @Test
    void convertCtoFTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/conversions/v2/convert")
			.accept(MediaType.APPLICATION_JSON).content("{\"sourceUnit\": \"Celsius\",\"destinationUnit\": \"Fahrenheit\",\"value\": \"15\"}")
			.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == 200);
        assertTrue(response.getContentAsString().contains("59.0"));
    }

    @Test
    void convertFtoCTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/conversions/v2/convert")
			.accept(MediaType.APPLICATION_JSON).content("{\"sourceUnit\": \"Fahrenheit\",\"destinationUnit\": \"Celsius\",\"value\": \"59\"}")
			.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == 200);
        assertTrue(response.getContentAsString().contains("15.0"));
    }

}

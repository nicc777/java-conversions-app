/**
 * Refer to https://spring.io/guides/gs/testing-web/ for setting up controller tests
 */

package com.example.conversions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @Test
    void convertCtoFTest() throws Exception {
        mockMvc.perform(get("/v1/convert/c-to-f/15.0")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("59.0")));
    }

    @Test
    void convertFtoCTest() throws Exception {
        mockMvc.perform(get("/v1/convert/f-to-c/59.0")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("15.0")));
    }

}

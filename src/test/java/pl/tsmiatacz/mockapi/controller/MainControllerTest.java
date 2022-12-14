package pl.tsmiatacz.mockapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.AdditionalMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheckShouldRespondWith200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/isAlive"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("I'm alive")));
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 401, 500, 520})
    void shouldRespondWith200(int statusCode) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/error/" + statusCode))
                .andExpect(status().is(statusCode))
                .andExpect(content().string(containsString("Responded with: " + statusCode)));
    }
}
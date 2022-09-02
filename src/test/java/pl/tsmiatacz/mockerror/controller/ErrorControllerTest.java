package pl.tsmiatacz.mockerror.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(ints = {200, 401, 500, 520})
    void shouldRespondWith200(int statusCode) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/" + statusCode))
                .andExpect(status().is(statusCode))
                .andExpect(content().string(containsString("Responded with: " + statusCode)));
    }
}
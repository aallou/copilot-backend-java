package com.example.demo.controller;

import com.example.demo.dto.HelloDto;
import com.example.demo.service.HelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HelloControllerTest {

    private HelloService svc;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        svc = Mockito.mock(HelloService.class);
        var controller = new HelloController(svc);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("GET /hello returns JSON with message from service")
    void getHello_returnsJson() throws Exception {
        // Given
        when(svc.getHello(eq("Bob"))).thenReturn(new HelloDto("Hello, Bob!"));

        // When / Then
        mvc.perform(get("/hello").param("name", "Bob"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello, Bob!\"}"));
    }

    @Test
    @DisplayName("GET /hello without name param returns 400 Bad Request")
    void getHello_missingParam_returnsBadRequest() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Controller delegates to HelloService when name provided")
    void getHello_delegatesToService() throws Exception {
        when(svc.getHello(eq("Eve"))).thenReturn(new HelloDto("Hello, Eve!"));

        mvc.perform(get("/hello").param("name", "Eve")).andExpect(status().isOk());

        verify(svc).getHello(eq("Eve"));
    }
}

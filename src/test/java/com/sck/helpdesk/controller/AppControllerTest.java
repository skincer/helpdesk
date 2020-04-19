package com.sck.helpdesk.controller;

import com.sck.helpdesk.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AppController.class)
@Import(AppController.class)
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void whenNotAuthenticated_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/app/home"));

        mockMvc.perform(get("/app/home"))
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @WithMockUser(authorities = "ROLE_CUSTOMER")
    @Test
    void whenAuthenticated_thenRedirectToHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(redirectedUrl("/app/home"));
    }

    @Test
    void login() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }
}
package com.sck.helpdesk.controller;

import com.sck.helpdesk.repository.CategoryRepository;
import com.sck.helpdesk.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
@Import(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private CategoryRepository categoryRepository;

    @WithMockUser(authorities = "ROLE_AGENT")
    @Test
    void whenDisplayIndex_thenReturns200() throws Exception {
        mockMvc.perform(get("/app/category")).andExpect(status().isOk());
    }
}
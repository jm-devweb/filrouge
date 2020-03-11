package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.service.DepartmentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService regionService;

 /*   @Test
    void emptyFindAll() throws Exception {
        when(this.regionService.findAll ()).thenReturn(new ArrayList<> ());
        this.mockMvc.perform(get("/api/regions")).andExpect(status().isNoContent ());
    }*/
}

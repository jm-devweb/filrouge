package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.service.PoIService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(PoIController.class)
public class PoIControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PoIController restController;

    @MockBean
    PoIService poiService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    void whenFindAll_thenReturnPoIList() throws Exception {
        PoIDTO objDTO1 = PoIDTO.builder()
                .id (1L)
                .name("Cinéma")
                .build();
        PoIDTO objDTO2 = PoIDTO.builder()
                .id (2L)
                .name("Théatre")
                .build();
        List<PoIDTO> expectedList = Arrays.asList(objDTO1,objDTO2);

        given(poiService.findAll ()).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/pois")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void whenFindAll_thenReturnEmptyList() throws Exception {
        List<PoIDTO> expectedList = Arrays.asList();

        given(poiService.findAll ()).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/pois")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent ());
    }

    @Test
    void whenFindById_thenReturnPoI() throws Exception {
        PoIDTO objDTO1 = PoIDTO.builder()
                .id (1L)
                .name("Cinéma")
                .build();

        given(poiService.findById (1L)).willReturn(objDTO1);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/pois/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void whenFindById_thenReturnNull() throws Exception {

        given(poiService.findById (1L)).willReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/pois/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound ());
    }
}

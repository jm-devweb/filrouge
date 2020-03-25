package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.service.CityService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CityController restController;

    @MockBean
    CityService cityService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    void whenFindById_thenReturnCity() throws Exception {
        CityDTO objDTO1 = CityDTO.builder()
                .id (1L)
                .name("Paris")
                .build();

        given(cityService.findById (1L)).willReturn(objDTO1);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/cities/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void whenFindById_thenReturnNull() throws Exception {

        given(cityService.findById (1L)).willReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/cities/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound ());
    }

    @Test
    void whenFindByRegionId_thenReturnCityList() throws Exception {
        CityDTO objDTO1 = CityDTO.builder()
                .id (1L)
                .name("Paris")
                .build();
        CityDTO objDTO2 = CityDTO.builder()
                .id (2L)
                .name("Dourdan")
                .build();
        List<CityDTO> expectedList = Arrays.asList(objDTO1,objDTO2);

        given(cityService.findCitiesByDepartmentId (1L)).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/cities/departments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void whenFindByRegionId_thenReturnEmptyList() throws Exception {

        given(cityService.findCitiesByDepartmentId (1L)).willReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/cities/departments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound ());
    }
}

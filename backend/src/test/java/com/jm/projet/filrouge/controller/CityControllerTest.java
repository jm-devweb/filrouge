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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void whenFindAll_thenReturnCityList() throws Exception {
        CityDTO city = CityDTO.builder()
                .name("Paris")
                .build();
        List<CityDTO> expectedCities = Arrays.asList(city);

        given(cityService.findAll ()).willReturn(expectedCities);

        mockMvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Paris")));
    }

    @Test
    void whenFindById_thenReturnCity() throws Exception {
        CityDTO city = CityDTO.builder()
                .name("Paris")
                .build();

        given(cityService.findById (1L)).willReturn(city);

        mockMvc.perform(get("/api/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Paris")));
    }

    @Test
    void whenFindByDepartmentId_thenReturnRegion() throws Exception {
        CityDTO cityDTO = CityDTO.builder()
                .name("Paris")
                .build();
        List<CityDTO> expectedCities = Arrays.asList(cityDTO);

        given(cityService.findCitiesByDepartmentId (1L)).willReturn(expectedCities);

        mockMvc.perform(get("/api/cities/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Paris")));

    }

}

package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.service.RegionService;

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
@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private RegionController restController;

    @MockBean
    RegionService regionService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    void whenFindAll_thenReturnRegionList() throws Exception {
        RegionDTO regionDTO = RegionDTO.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build();
        List<RegionDTO> expectedRegions = Arrays.asList(regionDTO);

        given(regionService.findAll ()).willReturn(expectedRegions);

        mockMvc.perform(get("/api/regions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Auvergne-Rhône-Alpes")));

     }


    @Test
    void whenFindById_thenReturnRegion() throws Exception {
        RegionDTO regionDTO = RegionDTO.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build();

        given(regionService.findById (1L)).willReturn(regionDTO);

        mockMvc.perform(get("/api/regions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Auvergne-Rhône-Alpes")));
    }
}

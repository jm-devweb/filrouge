package com.jm.projet.filrouge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        RegionDTO regionDTO1 = RegionDTO.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build();
        RegionDTO regionDTO2 = RegionDTO.builder()
                .id (2L)
                .name("Bretagne")
                .build();
        List<RegionDTO> expectedRegions = Arrays.asList(regionDTO1,regionDTO2);

        given(regionService.findAll ()).willReturn(expectedRegions);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/regions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
     }

    @Test
    void whenFindAll_thenReturnEmptyList() throws Exception {
        List<RegionDTO> expectedRegions = Arrays.asList();

        given(regionService.findAll ()).willReturn(expectedRegions);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/regions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent ());
    }

    @Test
    void whenFindById_thenReturnRegion() throws Exception {
        RegionDTO regionDTO = RegionDTO.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build();


        Mockito.doReturn(Optional.of(regionDTO))
                .when(this.regionService)
                .findById (1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/regions/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void whenFindById_thenNotFound() throws Exception {
        Mockito.doReturn(Optional.empty()).when(regionService).findById (1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/regions/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }
}

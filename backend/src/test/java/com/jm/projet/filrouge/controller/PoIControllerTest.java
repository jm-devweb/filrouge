package com.jm.projet.filrouge.controller;

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
        PoIDTO poi = PoIDTO.builder()
                .id (1L)
                .name ("Cinéma")
                .trips (new ArrayList<Trip> ( ))
                .users (new ArrayList<User> ( ))
                .build();
        List<PoIDTO> expectedPoIs = Arrays.asList(poi);

        given(poiService.findAll ()).willReturn(expectedPoIs);

        mockMvc.perform(get("/api/pois")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Cinéma")));

    }


    @Test
    void whenFindById_thenReturnPoI() throws Exception {
        PoIDTO poi = PoIDTO.builder()
                .id (1L)
                .name ("Cinéma")
                .trips (new ArrayList<Trip> ( ))
                .users (new ArrayList<User> ( ))
                .build();

        given(poiService.findById (1L)).willReturn(poi);

        mockMvc.perform(get("/api/pois/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Cinéma")));
    }


}

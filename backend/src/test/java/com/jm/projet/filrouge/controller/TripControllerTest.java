package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(TripController.class)
public class TripControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TripController restController;

    @MockBean
    TripService objectService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    void whenFindById_thenReturnTrip() throws Exception {
        final Long PARAM = 1L;
        final String URL = "/api/trips/1";

        TripDTO objDTO = TripDTO.builder()
                .id (PARAM)
                .name("Paris")
                .build();

        given(objectService.findById (PARAM)).willReturn(Optional.of(objDTO));

        mockMvc.perform( MockMvcRequestBuilders
                .get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void whenFindById_thenNotFound() throws Exception {
        final Long PARAM = 1L;
        final String URL = "/api/trips/1";

        Mockito.doReturn(Optional.empty()).when(objectService).findById (PARAM);
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }
}

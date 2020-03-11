package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.service.DepartmentService;
import com.jm.projet.filrouge.service.PoIService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = PoIController.class)
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
    void emptyFindAll() throws Exception {
        when(this.poiService.findAll ()).thenReturn(new ArrayList<> ());
        this.mockMvc.perform(get("/api/pois")).andExpect(status().isNoContent ());
    }


}

package com.jm.projet.filrouge.controller;


import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.service.DepartmentService;
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
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private DepartmentController restController;

    @MockBean
    DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    void whenFindAll_thenReturnDepartmentList() throws Exception {
        DepartmentDTO objDTO1 = DepartmentDTO.builder()
                .id (1L)
                .name("Paris")
                .build();
        DepartmentDTO objDTO2 = DepartmentDTO.builder()
                .id (2L)
                .name("Essonne")
                .build();
        List<DepartmentDTO> expectedList = Arrays.asList(objDTO1,objDTO2);

        given(departmentService.findAll ()).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void whenFindAll_thenReturnEmptyList() throws Exception {
        List<DepartmentDTO> expectedList = Arrays.asList();

        given(departmentService.findAll ()).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent ());
    }

    @Test
    void whenFindById_thenReturnDepartment() throws Exception {
        DepartmentDTO objDTO1 = DepartmentDTO.builder()
                .id (1L)
                .name("Paris")
                .build();

        given(departmentService.findById (1L)).willReturn(objDTO1);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void whenFindById_thenReturnNull() throws Exception {

        given(departmentService.findById (1L)).willReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound ());
    }


    @Test
    void whenFindByRegionId_thenReturnDepartmentList() throws Exception {
        DepartmentDTO objDTO1 = DepartmentDTO.builder()
                .id (1L)
                .name("Paris")
                .build();
        DepartmentDTO objDTO2 = DepartmentDTO.builder()
                .id (2L)
                .name("Essonne")
                .build();
        List<DepartmentDTO> expectedList = Arrays.asList(objDTO1,objDTO2);

        given(departmentService.findDepartmentsByRegionId (1L)).willReturn(expectedList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments/regions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void whenFindByRegionId_thenReturnEmptyList() throws Exception {

        given(departmentService.findDepartmentsByRegionId (1L)).willReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/departments/regions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound ());
    }
}

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        DepartmentDTO department = DepartmentDTO.builder()
                .name("Paris")
                .build();
        List<DepartmentDTO> expectedDepartments = Arrays.asList(department);

        given(departmentService.findAll ()).willReturn(expectedDepartments);

        mockMvc.perform(get("/api/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Paris")));
    }

    @Test
    void whenFindById_thenReturnRegion() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO (1L,"Ain",new Region (1L,"Auvergne-Rhône-Alpes"));

        given(departmentService.findById (1L)).willReturn(departmentDTO);

        mockMvc.perform(get("/api/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Ain")));
    }

    @Test
    void whenFindByRegionId_thenReturnRegion() throws Exception {
        DepartmentDTO department = DepartmentDTO.builder()
                .name("Paris")
                .build();
        List<DepartmentDTO> expectedDepartments = Arrays.asList(department);

        given(departmentService.findDepartmentsByRegionId (1L)).willReturn(expectedDepartments);

        mockMvc.perform(get("/api/departments/regions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Paris")));

    }

}

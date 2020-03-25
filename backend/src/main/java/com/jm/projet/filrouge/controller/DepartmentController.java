package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RequestMapping(value = "/api/departments")

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> departments = departmentService.findAll ();
        return departments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(departments);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable(value = "departmentId")
                    Long regionId) {
        DepartmentDTO departmentDTO = departmentService.findById(regionId);
        return Objects.isNull(departmentDTO) ? ResponseEntity.notFound().build() : ResponseEntity.ok(departmentDTO);
    }

    @GetMapping("/regions/{regionId}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentByRegionId(
            @PathVariable(value = "regionId")
                    Long regionId) {
        List<DepartmentDTO> departmentDTO = departmentService.findDepartmentsByRegionId (regionId);
        return Objects.isNull(departmentDTO) ? ResponseEntity.notFound().build() : ResponseEntity.ok(departmentDTO);
    }
}

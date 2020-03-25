package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @ApiOperation(
            value = "Get all departments",
            response = DepartmentDTO.class,
            notes = "Get list of departments",
            nickname = "getAll")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 204, message = "No Content")
            })
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> departments = departmentService.findAll ();
        return departments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.status (HttpStatus.OK).body (departments);
    }

    @GetMapping("/{departmentId}")
    @ApiOperation(
            value = "Get a department",
            response = DepartmentDTO.class,
            notes = "Get a department",
            nickname = "getDepartmentById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable(value = "departmentId")
                    Long regionId) {
        DepartmentDTO departmentDTO = departmentService.findById(regionId);
        return Objects.isNull(departmentDTO) ? ResponseEntity.notFound().build() : ResponseEntity.ok(departmentDTO);
    }

    @GetMapping("/regions/{regionId}")
    @ApiOperation(
            value = "Get all departments",
            response = DepartmentDTO.class,
            notes = "Get list of departments for a region",
            nickname = "getDepartmentByRegionId")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 204, message = "No Content")
            })
    public ResponseEntity<List<DepartmentDTO>> getDepartmentByRegionId(
            @PathVariable(value = "regionId")
                    Long regionId) {
        List<DepartmentDTO> departmentDTO = departmentService.findDepartmentsByRegionId (regionId);
        return Objects.isNull(departmentDTO) ? ResponseEntity.notFound().build() : ResponseEntity.ok(departmentDTO);
    }
}

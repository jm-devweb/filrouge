package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.mapper.DepartmentMapper;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/departments")
@Validated
@Api(tags = "Department")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    @Autowired
    DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper= departmentMapper;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> find(
            @ApiParam(value = "Id of Region for 'idRegion'")
            @Valid
            @RequestParam(value = "idRegion", required = false, defaultValue = "0") Long idRegion) {
        List<Department> departments = null ;
        if(idRegion == 0L) {
            departments = departmentService.findAll ( );
        }
        else {
            departments = departmentService.findDepartmentsByRegionId (idRegion);
        }
        return (!departments.isEmpty ()) ? ResponseEntity.ok(departmentMapper.toListDTO (departments)) : ResponseEntity.notFound().build() ;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @ApiParam(value = "The id of the Department to retrieve", required = true)
            @PathVariable(value = "departmentId")
                    Long departmentId) {
        // log.info("[ENDPOINT] Received request to get a region");
        Optional<Department> departmentOpt = departmentService.findById(departmentId);
        return (departmentOpt.isPresent ()) ? ResponseEntity.ok(departmentMapper.toDTO (departmentOpt.get ())) : ResponseEntity.notFound().build() ;
    }


}

package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.service.DepartmentService;
import com.jm.projet.filrouge.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/regions")
@Validated
@Api(tags = "Region")
@Slf4j
public class RegionController {

    private final RegionService regionService;

    private final RegionMapper regionMapper;

    @Autowired
    RegionController(RegionService regionService, RegionMapper regionMapper) {
        this.regionService = regionService;
        this.regionMapper= regionMapper;
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<Region> regions = regionService.findAll ();

        return (!regions.isEmpty ()) ? ResponseEntity.ok(regionMapper.toListDTO (regions)) : ResponseEntity.notFound().build() ;
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionDTO> getRegionById(
            @ApiParam(value = "The id of the region to retrieve", required = true)
            @PathVariable(value = "regionId")
                    Long regionId) {
        // log.info("[ENDPOINT] Received request to get a region");
        Optional<Region> regionOpt = regionService.findById(regionId);
        return (regionOpt.isPresent ()) ? ResponseEntity.ok(regionMapper.toDTO (regionOpt.get ())) : ResponseEntity.notFound().build() ;
    }
}

package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/regions")
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<RegionDTO> regions = regionService.findAll ();
        return regions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(regions);
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionDTO> getRegionById(
            @PathVariable(value = "regionId")
                    Long regionId) {
        RegionDTO region = regionService.findById(regionId);
        return Objects.isNull(region) ? ResponseEntity.notFound().build() : ResponseEntity.ok(region);
    }
}

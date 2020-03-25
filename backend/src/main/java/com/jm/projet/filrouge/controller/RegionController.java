package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.RegionService;
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
import java.util.Optional;

@RequestMapping(value = "/api/regions")
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    @ApiOperation(
            value = "Get all region",
            response = RegionDTO.class,
            notes = "Get list of regions",
            nickname = "getAll")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 204, message = "No Content")
            })
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<RegionDTO> regions = regionService.findAll ();
        return regions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.status (HttpStatus.OK).body (regions);
    }

    @GetMapping("/{regionId}")
    @ApiOperation(
            value = "Get a region",
            response = RegionDTO.class,
            notes = "Get a region",
            nickname = "getRegionById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<RegionDTO> getRegionById(
            @PathVariable(value = "regionId")
                    Long regionId)  {
        Optional<RegionDTO> regionDTO = regionService.findById (regionId);

        if(regionDTO.isPresent ()) {
            return ResponseEntity.status(HttpStatus.OK).body(regionDTO.get ());
        }
        return  ResponseEntity.notFound ().build() ;
    }
}

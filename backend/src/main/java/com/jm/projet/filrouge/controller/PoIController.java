package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.PoIService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/pois")

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class PoIController {

    private final PoIService poiService;

    @GetMapping
    @ApiOperation(
            value = "Get all point of interest",
            response = PoIDTO.class,
            notes = "Get list of point of interest",
            nickname = "getAll")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 204, message = "No Content")
            })
    public ResponseEntity<List<PoIDTO>> getAll() {
        List<PoIDTO> pois = poiService.findAll ();
        return pois.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pois);
    }

    @GetMapping("/{poiId}")
    @ApiOperation(
            value = "Get a point of interest",
            response = PoIDTO.class,
            notes = "Get a point of interest",
            nickname = "getPoIById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<PoIDTO> getPoIById(
            @PathVariable(value = "poiId")
                    Long poiId) {
        PoIDTO poi = poiService.findById(poiId);
        return Objects.isNull(poi) ? ResponseEntity.notFound().build() : ResponseEntity.ok(poi);
    }
}

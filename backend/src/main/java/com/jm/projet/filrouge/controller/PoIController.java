package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.service.PoIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/pois")

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class PoIController {

    private final PoIService poiService;

    @GetMapping
    public ResponseEntity<List<PoIDTO>> getAll() {
        List<PoIDTO> pois = poiService.findAll ();
        return pois.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pois);
    }

    @GetMapping("/{poiId}")
    public ResponseEntity<PoIDTO> getRegionById(
            @PathVariable(value = "poiId")
                    Long poiId) {
        PoIDTO poi = poiService.findById(poiId);
        return Objects.isNull(poi) ? ResponseEntity.notFound().build() : ResponseEntity.ok(poi);
    }
}

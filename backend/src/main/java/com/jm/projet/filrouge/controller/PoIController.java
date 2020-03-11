package com.jm.projet.filrouge.controller;


import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.service.PoIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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

@RestController
@RequestMapping(value = "/api/pois")
@Validated
@Api(tags = "POI")
@Slf4j
public class PoIController {

    private final PoIService poiService;

    @Autowired
    PoIController(PoIService poiService) {
        this.poiService = poiService;
    }

    @GetMapping
    public ResponseEntity<List<PoIDTO>> getAll() {
        List<PoIDTO> pois = poiService.findAll ();
        return (!pois.isEmpty ()) ? ResponseEntity.ok(pois) : new ResponseEntity(HttpStatus.NO_CONTENT) ;
    }

    @GetMapping("/{poiId}")
    public ResponseEntity<PoIDTO> getPoIById(
            @ApiParam(value = "The id of the PoI to retrieve", required = true)
            @PathVariable(value = "poiId")
                    Long poiId) {
        // log.info("[ENDPOINT] Received request to get a region");
        PoIDTO poiDTO = poiService.findById(poiId);
        return (!Objects.isNull (poiDTO)) ? ResponseEntity.ok(poiDTO) : ResponseEntity.notFound().build() ;
    }

}

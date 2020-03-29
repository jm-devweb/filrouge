package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.model.TripManager;
import com.jm.projet.filrouge.service.TripManagerService;
import com.jm.projet.filrouge.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@RequestMapping(value = "/api/tripmanager")
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class TripManagerController {

    private final TripManagerService tripManagerService;

    @PostMapping
    public ResponseEntity<TripDTO> create(@Valid @RequestBody TripManager tripManager) throws ParseException {
        Optional<TripDTO> saveTrip = tripManagerService.create (tripManager);
        if (saveTrip.isPresent ( )) {
            return ResponseEntity.status (HttpStatus.CREATED.value ( )).body (saveTrip.get ());
        }
        return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR.value ( )).build ( );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> update(@PathVariable Long id, @Valid @RequestBody TripManager tripManager) throws ParseException {
        Optional<TripDTO> tripToUpdate = tripManagerService.update (tripManager);
        if (tripToUpdate.isPresent ( )) {
            return ResponseEntity.ok ( ).body (tripToUpdate.get () );
        }
        return ResponseEntity.badRequest ( ).build ( );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (tripManagerService.delete (id)) {
            return ResponseEntity.ok ( ).build ( );
        }
        return ResponseEntity.badRequest ( ).build ( );
    }
}

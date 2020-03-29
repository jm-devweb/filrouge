package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.service.TripService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RequestMapping(value = "/api/trips")
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class TripController {

    private final TripService tripService;

    @GetMapping
    @ApiOperation(
            value = "Get all trip",
            response = TripDTO.class,
            notes = "Get list of trips",
            nickname = "getAll")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 501, message = "Successful operation")
            })
    public ResponseEntity getAll() {
        return ResponseEntity.status (HttpStatus.NOT_IMPLEMENTED).build ( );
    }

    @GetMapping("/{tripId}")
    @ApiOperation(
            value = "Get a trip",
            response = TripDTO.class,
            notes = "Get a trip",
            nickname = "getTripById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<TripDTO> getTripById(
            @PathVariable(value = "tripId")
                    Long tripId) {
        Optional<TripDTO> tripDTO = tripService.findById (tripId);

        if (tripDTO.isPresent ( )) {
            return ResponseEntity.status (HttpStatus.OK).body (tripDTO.get ( ));
        }
        return ResponseEntity.notFound ( ).build ( );
    }

    /**
     * Filter trips
     *
     * @param pageable   the page to get
     * @param user       the user => promoteur
     * @param keywords   the age category to get (optional)
     * @param startDate  the login to get (optional)
     * @param poi        the poi id to get (optional)
     * @param region     the region id to get (optional)
     * @param department the department id to get (optional)
     * @return a page of users
     */
    @GetMapping("/filter")
    public Page<TripDTO> getFilteredTrips(
            @PageableDefault(size = 25, page = 0, direction = Sort.Direction.ASC) Pageable pageable,
            @ApiParam(value = "Query param for 'user'") @Valid @RequestParam(value = "user", defaultValue = "0") Long user,
            @ApiParam(value = "Query param for 'keywords'") @Valid @RequestParam(value = "keywords", defaultValue = "") String keywords,

            @ApiParam(value = "Query param for 'dates'")
            @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "startDate", defaultValue = "") String startDate,

            @ApiParam(value = "Query param for 'poi'") @Valid @RequestParam(value = "poi", defaultValue = "0") Integer poi,
            @ApiParam(value = "Query param for 'region'") @Valid @RequestParam(value = "region", defaultValue = "0") Integer region,
            @ApiParam(value = "Query param for 'department'") @Valid @RequestParam(value = "department", defaultValue = "0") Integer department
    ) throws ParseException {

        Date date = new Date ( );
        if (!startDate.equals ("")) {
            date = new SimpleDateFormat ("yyyy-MM-dd").parse (startDate);
        }
        return tripService.getFilteredTrips (pageable, poi, region, department, user, keywords, date);
    }

    /**
     * Register users
     *
     * @param user
     * @param trip
     * @param register
     * @return a trip
     */
    @GetMapping("/register")
    public ResponseEntity<TripDTO> registerController(
            @ApiParam(value = "param for 'user'") @Valid @RequestParam(value = "user", defaultValue = "0") Long user,
            @ApiParam(value = "param for 'trip'") @Valid @RequestParam(value = "trip", defaultValue = "0") Long trip,
            @ApiParam(value = "param for 'register'") @Valid @RequestParam(value = "register", defaultValue = "true") boolean register) {

        Optional<TripDTO> tripDTO = tripService.registerService (user, trip, register);

        if (tripDTO.isPresent ( )) {
            return ResponseEntity.status (HttpStatus.OK).body (tripDTO.get ( ));
        }
        return ResponseEntity.notFound ( ).build ( );
    }


}

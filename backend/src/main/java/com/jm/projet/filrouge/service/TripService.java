package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.mapper.TripMapper;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import static com.jm.projet.filrouge.repository.TripSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripService {

    private final TripRepository objectRepository;
    private final TripMapper objectMapper;

    public Optional<TripDTO> findById(Long tripId) {
        Optional<Trip> found = objectRepository.findById (tripId);
        if(found.isPresent ( )) {
            return  Optional.of (objectMapper.INSTANCE.toDTO (found.get ( )));
        }
        return  Optional.empty ( );
     }

    /**
     * Filter users
     *
     * @param pageable the page to get
     * @param poiId the poi to get (optional)
     * @param regionId the region id to get (optional)
     * @param departmentId the department id to get (optional)
     * @return a page of users
     */
    public Page<TripDTO> getFilteredTrips(
            Pageable pageable,  int poiId, int regionId, int departmentId) {
        // Find filtered trips in the repository
        Page<Trip> tripPage = objectRepository.findAll(
                where(hasDepartmentOptional(departmentId).and(hasRegionOptional(regionId))),pageable);

        return tripPage.map(trip -> objectMapper.INSTANCE.toDTO(trip));
    }

}

package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.mapper.TripMapper;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.TripRepository;
import com.jm.projet.filrouge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.jm.projet.filrouge.repository.TripSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripMapper objectMapper;

    public Optional<TripDTO> findById(Long tripId) {
        Optional<Trip> found = tripRepository.findById (tripId);
        if (found.isPresent ( )) {
            return Optional.of (objectMapper.INSTANCE.toDTO (found.get ( )));
        }
        return Optional.empty ( );
    }

    /**
     * Filter users
     *
     * @param pageable     the page to get
     * @param poiId        the poi to get (optional)
     * @param regionId     the region id to get (optional)
     * @param departmentId the department id to get (optional)
     * @return a page of users
     */
    public Page<TripDTO> getFilteredTrips(
            Pageable pageable, int poiId, int regionId, int departmentId, Long userId, String words, Date startDate) {
        // Find filtered trips in the repository

        Page<Trip> tripPage = tripRepository.findAll (
                where (hasDepartmentOptional (departmentId)
                        .and (hasRegionOptional (regionId))
                        .and (hasPoIOptional (poiId))
                        .and (hasUserOptional (userId))
                       .and (hasDateOptional(startDate))
                        .and ((hasWordsTitleOptional (words)).or (hasWordsDescriptionOptional (words)))
                ), pageable);

        return tripPage.map (trip -> objectMapper.INSTANCE.toDTO (trip));
    }

    /**
     * Filter users
     *
     * @param userId
     * @param tripId
     * @return a trip
     */
    public Optional<TripDTO> registerService(Long userId, Long tripId, boolean register) {
        boolean result = true;
        Optional<User> currentUser = userRepository.findById (userId);
        Optional<Trip> currentTrip = tripRepository.findById (tripId);
        if (currentUser.isPresent ( ) && currentTrip.isPresent ( )) {
            Trip thisTrip = currentTrip.get ( );
            if (register) {
                result = thisTrip.unregister (currentUser.get ( ));
            } else {
                result = thisTrip.register (currentUser.get ( ));
            }
            thisTrip = tripRepository.saveAndFlush (thisTrip);
            return Optional.of (objectMapper.INSTANCE.toDTO (thisTrip));
        }
        return Optional.empty ( );
    }


}

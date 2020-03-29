package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.mapper.TripMapper;
import com.jm.projet.filrouge.model.*;
import com.jm.projet.filrouge.repository.CityRepository;
import com.jm.projet.filrouge.repository.PoIRepository;
import com.jm.projet.filrouge.repository.TripRepository;
import com.jm.projet.filrouge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripManagerService {

    private final TripRepository tripRepository;
    private final PoIRepository poiRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final TripMapper tripMapper;

    /**
     * @param tripManager
     * @return
     */
    public Optional<TripDTO> create(final TripManager tripManager) throws ParseException {
        Date dateTrip = new SimpleDateFormat ("yyyy-MM-dd").parse(tripManager.getDateTrip ( ));
        Date timeStart = new SimpleDateFormat ("HH-mm").parse(tripManager.getTimeStart ());
        Date timeEnd = new SimpleDateFormat ("HH-mm").parse(tripManager.getTimeEnd ());

        final Optional<City> currentCity = cityRepository.findById (tripManager.getCityId ( ));
        final Optional<PoI> currentPoI = poiRepository.findById (tripManager.getPoiId ( ));
        final Optional<User> currentPromoteur = userRepository.findById (tripManager.getPromoteurId ( ));
        if (currentPromoteur.isPresent ( ) && currentCity.isPresent ( ) && currentPoI.isPresent ( )) {
            Trip tripToSave = Trip.builder ( )
                //    .id (1L)
                    .name (tripManager.getName ( ))
                    .dateTrip (dateTrip)
                    .timeStart (timeStart)
                    .timeEnd (timeEnd)
                    .nbPerson (tripManager.getNbPerson ( ))
                    .description (tripManager.getDescription ( ))
                    .ageMin (tripManager.getAgeMin ( ))
                    .ageMax (tripManager.getAgeMax ( ))
                    .city (currentCity.get ())
                    .poi (currentPoI.get ())
                    .promoteur (currentPromoteur.get ())
                    .build ( );
            System.out.println (tripToSave );
            return Optional.of (tripMapper.INSTANCE.toDTO (tripRepository.save (tripToSave)));
        }
        return Optional.empty ();
    }


    public Optional<TripDTO> update(TripDTO tripDTO) {


        Optional<Trip> tripToTest = this.tripRepository.findById (tripDTO.getId ( ));
        if (tripToTest.isPresent ( )) {
            // Get the current trip
            Trip tripToSave = tripMapper.INSTANCE.toEntity (tripDTO);
            tripToSave.setUsers (tripToTest.get ( ).getUsers ( ));
            return Optional.of (tripMapper.INSTANCE.toDTO (tripRepository.save (tripToSave)));
        }
        return Optional.empty ( );
    }

    public boolean delete(Long id) {
        Optional<Trip> tripToTest = this.tripRepository.findById (id);

        if (tripToTest.isPresent ( )) {
            tripRepository.deleteById (id);
            return true;
        }
        return false;
    }
}

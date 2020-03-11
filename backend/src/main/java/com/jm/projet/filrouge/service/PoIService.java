package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.mapper.PoIMapper;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.repository.PoIRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PoIService {

    private final PoIRepository poiRepository;
    private final PoIMapper poiMapper;

    public PoIService(PoIRepository poiRepository, PoIMapper poiMapper) {
        this.poiRepository = poiRepository;
        this.poiMapper = poiMapper ;
    }

    public PoIDTO findById(Long poiId) {
        Optional<PoI> poiOpt =poiRepository.findById(poiId)   ;
        return  (poiOpt.isPresent ()) ? poiMapper.toDTO (poiOpt.get ()) : null ;
    }

    public List<PoIDTO> findAll() {
        List<PoI> pois = poiRepository.findAll () ;
        return (!pois.isEmpty ()) ? poiMapper.toListDTO (pois) : new ArrayList<PoIDTO> ();
     }
}

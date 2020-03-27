package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.mapper.PoIMapper;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.PoIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PoIService {

    private final PoIRepository poiRepository;
    private final PoIMapper poiMapper;

    public PoIDTO findById(Long poiId) {
        Optional<PoI> found = poiRepository.findById(poiId);
        return found.isPresent () ? poiMapper.INSTANCE.toDTO (found.get ()) :  null ;
    }

    public List<PoIDTO> findAll() {
        List<PoI> found = poiRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return poiMapper.INSTANCE.toListDTO (found) ;
    }
}

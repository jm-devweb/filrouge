package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public Optional<RegionDTO> findById(Long regionId) {
        Optional<Region> found = regionRepository.findById (regionId);
        return found.isPresent ( ) ? Optional.of (regionMapper.INSTANCE.toDTO (found.get ( ))) : Optional.empty ( );
    }

    public List<RegionDTO> findAll() {
        List<Region> found = regionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return regionMapper.INSTANCE.toListDTO (found) ;
    }

}

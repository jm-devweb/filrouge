package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.PoIMapper;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionService(RegionRepository regionRepository,RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    public RegionDTO findById(Long regionId) {
        Optional<Region> regionOpt =regionRepository.findById(regionId)   ;
        return  (regionOpt.isPresent ()) ? regionMapper.toDTO (regionOpt.get ()) : null ;

    }

    public List<RegionDTO> findAll() {
        List<Region> regions = regionRepository.findAll () ;
        return (!regions.isEmpty ()) ? regionMapper.toListDTO (regions) : new ArrayList<RegionDTO> ();
    }

    public RegionDTO findRegionByName(String name) {
        Optional<Region> regionOpt =regionRepository.findRegionByName (name)   ;
        return  (regionOpt.isPresent ()) ? regionMapper.toDTO (regionOpt.get ()) : null ;

    }

}

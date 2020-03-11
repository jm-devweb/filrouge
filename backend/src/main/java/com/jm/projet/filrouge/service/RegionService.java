package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * Get a {@link Region} with the given id
     *
     * @param RegionId
     * @return a {@link Region} matching the given id or null if no Region matches the given id
     */
    public Optional<Region> findById(Long RegionId) {
        return regionRepository.findById(RegionId)   ;
    }

    /**
     * Get All
     *
     * @return all {@link Region}
     */
    public List<Region> findAll() {
        return regionRepository.findAll () ;
    }

    public Optional<Region> findRegionByName(String name) {
        return regionRepository.findRegionByName (name) ;
    }

}

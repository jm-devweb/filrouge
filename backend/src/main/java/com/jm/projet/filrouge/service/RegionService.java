package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionDTO findById(Long regionId) {
        Optional<Region> found = regionRepository.findById(regionId);
        return found.isPresent () ? regionMapper.INSTANCE.toDTO (found.get ()) :  null ;
    }

    public List<RegionDTO> findAll() {
        List<Region> found = regionRepository.findAll();
        return regionMapper.INSTANCE.toListDTO (found) ;
    }

     public RegionDTO save(RegionDTO regionDTO) {
        return regionMapper.toDTO(regionRepository.save(regionMapper.toEntity(regionDTO)));
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}

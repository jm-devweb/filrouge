package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper extends EntityDTOMapper<Trip, TripDTO> {
    TripMapper INSTANCE = Mappers.getMapper( TripMapper.class );

    @Mapping(target = "dateTrip", source = "dto.dateTrip", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "timeStart", source = "dto.timeStart", dateFormat = "HH-mm")
    @Mapping(target = "timeEnd", source = "dto.timeEnd", dateFormat = "HH-mm")
    Trip toEntity(TripDTO dto);


    @Mapping(target = "dateTrip", source = "entity.dateTrip", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "timeStart", source = "entity.timeStart", dateFormat = "HH-mm")
    @Mapping(target = "timeEnd", source = "entity.timeEnd", dateFormat = "HH-mm")
    TripDTO toDTO(Trip entity);

    List<TripDTO> toListDTO(Page<Trip> entity);
    List<TripDTO> toListDTO(List<Trip> entity);
    List<Trip> toListEntity(List<TripDTO> dto);

}

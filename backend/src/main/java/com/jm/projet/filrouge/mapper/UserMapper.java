package com.jm.projet.filrouge.mapper;


import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityDTOMapper<User, UserDTO> {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    User toEntity(UserDTO dto);
    UserDTO toDTO(User entity);

    List<UserDTO> toListDTO(Page<User> entity);
    List<UserDTO> toListDTO(List<User> entity);
    List<User> toListEntity(List<UserDTO> dto);
}

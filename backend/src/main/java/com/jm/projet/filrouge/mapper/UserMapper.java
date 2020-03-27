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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityDTOMapper<User, UserDTO> {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "birthday", source = "dto.birthday", dateFormat = "yyyy-MM-dd")
    User toEntity(UserDTO dto);

    @Mapping(target = "birthday", source = "entity.birthday", dateFormat = "yyyy-MM-dd")
    UserDTO toDTO(User entity);

    List<UserDTO> toListDTO(Page<User> entity);
    List<UserDTO> toListDTO(List<User> entity);
    List<User> toListEntity(List<UserDTO> dto);
}

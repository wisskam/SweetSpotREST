package com.sweetspotrecipies.sweetspot.api.mapper;

import com.sweetspotrecipies.sweetspot.api.model.UserDTO;
import com.sweetspotrecipies.sweetspot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="email", source="entity.email"),
            @Mapping(target="firstName", source="entity.firstName"),
            @Mapping(target="lastName", source="entity.lastName"),
    })
    UserDTO userToUserDTO(User entity);
    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="firstName", source="dto.firstName"),
            @Mapping(target="lastName", source="dto.lastName"),
    })
    User userDTOToUser(UserDTO dto);
    List<UserDTO> map(Iterable<User> users);
}

package com.orcrist.facebookcloneserver.dto.user;

import com.orcrist.facebookcloneserver.dto.auth.RegisterDto;
import com.orcrist.facebookcloneserver.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterDto dtoToEntity(User user);
    User entityToDto(RegisterDto dto);

    @Mapping(target = "user.roles", ignore = true)
    UserDto loginResponse(User user) throws ParseException;
}

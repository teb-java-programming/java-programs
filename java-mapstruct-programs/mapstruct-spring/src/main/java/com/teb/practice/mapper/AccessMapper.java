package com.teb.practice.mapper;

import com.teb.practice.bean.Access;
import com.teb.practice.dto.AccessDto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessMapper {

    @Mapping(target = "username", source = "user")
    AccessDto accessToAccessDto(Access access);

    @InheritInverseConfiguration
    @Mapping(target = "pass", ignore = true)
    Access accessDtoToAccess(AccessDto accessDto);
}

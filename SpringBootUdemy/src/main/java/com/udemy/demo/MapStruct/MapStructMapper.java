package com.udemy.demo.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.udemy.demo.DTO.UserMMDTO;
import com.udemy.demo.Entity.User;

@Mapper(componentModel="Spring")
public interface MapStructMapper {
	
	MapStructMapper INSTANCE=Mappers.getMapper(MapStructMapper.class);
	
	UserMMDTO userToUserMMDTO(User user);

}

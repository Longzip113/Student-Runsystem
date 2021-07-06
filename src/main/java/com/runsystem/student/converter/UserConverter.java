package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.dto.UserDTO;
import com.runsystem.student.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		
		entity.setEmail(dto.getEmail());
		entity.setPassWord(dto.getPassWord());
		entity.setUserId(dto.getUserId());
		
		return entity;
	}
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(entity.getEmail());
		dto.setPassWord(entity.getPassWord());
		dto.setUserId(entity.getUserId());
		dto.setRole_code(entity.getRoleEntity().getRoleCode());
		dto.setRoleId(entity.getRoleEntity().getRoleId());
		
		return dto;
	}

}

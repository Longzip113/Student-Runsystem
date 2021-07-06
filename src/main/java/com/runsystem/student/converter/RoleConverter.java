package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.dto.RoleDTO;
import com.runsystem.student.entity.RoleEntity;

@Component
public class RoleConverter {
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		
		entity.setRoleId(dto.getRoleId());	
		entity.setRoleCode(dto.getRoleCode());
		entity.setRoleName(dto.getRoleName());
		
		return entity;
	}
	
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		
		dto.setRoleId(entity.getRoleId());
		dto.setRoleCode(entity.getRoleCode());
		dto.setRoleName(entity.getRoleName());
		
		return dto;
	}
	
}

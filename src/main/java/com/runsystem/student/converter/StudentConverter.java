package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.entity.StudentEntity;

@Component
public class StudentConverter {

	public StudentEntity toEntity(StudentDTO dto) {
		StudentEntity entity = new StudentEntity();

		entity.setStudentCode(dto.getStudentCode());
		entity.setStudentName(dto.getStudentName());
		entity.setStudentId(dto.getStudentId());

		return entity;
	}

	public StudentDTO toDTO(StudentEntity entity) {
		StudentDTO dto = new StudentDTO();
		
		dto.setStudentCode(entity.getStudentCode());
		dto.setStudentName(entity.getStudentName());
		dto.setStudentId(entity.getStudentId());
		
		return dto;
	}

}

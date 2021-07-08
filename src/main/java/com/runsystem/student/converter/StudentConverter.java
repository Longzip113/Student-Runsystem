package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.utils.ConvertDate;

@Component
public class StudentConverter {
	
	ConvertDate convertDate;

	public StudentEntity toEntity(StudentDTO dto) {
		StudentEntity entity = new StudentEntity();

		entity.setStudentCode(dto.getStudentCode());
		entity.setStudentName(dto.getStudentName());
		entity.setStudentId(dto.getStudentId());

		return entity;
	}

	@SuppressWarnings("static-access")
	public StudentDTO toDTO(StudentEntity entity) {
		StudentDTO dto = new StudentDTO();
		
		dto.setStudentCode(entity.getStudentCode());
		dto.setStudentName(entity.getStudentName());
		dto.setStudentId(entity.getStudentId());
		dto.setAddress(entity.getStudentInfoEntity().getAddress());
		dto.setAverageScore(entity.getStudentInfoEntity().getAverageScore());
		dto.setDateOfBirth(convertDate.toLocalDate(entity.getStudentInfoEntity().getDateOfBirth()));
		
		return dto;
	}

}

package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.dto.StudentInfoDTO;
import com.runsystem.student.entity.StudentInfoEntity;
import com.runsystem.student.utils.ConvertDate;

@Component
public class StudentInfoConverter {
	
	ConvertDate convertDate;
	
	@SuppressWarnings("static-access")
	public StudentInfoEntity toEntity(StudentInfoDTO dto) {
		StudentInfoEntity entity = new StudentInfoEntity();
		
		entity.setInfoID(dto.getInfoID());
		entity.setAddress(dto.getAddress());
		entity.setAverageScore(dto.getAverageScore());
		
//		Convert LocalDate to data set for Entity;
		entity.setDateOfBirth(convertDate.toDate(dto.getDateOfBirth()));
		return entity;
	}
	
	@SuppressWarnings("static-access")
	public StudentInfoDTO toDTO(StudentInfoEntity entity) {
		StudentInfoDTO dto = new StudentInfoDTO();
		
//		Convert StudentInfoEntity to StudentInfoDTO
		dto.setInfoID(entity.getInfoID());
		dto.setAddress(entity.getAddress());
		dto.setAverageScore(entity.getAverageScore());
		
//		Convert Date to LocalDate set for DTO;		
		dto.setDateOfBirth(convertDate.toLocalDate(entity.getDateOfBirth()));
		
//		Convert StudentEntity to StudentDTO
		dto.getStudentDTO().setStudentCode(entity.getStudentEntity().getStudentCode());
		dto.getStudentDTO().setStudentName(entity.getStudentEntity().getStudentName());
		dto.getStudentDTO().setStudentId(entity.getStudentEntity().getStudentId());
		
		return dto;
	}

}

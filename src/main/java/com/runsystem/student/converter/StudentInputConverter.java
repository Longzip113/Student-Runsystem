package com.runsystem.student.converter;

import org.springframework.stereotype.Component;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.entity.StudentInfoEntity;
import com.runsystem.student.utils.ConvertDate;

@Component
public class StudentInputConverter {
	
	ConvertDate convertDate;
	
	public StudentEntity toStudentEntity(StudentInput input) {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setStudentId(input.getStudentId());
		studentEntity.setStudentCode(input.getStudentCode());
		studentEntity.setStudentName(input.getStudentName());

		return studentEntity;
	}
	
	@SuppressWarnings("static-access")
	public StudentInfoEntity toStudentInfoEntity(StudentInput input) {
		StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
		
		studentInfoEntity.setAddress(input.getAddress());
		studentInfoEntity.setAverageScore(input.getAverageScore());
		studentInfoEntity.setDateOfBirth(convertDate.toDate(input.getBirthDay()));
		
		return studentInfoEntity;
	}

}

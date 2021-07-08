package com.runsystem.student.service;

import java.util.List;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.entity.StudentEntity;

public interface IStudentService {
	
	List<StudentDTO> searchStudent(StudentInput student);
	StudentEntity findOne(Long id);

}

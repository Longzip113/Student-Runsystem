package com.runsystem.student.service;

import java.util.List;

import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.paging.Pageble;

public interface IStudentService {
	
	List<StudentDTO> searchStudent(StudentDTO student, Pageble pageble);
	
	Boolean deleteStudent(Long id);
	

}

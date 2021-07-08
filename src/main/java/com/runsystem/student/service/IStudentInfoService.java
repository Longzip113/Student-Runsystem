package com.runsystem.student.service;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.dto.StudentInfoDTO;

public interface IStudentInfoService {
	
	
	StudentInfoDTO findByStudentID(Long id);
	
	StudentInfoDTO saveStudent(StudentInput studentInput);
}

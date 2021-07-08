package com.runsystem.student.service;

import java.util.List;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.dto.StudentInfoDTO;

public interface IStudentInfoService {
	
	List<StudentInfoDTO> searchStudentByInfo(StudentInput student);
	
	StudentInfoDTO findByStudentID(Long id);
	
	StudentInfoDTO saveStudent(StudentInput studentInput);
}

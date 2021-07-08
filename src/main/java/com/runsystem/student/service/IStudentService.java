package com.runsystem.student.service;

import java.util.List;

import com.runsystem.student.entity.StudentEntity;

public interface IStudentService {
	
	List<StudentEntity> findByName();
	StudentEntity findOne(Long id);

}

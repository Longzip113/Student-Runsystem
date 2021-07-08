package com.runsystem.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.student.converter.StudentConverter;
import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.repository.StudentRepository;
import com.runsystem.student.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentConverter studentConverter;

	@Override
	public List<StudentEntity> findByName() {

		try {
//			List<StudentEntity> studentEntities = studentRepository.searchStudentByInfo("", "",
//					new SimpleDateFormat("yyyy-MM-dd").parse("2000-07-20"),
//					new SimpleDateFormat("yyyy-MM-dd").parse("2000-07-20"));

//			List<StudentEntity> studentEntities = studentRepository.searchStudentByInfo("Binh Duong");
//			Long aLong = 1l;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public StudentEntity findOne(Long id) {
		return null;
	}

}

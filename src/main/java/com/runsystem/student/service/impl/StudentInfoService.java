package com.runsystem.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.converter.StudentInfoConverter;
import com.runsystem.student.converter.StudentInputConverter;
import com.runsystem.student.dto.StudentInfoDTO;
import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.entity.StudentInfoEntity;
import com.runsystem.student.repository.StudentInfoRepository;
import com.runsystem.student.repository.StudentRepository;
import com.runsystem.student.service.IStudentInfoService;

@Service
public class StudentInfoService implements IStudentInfoService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentInfoRepository studentInfoRepository;

	@Autowired
	StudentInfoConverter studentInfoConverter;

	@Autowired
	StudentInputConverter studentInputConverter;

	@Override
	public StudentInfoDTO findByStudentID(Long id) {
		return studentInfoConverter.toDTO(studentInfoRepository.findByStudentID(id));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public StudentInfoDTO saveStudent(StudentInput studentInput) {

//		Convert data form studentInput to studentEntity
		StudentEntity studentEntity = studentInputConverter.toStudentEntity(studentInput);
		studentEntity = studentRepository.save(studentEntity);
		StudentInfoEntity studentInfoEntity = null;
//		check ID if not ID is Save, else Update
		if (studentInput.getStudentId() == null) {
//			Convert data form studentInput to StudentInfoEntity
			studentInfoEntity = studentInputConverter.toStudentInfoEntity(studentInput);
			studentInfoEntity.setStudentEntity(studentEntity);
		} else {
//			Search StudentInfoEntity by Student newly returned ID
			List<StudentInfoEntity> infoEntities = studentInfoRepository.findByStudentEntity(studentEntity);

//			Convert data form studentInput to StudentInfoEntity
			studentInfoEntity = studentInputConverter.toStudentInfoEntity(studentInput);
			studentInfoEntity.setInfoID(infoEntities.get(0).getInfoID());
		}

//		Update studentInfo and return StudentInfo edited
		StudentInfoEntity resultStudentInfoEntity = studentInfoRepository.save(studentInfoEntity);
		studentEntity = studentRepository.findOne(studentEntity.getStudentId());
		resultStudentInfoEntity.setStudentEntity(studentEntity);

		return studentInfoConverter.toDTO(resultStudentInfoEntity);
	}

}

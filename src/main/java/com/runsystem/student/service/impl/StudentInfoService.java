package com.runsystem.student.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	public List<StudentInfoDTO> searchStudentByInfo(StudentInput student) {
		List<StudentInfoDTO> lisDtos = new ArrayList<StudentInfoDTO>();
//	save results after query 
		List<StudentInfoEntity> studentInfoEntities = null;
//	save results after filtering by name
		List<StudentInfoEntity> studentInfoEntitiesByName = new ArrayList<StudentInfoEntity>();
//	save results final
		List<StudentInfoEntity> resultEntities = new ArrayList<StudentInfoEntity>();

		try {

//			Query data by studentName, studentCode, birthDay.
			studentInfoEntities = studentInfoRepository.findAllByDateOfBirth(
					new SimpleDateFormat("yyyy-MM-dd").parse(student.getBirthDay().toString()),
					new SimpleDateFormat("yyyy-MM-dd").parse(student.getBirthDay().plusDays(1).toString()));
//			student.getBirthDay().plusDays(1) Tommorow date
		} catch (ParseException e) {
			e.printStackTrace();
		}

//			Check search by StudentName and Filter list entity by StudentName
		if (StringUtils.isNotBlank(student.getStudentName())) {
			studentInfoEntitiesByName = filter(studentInfoEntities, student.getStudentName());
//			Check search by StudentCode and Filter list entity by StudentCode
			if (StringUtils.isNotBlank(student.getStudentCode())) {
				resultEntities = filter(studentInfoEntitiesByName, student.getStudentCode());
			} else {
				resultEntities = studentInfoEntitiesByName;
			}
		}
//			Check search by StudentCode and Filter list entity by StudentCode
		else if (StringUtils.isNotBlank(student.getStudentCode())) {
			resultEntities = filter(studentInfoEntities, student.getStudentCode());
		}

//			Converter list data from entity to dto
		resultEntities.forEach((n) -> {
			StudentInfoDTO itemDto = studentInfoConverter.toDTO(n);
			lisDtos.add(itemDto);
		});

		return lisDtos;
	}

//			method filter by StudentCode and StudentName of Student
	private List<StudentInfoEntity> filter(List<StudentInfoEntity> date, String item) {
		List<StudentInfoEntity> result = new ArrayList<StudentInfoEntity>();
		date.forEach((n) -> {
			if (n.getStudentEntity().getStudentName().contains(item)
					|| n.getStudentEntity().getStudentCode().contains(item)) {
				result.add(n);
			}
		});

		return result;
	}

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

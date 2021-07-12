package com.runsystem.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.converter.StudentConverter;
import com.runsystem.student.converter.StudentInfoConverter;
import com.runsystem.student.converter.StudentInputConverter;
import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.dto.StudentInfoDTO;
import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.entity.StudentInfoEntity;
import com.runsystem.student.exception.NotFoundException;
import com.runsystem.student.paging.Pageble;
import com.runsystem.student.repository.StudentInfoRepository;
import com.runsystem.student.repository.StudentRepository;
import com.runsystem.student.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentInfoRepository studentInfoRepository;

	@Autowired
	StudentConverter studentConverter;

	@Autowired
	StudentInfoConverter studentInfoConverter;

	@Autowired
	StudentInputConverter studentInputConverter;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	public List<StudentDTO> searchStudent(StudentDTO studentInput, Pageble pageble){

		List<StudentEntity> studentEntities = null;
		List<StudentDTO> lisDtos = new ArrayList<StudentDTO>();
//		Query data by studentName, studentCode, birthDay.
		try {
			if (studentInput.getDateOfBirth() == null) {
//				Query date by name and code 
				studentEntities = studentRepository.findByStudentNameLikeAndStudentCodeLike(
						studentInput.getStudentName(), studentInput.getStudentCode(), pageble.getOffset(),
						pageble.getLimit());
//				get total Item of Query date by name and code
				studentInput.setTotalItem(
						studentRepository.countItem(studentInput.getStudentName(), studentInput.getStudentCode()));
			} else {
//				Query date by name, code and date
				studentEntities = studentRepository.searchStudentByInfo(studentInput.getStudentName(),
						studentInput.getStudentCode(),
						new SimpleDateFormat("yyyy-MM-dd").parse(studentInput.getDateOfBirth().toString()),
						pageble.getOffset(), pageble.getLimit());
//				get total Item of Query date by name, code and date
				studentInput.setTotalItem(
						studentRepository.countItem(studentInput.getStudentName(), studentInput.getStudentCode(),
								new SimpleDateFormat("yyyy-MM-dd").parse(studentInput.getDateOfBirth().toString())));
			}
		} catch (java.text.ParseException ex) {
			throw new NotFoundException(resourceBundle.getString("DOES_EXIST"));
		}
		
		if(studentEntities == null) {
			throw new NotFoundException(resourceBundle.getString("DOES_EXIST"));
		}

//		Converter list data from entity to dto
		studentEntities.forEach((n) -> {
			StudentDTO itemDto = studentConverter.toDTO(n);
			lisDtos.add(itemDto);
		});

		return lisDtos;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Boolean deleteStudent(Long id) {
//		Query student get id StudentInfo
		StudentEntity studentEntity = studentRepository.findOne(id);
		
//		Delete student and StudentInfo
		studentInfoRepository.delete(studentEntity.getStudentInfoEntity().getInfoID());

//		Check delete
		studentEntity = studentRepository.findOne(id);
		if (studentEntity != null) {
			throw new NotFoundException(resourceBundle.getString("DELETE_FAILURE"));
		}
		return true;
	}

	@Override
	public StudentInfoDTO findByStudentID(Long id) {
		StudentInfoDTO result = studentInfoConverter.toDTO(studentInfoRepository.findByStudentID(id));
		if (result != null) {
			return result;
		}
		throw new NotFoundException(resourceBundle.getString("DOES_EXIST"));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public StudentInfoDTO saveAndUpdateStudent(StudentInput studentInput) {

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

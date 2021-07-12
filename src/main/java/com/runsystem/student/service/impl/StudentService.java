package com.runsystem.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.student.converter.StudentConverter;
import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.entity.StudentEntity;
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

	@Override
	public List<StudentDTO> searchStudent(StudentDTO studentInput, Pageble pageble) {

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
		} catch (Exception e) {
			e.printStackTrace();
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
			return false;
		}
		return true;
	}

}

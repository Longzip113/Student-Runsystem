package com.runsystem.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.converter.StudentConverter;
import com.runsystem.student.dto.StudentDTO;
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
	public List<StudentDTO> searchStudent(StudentInput studentInput) {

		List<StudentEntity> studentEntities = null;
		List<StudentDTO> lisDtos = new ArrayList<StudentDTO>();
//		Query data by studentName, studentCode, birthDay.
		try {
			studentEntities = studentRepository.searchStudentByInfo("", "",
					new SimpleDateFormat("yyyy-MM-dd").parse(studentInput.getBirthDay().toString()),
					new SimpleDateFormat("yyyy-MM-dd").parse(studentInput.getBirthDay().plusDays(1).toString()));
//			student.getBirthDay().plusDays(1) Tommorow date
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
	public StudentEntity findOne(Long id) {
		return null;
	}

}

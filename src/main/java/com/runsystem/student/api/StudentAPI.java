package com.runsystem.student.api;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runsystem.student.api.input.StudentInput;
import com.runsystem.student.api.output.DataResponse;
import com.runsystem.student.constant.ConstantSystem;
import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.dto.StudentInfoDTO;
import com.runsystem.student.service.IStudentInfoService;
import com.runsystem.student.service.IStudentService;

@Validated
@RestController
@RequestMapping("api/")
public class StudentAPI {

	@Autowired
	IStudentInfoService studentInfoService;
	
	@Autowired
	IStudentService studentService;

	ConstantSystem constantSystem;

	@SuppressWarnings("static-access")
	@GetMapping(value = "students")
	public DataResponse<List<StudentDTO>> getAll(@RequestParam(required = false) @Size(max = 10) String studentCode,
			@RequestParam(required = false) @Size(max = 20) String studentName,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

		StudentInput studentSearchInput = new StudentInput();

//		isNotBlank = (null) and (isEmpty)
		studentSearchInput.setStudentCode(StringUtils.isNotBlank(studentCode) ? studentCode : null);
		studentSearchInput.setStudentName(StringUtils.isNotBlank(studentName) ? studentName : null);

//		Convert Date from LocalDate to Date
		studentSearchInput.setBirthDay(date);

//		Query by field
		List<StudentDTO> studentDTOs = studentService.searchStudent(studentSearchInput);

		return new DataResponse<List<StudentDTO>>(constantSystem.SUCCESS, studentDTOs, LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@GetMapping(value = "students/{id}")
	public DataResponse<StudentInfoDTO> getOneStudent(@PathVariable Long id) {

//		Query by id of student
		StudentInfoDTO studentDTO = studentInfoService.findByStudentID(id);
		return new DataResponse<StudentInfoDTO>(constantSystem.SUCCESS, studentDTO, LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PutMapping(value = "students")
	public DataResponse<StudentInfoDTO> updateStudent(@RequestBody StudentInput student) {
		StudentInfoDTO studentDTO = studentInfoService.saveStudent(student);
		return new DataResponse<StudentInfoDTO>(constantSystem.SUCCESS, studentDTO, LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PostMapping(value = "students")
	public DataResponse<StudentInfoDTO> saveStudent(@RequestBody StudentInput student) {
		StudentInfoDTO studentDTO = studentInfoService.saveStudent(student);
		return new DataResponse<StudentInfoDTO>(constantSystem.SUCCESS, studentDTO, LocalDateTime.now().toString());
	}

}

package com.runsystem.student.api;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

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
import com.runsystem.student.dto.StudentDTO;
import com.runsystem.student.dto.StudentInfoDTO;
import com.runsystem.student.paging.PageRequest;
import com.runsystem.student.paging.Pageble;
import com.runsystem.student.service.IStudentInfoService;
import com.runsystem.student.service.IStudentService;
import com.runsystem.student.utils.ConvertDate;
import com.runsystem.student.utils.Sorter;

@Validated
@RestController
@RequestMapping("api/")
public class StudentAPI {

	@Autowired
	IStudentInfoService studentInfoService;

	@Autowired
	IStudentService studentService;

	ConvertDate convertDate;

	Sorter sorter;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@SuppressWarnings("static-access")
	@GetMapping(value = "students")
	public DataResponse<StudentDTO> getAll(@RequestParam(required = false) @Size(max = 10) String studentCode,
			@RequestParam(required = false) @Size(max = 20) String studentName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
			@RequestParam(required = false) String sortName, @RequestParam(required = false) String sortBy) {

		StudentDTO studentSearch = new StudentDTO();

//		isNotBlank = (null) and (isEmpty)
		studentSearch.setStudentCode(StringUtils.isNotBlank(studentCode) ? studentCode : "");
		studentSearch.setStudentName(StringUtils.isNotBlank(studentName) ? studentName : "");
		studentSearch.setPage(page);
		studentSearch.setMaxPageItem(size);
		studentSearch.setSortBy(sortBy);
		studentSearch.setSortName(sortName);

//		Convert Date from Date to LocalDate
		if (date != null) {
			studentSearch.setDateOfBirth(convertDate.toLocalDate(date));
		}
//		set parameter pagination
		Pageble pageble = new PageRequest(studentSearch.getPage(), studentSearch.getMaxPageItem());

//		Query by field and Sort result
		sorter = new Sorter(sortName, sortBy, studentService.searchStudent(studentSearch, pageble));
		studentSearch.setListResult(sorter.sort());
		studentSearch
				.setTotalPage((int) Math.ceil((double) studentSearch.getTotalItem() / studentSearch.getMaxPageItem()));

		return new DataResponse<StudentDTO>(resourceBundle.getString("SUCCESS"), studentSearch,
				LocalDateTime.now().toString());
	}

	@GetMapping(value = "students/{id}")
	public DataResponse<StudentInfoDTO> getOneStudent(@PathVariable Long id) {
//		Query by id of student
		StudentInfoDTO studentDTO = studentInfoService.findByStudentID(id);
		return new DataResponse<StudentInfoDTO>(resourceBundle.getString("SUCCESS"), studentDTO,
				LocalDateTime.now().toString());
	}

	@PutMapping(value = "students")
	public DataResponse<StudentInfoDTO> updateStudent(@RequestBody StudentInput student) {
		StudentInfoDTO studentDTO = studentInfoService.saveStudent(student);
		return new DataResponse<StudentInfoDTO>(resourceBundle.getString("SUCCESS"), studentDTO,
				LocalDateTime.now().toString());
	}

	@PostMapping(value = "students")
	public DataResponse<StudentInfoDTO> saveStudent(@RequestBody StudentInput student) {
		StudentInfoDTO studentDTO = studentInfoService.saveStudent(student);
		return new DataResponse<StudentInfoDTO>(resourceBundle.getString("SUCCESS"), studentDTO,
				LocalDateTime.now().toString());
	}
}

package com.runsystem.student.dto;

public class StudentDTO {
	
	private Long studentId;
	
	private String studentName;
	
	private String studentCode; 
	
    private StudentInfoDTO studentInfoEntity;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public StudentInfoDTO getStudentInfoEntity() {
		return studentInfoEntity;
	}

	public void setStudentInfoEntity(StudentInfoDTO studentInfoEntity) {
		this.studentInfoEntity = studentInfoEntity;
	}
}

package com.runsystem.student.dto;

import java.util.Date;

public class StudentInfoDTO {
	
	private Long infoID;
	
	private Long studentId;
	
	private String address;
	
	private Double averageScore;
	
	private Date dateOfBirth;
	
    private StudentDTO studentEntity;

	public Long getInfoID() {
		return infoID;
	}

	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public StudentDTO getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentDTO studentEntity) {
		this.studentEntity = studentEntity;
	}
}

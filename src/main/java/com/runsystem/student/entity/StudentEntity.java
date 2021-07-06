package com.runsystem.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id //(định nghia khoa chinh va not null)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //id tự động tăng
	private Long studentId;
	
	@Column(length = 20, nullable = false)
	private String studentName;
	
	@Column(length = 10, nullable = false)
	private String studentCode; 
	
	@OneToOne(mappedBy = "studentEntity")
    private StudentInfoEntity studentInfoEntity;

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

	public StudentInfoEntity getStudentInfoEntity() {
		return studentInfoEntity;
	}

	public void setStudentInfoEntity(StudentInfoEntity studentInfoEntity) {
		this.studentInfoEntity = studentInfoEntity;
	}
}

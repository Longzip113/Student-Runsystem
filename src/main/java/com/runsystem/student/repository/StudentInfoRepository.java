package com.runsystem.student.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.runsystem.student.entity.StudentEntity;
import com.runsystem.student.entity.StudentInfoEntity;

public interface StudentInfoRepository extends JpaRepository<StudentInfoEntity, Long>{
	
	@Query(value = "select stif.* from student_info stif where stif.date_of_birth > :date and stif.date_of_birth < :date1", nativeQuery = true)
	List<StudentInfoEntity> findAllByDateOfBirth(@Param("date")Date dateOfBirth, @Param("date1")Date dateOfBirth1);
	
	@Query(value = "select stif.* from student_info stif where stif.student_id = :id", nativeQuery = true)
	StudentInfoEntity findByStudentID(@Param("id")Long id);
	
	List<StudentInfoEntity> findByStudentEntity(StudentEntity studentEntity);
}

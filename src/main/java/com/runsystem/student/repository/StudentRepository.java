package com.runsystem.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.runsystem.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
//	@Query(value = "select st from student st JOIN st.student_info stif \n"
//			+ "where st.student_name  LIKE %:name% and st.student_code LIKE %:code% \n"
//			+ "and stif.date_of_birth > :date and stif.date_of_birth < :date1", nativeQuery = true)
//	List<StudentEntity> searchStudentByInfo(@Param("name") String studentname, @Param("code") String studentcode,
//			@Param("date") Date dateOfBirth, @Param("date1") Date dateOfBirth1);
	
	@Query(value = "select st.* from student st ", nativeQuery = true)
	List<StudentEntity> searchStudentByInfo( String address);
}

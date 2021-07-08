package com.runsystem.student.repository;

import java.util.Date;
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
	
	@Query(value = "SELECT * \n"
			+ "FROM student st\n"
			+ "INNER JOIN student_info stif ON st.student_id = stif.student_id\n"
			+ "WHERE st.student_name like %?1 and st.student_code like %?2 and stif.date_of_birth > ?3 and stif.date_of_birth < ?4", nativeQuery = true)
	List<StudentEntity> searchStudentByInfo(String studentname, String studentcode,
			Date dateOfBirth, Date tommorow);
}

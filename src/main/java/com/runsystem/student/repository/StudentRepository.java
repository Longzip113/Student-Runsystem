package com.runsystem.student.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.runsystem.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	@Query(value = "SELECT * \n" + "FROM student st\n"
			+ "INNER JOIN student_info stif ON st.student_id = stif.student_id\n"
			+ "WHERE st.student_name like %?1 and st.student_code like %?2 and stif.date_of_birth = ?3 LIMIT ?4, ?5", nativeQuery = true)
	List<StudentEntity> searchStudentByInfo(String studentname, String studentcode, Date dateOfBirth, Integer offset,
			Integer limit);

	@Query(value = "SELECT * \n" + "FROM student st\n"
			+ "WHERE st.student_name like %?1 and st.student_code like %?2 LIMIT ?3, ?4", nativeQuery = true)
	List<StudentEntity> findByStudentNameLikeAndStudentCodeLike(String studentname, String studentcode, Integer offset,
			Integer limit);
	
	@Query(value = "SELECT count(*) \n" + "FROM student st\n"
			+ " INNER JOIN student_info stif ON st.student_id = stif.student_id\n"
			+ " WHERE st.student_name like %?1 and st.student_code like %?2 and stif.date_of_birth = ?3", nativeQuery = true)
	Integer countItem(String studentname, String studentcode, Date dateOfBirth);
	
	@Query(value = "SELECT count(*) \n" + "FROM student st\n"
			+ " WHERE st.student_name like %?1 and st.student_code like %?2", nativeQuery = true)
	Integer countItem(String studentname, String studentcode);
	
	

}

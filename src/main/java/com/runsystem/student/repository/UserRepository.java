package com.runsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.runsystem.student.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	@Query(value = "select c.* from user c where c.email = :email", nativeQuery = true)
	UserEntity findByEmail(@Param("email")String email);
	
	@Query(value = "select c.* from user c where c.email = :email and pass_word = :passwork", nativeQuery = true)
	UserEntity findByEmailAndPasswork(@Param("email")String email, @Param("passwork")String passwork);
}

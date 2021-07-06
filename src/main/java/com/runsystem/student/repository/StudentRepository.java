package com.runsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runsystem.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

}

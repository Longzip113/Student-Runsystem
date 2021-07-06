package com.runsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runsystem.student.entity.StudentInfoEntity;

public interface StudentInfoRepository extends JpaRepository<StudentInfoEntity, Long>{

}

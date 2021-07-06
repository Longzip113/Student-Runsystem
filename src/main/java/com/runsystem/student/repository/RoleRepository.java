package com.runsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runsystem.student.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}

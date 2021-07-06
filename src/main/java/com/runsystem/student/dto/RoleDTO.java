package com.runsystem.student.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
	
	private Long roleId;
	
	private String roleName;
	
	private String roleCode;
	
	private List<UserDTO> userEntities = new ArrayList<>();

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<UserDTO> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserDTO> userEntities) {
		this.userEntities = userEntities;
	}
	
	

}

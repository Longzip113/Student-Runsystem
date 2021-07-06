package com.runsystem.student.api.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class UserInput {
	
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Enter the correct email format")
	private String email;
	
	@NotNull(message = "PassWord cannot be null")
	@Size(min = 6, max = 15, message = "Password be between 6 and 15 characters")
	private String passWord;
	
	@NotNull(message = "PassWord Confirm cannot be null")
	@Size(min = 6, max = 15, message = "Password Confirm be between 6 and 15 characters")
	private String passWordConfirm;

	public String getPassWordConfirm() {
		return passWordConfirm;
	}

	public void setPassWordConfirm(String passWordConfirm) {
		this.passWordConfirm = passWordConfirm;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

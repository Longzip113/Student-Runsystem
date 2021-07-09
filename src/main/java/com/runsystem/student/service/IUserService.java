package com.runsystem.student.service;

import java.util.List;

import com.runsystem.student.api.input.UserInput;
import com.runsystem.student.api.input.UserRegisterInput;
import com.runsystem.student.dto.UserDTO;

public interface IUserService {
	UserDTO loadUserByEmail(String Email);
	UserDTO checkLogin(UserInput user);
	List<UserDTO> findAll();
	UserDTO registerUser(UserRegisterInput user);
}

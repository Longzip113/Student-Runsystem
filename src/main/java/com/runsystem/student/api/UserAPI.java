package com.runsystem.student.api;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runsystem.student.api.input.UserInput;
import com.runsystem.student.api.input.UserRegisterInput;
import com.runsystem.student.api.output.DataResponse;
import com.runsystem.student.constant.ConstantSystem;
import com.runsystem.student.dto.UserDTO;
import com.runsystem.student.service.IUserService;
import com.runsystem.student.service.impl.JwtService;

@RestController
@RequestMapping("api/")
public class UserAPI {

	@Autowired
	private JwtService jwtService;

	@Autowired
	IUserService service;

	ConstantSystem constantSystem;

	@SuppressWarnings("static-access")
	@GetMapping(value = "users")
	public DataResponse<List<UserDTO>> getAll() {
		return new DataResponse<List<UserDTO>>(constantSystem.SUCCESS, service.findAll(),
				LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PostMapping(value = "login")
	public DataResponse<UserDTO> login(HttpServletRequest request, @Valid @RequestBody UserInput user) {
		String result = "";
		UserDTO dto = null;
		try {
			// ON login in system
			dto = service.checkLogin(user);
			if (dto != null) {
				result = constantSystem.TOKEN_SUCCESS + jwtService.generateTokenLogin(user.getEmail());
			} else {
				result = constantSystem.WRONG_EMAIL_PASSWORD;
			}
		} catch (Exception ex) {
			result = constantSystem.SERVER_ERROR;
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PostMapping(value = "register")
	public DataResponse<UserDTO> register(HttpServletRequest request, @Valid @RequestBody UserRegisterInput user) {

		// Check password confirm and password
		if (!user.getPassWord().equals(user.getPassWordConfirm())) {
			return new DataResponse<UserDTO>(constantSystem.PASSWORD_PASSWORKCONFIRM, null,
					LocalDateTime.now().toString());
		}

		String result = "";
		UserDTO dto = null;
		try {
			// perform user registration and check if the user already exists.
			dto = service.registerUser(user);
			if (dto != null) {
				result = constantSystem.REGISTER_SUCCESS;
			} else {
				result = constantSystem.ACCOUNT_ALREADY;
			}
		} catch (Exception ex) {
			result = constantSystem.SERVER_ERROR;
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

}

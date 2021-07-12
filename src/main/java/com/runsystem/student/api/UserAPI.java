package com.runsystem.student.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.runsystem.student.dto.UserDTO;
import com.runsystem.student.exception.NotFoundException;
import com.runsystem.student.service.IUserService;
import com.runsystem.student.service.impl.JwtService;

@RestController
@RequestMapping("api/")
public class UserAPI {

	@Autowired
	private JwtService jwtService;

	@Autowired
	IUserService service;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@GetMapping(value = "users")
	public DataResponse<List<UserDTO>> getAllUserAPI() {
		return new DataResponse<List<UserDTO>>(resourceBundle.getString("SUCCESS"), service.findAllUser(),
				LocalDateTime.now().toString());
	}

	@PostMapping(value = "login")
	public DataResponse<UserDTO> loginAPI(HttpServletRequest request, @Valid @RequestBody UserInput user) {
		String result = "";
		UserDTO dto = null;

		// Get the session if exists or create a new one.
		HttpSession session = request.getSession(true);

		try {
			// ON login in system
			dto = service.checkLogin(user);
			if (dto != null) {
				result = resourceBundle.getString("TOKEN_SUCCESS") + jwtService.generateTokenLogin(user.getEmail());
				// Set session attributes
				session.setAttribute("user", dto);
			} else {
				throw new NotFoundException(resourceBundle.getString("WRONG_EMAIL_PASSWORD"));
			}
		} catch (Exception ex) {
			result = resourceBundle.getString("SERVER_ERROR");
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

	@PostMapping(value = "register")
	public DataResponse<UserDTO> registerAPI(HttpServletRequest request, @Valid @RequestBody UserRegisterInput user) {

		// Check password confirm and password
		if (!user.getPassWord().equals(user.getPassWordConfirm())) {
			throw new NotFoundException(resourceBundle.getString("PASSWORD_PASSWORKCONFIRM"));
		}
		String result = "";
		UserDTO dto = null;
		try {
			// perform user registration and check if the user already exists.
			dto = service.registerUser(user);
			if (dto != null) {
				result = resourceBundle.getString("REGISTER_SUCCESS");
			} else {
				result = resourceBundle.getString("ACCOUNT_ALREADY");
			}
		} catch (Exception ex) {
			throw new NotFoundException("SERVER_ERROR");
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

	@GetMapping(value = "logout")
	public DataResponse<Boolean> logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
//		Remove session 
		session.removeAttribute("user");
//		Change code Token when logout
		jwtService.generateTokenLogin("");
		return new DataResponse<Boolean>(resourceBundle.getString("SUCCESS"), true, LocalDateTime.now().toString());
	}

}

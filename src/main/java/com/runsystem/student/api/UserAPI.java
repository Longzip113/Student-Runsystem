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
import com.runsystem.student.api.output.DataResponse;
import com.runsystem.student.constant.MessageSystem;
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

	MessageSystem messageSystem;

	@SuppressWarnings("static-access")
	@GetMapping(value = "users")
	public DataResponse<List<UserDTO>> getAll() {
		service.findAll();
		return new DataResponse<List<UserDTO>>(messageSystem.SUCCESS, service.findAll(),
				LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PostMapping(value = "login")
	public DataResponse<UserDTO> login(HttpServletRequest request, @Valid @RequestBody UserInput user) {
		String result = "";
		UserDTO dto = null;
		try {
			// Thực hiện login vào hệ thống
			dto = service.checkLogin(user);
			if (dto != null) {
				result = messageSystem.TOKEN_SUCCESS + jwtService.generateTokenLogin(user.getEmail());
			} else {
				result = messageSystem.WRONG_EMAIL_PASSWORD;
			}
		} catch (Exception ex) {
			result = messageSystem.SERVER_ERROR;
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

	@SuppressWarnings("static-access")
	@PostMapping(value = "register")
	public DataResponse<UserDTO> register(HttpServletRequest request, @Valid @RequestBody UserInput user) {

		// Check passwork confirm voi passwork
		if (!user.getPassWord().equals(user.getPassWordConfirm())) {
			return new DataResponse<UserDTO>(messageSystem.PASSWORD_PASSWORKCONFIRM, null,
					LocalDateTime.now().toString());
		}

		String result = "";
		UserDTO dto = null;
		try {
			
			//thực hiện đăng ký user và kiểm tra user đã tồn tại chưa.
			dto = service.registerUser(user);
			if (dto != null) {
				result = messageSystem.REGISTER_SUCCESS;
			} else {
				result = messageSystem.ACCOUNT_ALREADY;
			}
			
		} catch (Exception ex) {
			result = messageSystem.SERVER_ERROR;
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

}

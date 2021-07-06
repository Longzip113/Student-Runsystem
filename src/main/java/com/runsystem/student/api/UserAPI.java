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
	
	@GetMapping(value = "users")
	public DataResponse<List<UserDTO>> getAll() {
		service.findAll();
		return new DataResponse<List<UserDTO>>("Success", service.findAll(), LocalDateTime.now().toString());
	}
	
	@PostMapping(value = "login")
	public DataResponse<UserDTO> login(HttpServletRequest request,@Valid @RequestBody UserInput user) {		
		String result = "";
		UserDTO dto = null;
		try {
			dto = service.checkLogin(user);
			if (dto != null) {
				result = "Success ! Token: " + jwtService.generateTokenLogin(user.getEmail());
			} else {
				result = "Wrong userId and password !";
			}
		} catch (Exception ex) {
			result = "Server Error !";
		}
		return new DataResponse<UserDTO>(result, dto, LocalDateTime.now().toString());
	}

}

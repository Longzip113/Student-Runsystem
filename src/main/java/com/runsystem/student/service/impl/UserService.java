package com.runsystem.student.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.student.api.input.UserInput;
import com.runsystem.student.api.input.UserRegisterInput;
import com.runsystem.student.converter.UserConverter;
import com.runsystem.student.dto.UserDTO;
import com.runsystem.student.entity.RoleEntity;
import com.runsystem.student.entity.UserEntity;
import com.runsystem.student.exception.NotFoundException;
import com.runsystem.student.repository.RoleRepository;
import com.runsystem.student.repository.UserRepository;
import com.runsystem.student.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private UserConverter userConverter;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	public UserDTO loadUserByEmail(String Email) {
		UserEntity userEntity = userRepository.findByEmail(Email);

		if (userEntity != null) {
			return userConverter.toDTO(userEntity);
		}
		throw new NotFoundException(resourceBundle.getString("DOES_EXIST"));
	}

	@Override
	public UserDTO checkLogin(UserInput user) {
		UserEntity userEntity = userRepository.findByEmailAndPasswork(user.getEmail(), user.getPassWord());
		
		if (userEntity != null) {
			return userConverter.toDTO(userEntity);
		}
		throw new NotFoundException(resourceBundle.getString("WRONG_EMAIL_PASSWORD"));
	}

	@Override
	public List<UserDTO> findAllUser() {
		
		List<UserEntity> listUser = userRepository.findAll();
		List<UserDTO> listDtos = new ArrayList<UserDTO>();
		
		if (listUser != null) {
			listUser.forEach( (n) -> { 
				UserDTO itemDto = userConverter.toDTO(n);
				listDtos.add(itemDto);
			});
			
			return listDtos;
		}
		throw new NotFoundException(resourceBundle.getString("DOES_NOT_USER"));
		
	}
	
	// Check if the registered User already exists
	private Boolean isUserByEmail(String email)
	{
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserDTO registerUser(UserRegisterInput user) {
		if (isUserByEmail(user.getEmail())) {
			throw new NotFoundException(resourceBundle.getString("USER_ALREADY"));
		} else {
			
			// get the user role and assign it to the user
			RoleEntity roleEntity = roleRepository.findOne(2L);
			
			// Save the User to the DB and return the newly added user to the client
			UserEntity entity = new UserEntity();
			entity.setEmail(user.getEmail());
			entity.setPassWord(user.getPassWord());
			entity.setRoleEntity(roleEntity);
			return userConverter.toDTO(userRepository.save(entity));
		}
	}
}

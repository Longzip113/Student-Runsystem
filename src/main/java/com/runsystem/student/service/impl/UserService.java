package com.runsystem.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.student.api.input.UserInput;
import com.runsystem.student.converter.UserConverter;
import com.runsystem.student.dto.UserDTO;
import com.runsystem.student.entity.UserEntity;
import com.runsystem.student.repository.UserRepository;
import com.runsystem.student.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserConverter userConverter;

	@Override
	public UserDTO loadUserByEmail(String Email) {
		UserEntity userEntity = userRepository.findByEmail(Email);

		if (userEntity != null) {
			return userConverter.toDTO(userEntity);
		}
		return null;
	}

	@Override
	public UserDTO checkLogin(UserInput user) {
		
		UserEntity userEntity = userRepository.findByEmailAndPasswork(user.getEmail(), user.getPassWord());
		
		if (userEntity != null) {
			return userConverter.toDTO(userEntity);
		}
		return null;
	}

	@Override
	public List<UserDTO> findAll() {
		
		List<UserEntity> listUser = userRepository.findAll();
		List<UserDTO> listDtos = new ArrayList<UserDTO>();
		
		listUser.forEach( (n) -> { 
			UserDTO itemDto = userConverter.toDTO(n);
			listDtos.add(itemDto);
		});
		
		
		return listDtos;
	}
}

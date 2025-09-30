package com.alirizakaygusuz.exception_poc.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.exception_poc.user.dto.UserRequest;
import com.alirizakaygusuz.exception_poc.user.dto.UserResponse;
import com.alirizakaygusuz.exception_poc.user.exception.EmailAlreadyExistsException;
import com.alirizakaygusuz.exception_poc.user.exception.UserNotFoundException;
import com.alirizakaygusuz.exception_poc.user.exception.UsernameAlreadyExistsException;
import com.alirizakaygusuz.exception_poc.user.model.User;
import com.alirizakaygusuz.exception_poc.user.repository.UserRepository;
import com.alirizakaygusuz.exception_poc.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

    

	@Override
	public UserResponse createUser(UserRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new UsernameAlreadyExistsException(request.getUsername());
		}
		
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException(request.getEmail());
		}

		User user = modelMapper.map(request, User.class);

		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser , UserResponse.class);
	}

	@Override
	public UserResponse getUserById(long id) {
		User user = findUserByIdOrThrowException(id);
		
		
		return modelMapper.map(user,UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserResponse> userResponses = new ArrayList<>();
		for(User user: users) {
			userResponses.add(modelMapper.map(user , UserResponse.class));
		}
		
		return userResponses;
	}

	@Override
	public UserResponse updateUser(long id, UserRequest request) {
		User user = findUserByIdOrThrowException(id);
		modelMapper.map(request , user);
		userRepository.save(user);
		
		return modelMapper.map(user , UserResponse.class);
	}

	@Override
	public void deleteUserById(long id) {
		User user = findUserByIdOrThrowException(id);
		
		userRepository.delete(user);
	}
	
	private User findUserByIdOrThrowException(long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

}

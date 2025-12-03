package com.car_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car_backend.dto.CreateUserDto;
import com.car_backend.dto.UpdateUserDto;
import com.car_backend.dto.UserResponseDto;
import com.car_backend.entities.Role;
import com.car_backend.entities.User;
import com.car_backend.exceptions.ResourceAlreadyExists;
import com.car_backend.exceptions.ResourceNotFoundException;
import com.car_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder encoder;

	@Override
	public UserResponseDto createUser(CreateUserDto dto) {
		if (userRepo.existsByEmail(dto.getEmail())) {
			throw new ResourceAlreadyExists("user already exists..");
		}
		User entity = mapper.map(dto, User.class);
		entity.setPassword(encoder.encode(dto.getPassword()));

		if (dto.getUserRole() == Role.MECHANIC && dto.getManagerId() != null) {
			User manager = userRepo.findById(dto.getManagerId())
					.orElseThrow(() -> new ResourceNotFoundException("Manager not found."));
			entity.setManager(manager);
		}
		User savedUser = userRepo.save(entity);

		return mapUserToResponseDto(savedUser);

	}

	@Override
	public List<UserResponseDto> getUsers() {
		List<User> users = userRepo.findAll();

		return users.stream().map(this::mapUserToResponseDto).collect(Collectors.toList());
	}

	@Override
	public UserResponseDto getUserById(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with specified id not found."));

		return mapUserToResponseDto(user);
	}

	@Override
	public UserResponseDto updateUser(Long targetUserId, UpdateUserDto dto) {
		User user = userRepo.findById(targetUserId)
				.orElseThrow(() -> new ResourceNotFoundException("user with specified id not found."));

		if (dto.getUserName() != null)
			user.setUserName(dto.getUserName());
		
		if (dto.getMobile() != null)
			user.setMobile(dto.getMobile());
		
		if (dto.getUserRole() != null)
			user.setUserRole(dto.getUserRole());
		
		if (dto.getSalary() != null)
			user.setSalary(dto.getSalary());
		
		if (dto.getIsActive() != null)
			user.setActive(dto.getIsActive());
		
		if (dto.getManagerId() != null) {
			User newManager = userRepo.findById(dto.getManagerId())
					.orElseThrow(() -> new ResourceNotFoundException("manager with specified id not found."));
			user.setManager(newManager);
		}
		
		User savedUser = userRepo.save(user);
		return mapUserToResponseDto(savedUser);
	}
	
	
	
	@Override
	public void deleteUser(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with specified id not found."));
		
		user.setActive(false);
		userRepo.save(user);
		
	}
	
	
	@Override
	public List<UserResponseDto> findActiveUsers() {
		List<User> activeUsers = userRepo.findByIsActiveTrue();
		
		return activeUsers.stream().map(this::mapUserToResponseDto).collect(Collectors.toList());
	}
	
	
	
	private UserResponseDto mapUserToResponseDto(User user) {
		UserResponseDto response = mapper.map(user, UserResponseDto.class);
		response.setUserId(user.getId());
		if (user.getManager() != null) {
			response.setManagerId(user.getManager().getId());
			response.setManagerName(user.getManager().getUserName());
		}
		return response;
	}

	

	

}

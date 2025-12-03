package com.car_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_backend.dto.CreateUserDto;
import com.car_backend.dto.UpdateUserDto;
import com.car_backend.dto.UserResponseDto;
import com.car_backend.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class UserController {
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto dto) {
		UserResponseDto created = userService.createUser(dto);
		System.out.println(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers() {
		System.out.println("in user controller get users");

		return ResponseEntity.ok(userService.getUsers());

	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<?> findById(@PathVariable Long userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto dto){
		return ResponseEntity.ok(userService.updateUser(userId, dto));
	}
	
	@DeleteMapping("{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId ){
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveUsers(){
		return ResponseEntity.ok(userService.findActiveUsers());
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

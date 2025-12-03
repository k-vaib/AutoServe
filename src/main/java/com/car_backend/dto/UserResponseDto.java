package com.car_backend.dto;

import com.car_backend.entities.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDto {
	private Long userId;
	private String userName;
	private String email;
	private Role userRole;
	private String mobile;
	private Boolean isActive;
	
	
	private String managerName;
	private Long managerId;
}

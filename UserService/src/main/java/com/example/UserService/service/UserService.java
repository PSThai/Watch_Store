package com.example.UserService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.UserService.entity.LoginUser;
import com.example.UserService.entity.User;

public interface UserService {
	
	User Register(User user);
	String login(LoginUser login);
	List<User> getAllUser();
}

	

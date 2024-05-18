package com.example.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.entity.LoginUser;
import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;

@RestController
public class Controller {

	 @Autowired
	    private UserService userService;
	
	 @PostMapping("/register")
	    public User register(@RequestBody User user){
	        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

	        return userService.Register(user);
	    }

	 @PostMapping("/login")
	    public String login(@RequestBody LoginUser login) {
	        // Gọi phương thức login từ service và trả về JWT token (nếu đăng nhập thành công)
	        return userService.login(login);
	    }
	 @GetMapping("/api/user")
	    public List<User> getAllAccounts() {
	        return userService.getAllUser();
	    }

}

package com.example.UserService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserService.entity.LoginUser;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.utils.Utils;

@Service
public class UserServiceIml implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired(required = true)
	private Utils jwtUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User Register(User user) {

		return userRepository.saveAndFlush(user);
	}

	public List<User> getAllUser() {

		String query = "SELECT  user_name, password FROM users";
		return jdbcTemplate.query(query, (rs, rowNum) -> {
			User user = new User();
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			return user;
		});

	}

	public String login(LoginUser login) {
		// Lấy danh sách tất cả người dùng từ phương thức getAllUser
		List<User> users = getAllUser();

		// Tiếp tục xử lý đăng nhập dựa trên danh sách người dùng đã nhận được
		for (User user : users) {
			if (user.getUserName().equals(login.getUserName())
					&& new BCryptPasswordEncoder().matches(login.getPassword(), user.getPassword())) {
				// Đăng nhập thành công: Tạo và trả về JWT token
				return jwtUtils.createJwtToken(user.getUserName());
			}
		}
		return "Thông tin đăng nhập không chính xác";
	}

}

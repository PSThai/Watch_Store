package com.example.UserService.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Token {
	
	private String token;
	
	private User user;
	
	
	@Override
	public String toString() {
		return "Token [token=" + token + ", user=" + user + "]";
	}
	
	
	
}

package com.tus.easyfare.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

	private Integer userId;

	public UserNotFoundException(Integer userId) {
		super();
		this.userId = userId;
	}
	
}

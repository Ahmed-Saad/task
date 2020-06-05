package com.example.task.service;

import com.example.task.models.User;

public interface UserService {

	public User addUser(User user);
	public User getUserById(Long id);
}

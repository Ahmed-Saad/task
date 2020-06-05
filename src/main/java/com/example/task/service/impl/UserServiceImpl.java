package com.example.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.task.models.User;
import com.example.task.repositories.UserRepository;
import com.example.task.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User getUserById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

}

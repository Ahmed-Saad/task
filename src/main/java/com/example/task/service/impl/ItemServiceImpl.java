package com.example.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.task.models.Item;
import com.example.task.repositories.ItemRepository;
import com.example.task.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository repo;

	@Override
	public Item getItemById(Long id) {
		return repo.findById(id).get();
	}

}

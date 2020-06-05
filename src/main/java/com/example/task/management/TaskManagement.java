package com.example.task.management;

import java.util.List;

import com.example.task.models.Item;
import com.example.task.models.User;

public interface TaskManagement {

	public User getUserById(Long id);
	public Item getItemById(Long id);
	public User createUser(User user);
	public double createBill(List<Item> itemList, Long userId);
}

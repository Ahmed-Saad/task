package com.example.task.management.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.task.management.TaskManagement;
import com.example.task.models.Item;
import com.example.task.models.User;
import com.example.task.service.ItemService;
import com.example.task.service.UserService;

@Component
public class TaskManagementImpl implements TaskManagement {

	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;

	@Override
	public User getUserById(Long id) {
		return userService.getUserById(id);
	}

	@Override
	public Item getItemById(Long id) {
		return itemService.getItemById(id);
	}

	private double doDiscount(double amount, User userType) {
		switch (userType.getType().getType()) {
		case "Employee":
			double ePercent = amount * 0.30;
			return amount - ePercent;
		case "Affiliate":
			double aPercent = amount * 0.10;
			return amount - aPercent;
		case "Customer":
			Date dateBefor2Years = Date
					.from(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
			if (userType.getCreationDate().before(dateBefor2Years)) {
				double cPercent = amount * 0.5;
				return amount - cPercent;
			}
		default:
			return 0;
		}
	}

	@Override
	public double createBill(List<Item> itemList, Long userId) {
		int itemsCount = itemList.size();
		User user = getUserById(userId);
		double billTotal = itemList.stream().mapToDouble(i -> i.getPrice()).sum();
		itemList.removeIf(item -> item.getType().getType().equals("Groceries"));
		double billAmount = billTotal;
		if (itemList.size() != itemsCount) {
			// RemoveGroceriesAmountFromBill
			billAmount = itemList.stream().mapToDouble(i -> i.getPrice()).sum();
		}
		double amountAfterDiscount = doDiscount(billAmount, user);
		if (amountAfterDiscount != 0) {
			// Discount not applied, apply $ 5 on every 100
			if (billAmount > 100) {
				double count = billAmount / 100;
				double discountAmount = 5 * count;
				amountAfterDiscount = billAmount - discountAmount;
			}
		}
		return amountAfterDiscount;

	}

	@Override
	public User createUser(User user) {
		return userService.addUser(user);
	}

}

package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.task.apis.TaskController;
import com.example.task.management.TaskManagement;
import com.example.task.models.Item;
import com.example.task.models.ItemType;
import com.example.task.models.User;
import com.example.task.models.UserType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TaskControllerTest {

	@org.mockito.junit.jupiter.MockitoSettings(strictness = Strictness.WARN)
	
	@InjectMocks
	TaskController taskController;

	@Mock
	TaskManagement repo;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddUser() {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest();
			RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
			User user = new User("Mounir", "Majed", new UserType(1l));

			ResponseEntity<Object> responseEntity = taskController.createUser(user);

			assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
			assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCreateBill() {
		try {
			// given
			Item item1 = new Item("Cheece", 50, new ItemType(1l));
			Item item2 = new Item("Mobile", 4000, new ItemType(2l));
			Item item3 = new Item("shose", 1000, new ItemType(2l));
			Item items = new Item();
			List<Item> list = new ArrayList<>();
			list.add(item1);
			list.add(item2);
			list.add(item3);

			Map<String, String> map = new HashMap<String, String>();

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(list);

			map.put("list", json);
			map.put("userId", "1");
			// when
			Double result = taskController.createBill(map);

			// then
			assertThat(result).isEqualTo(3500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

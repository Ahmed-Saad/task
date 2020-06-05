package com.example.task.apis;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.task.management.TaskManagement;
import com.example.task.models.Item;
import com.example.task.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class TaskController {

	@Autowired
	private TaskManagement manage;

	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "createBill", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Double createBill(@RequestBody Map<String, String> map) {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Item>>() {
		}.getType();
		List<Item> list = gson.fromJson(map.get("list").toString(), listType);
		return manage.createBill(list, Long.parseLong(map.get("userId")));
	}

	// There is no need to create post service to add a new user but I implement it
	// to use mockito in test
	@RequestMapping(value = "createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		manage.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}

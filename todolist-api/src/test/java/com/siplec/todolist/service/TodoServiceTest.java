package com.siplec.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.siplec.todolist.exception.ResourceNotFoundException;
import com.siplec.todolist.model.dao.Todo;
import com.siplec.todolist.repository.TodoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

	private static final String todoLabel = "Do somthing useful";
	private Todo todo1 = new Todo(todoLabel, false);

	@Autowired
	private TodoService todoService;

	@MockBean
	private TodoRepository todoRepository;

	@Before
	public void setUp() {
		todo1.setId(1);
		List<Todo> todos = new ArrayList<>();
		todos.add(todo1);

		Mockito.when(todoRepository.findAll()).thenReturn(todos);
		Mockito.when(todoRepository.findById(todo1.getId())).thenReturn(Optional.ofNullable(todo1));
	}

	@Test
	public void todoShouldBeFoundById() throws ResourceNotFoundException {
		Todo todoFetched = todoService.findById(todo1.getId());

		Assert.assertTrue("Todo should exist", todoFetched != null);

		Assert.assertEquals(todoFetched.getId(), todo1.getId());
	}

}

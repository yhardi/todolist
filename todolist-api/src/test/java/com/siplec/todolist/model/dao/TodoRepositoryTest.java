package com.siplec.todolist.model.dao;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.siplec.todolist.repository.TodoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {
	private static final String todoLabel = "Do somthing useful";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoRepository todoRepository;

	@Test
	public void whenFindAll_thenReturnTodos() {
		Todo todo1 = new Todo(todoLabel, false);
		entityManager.persist(todo1);
		entityManager.flush();

		// when
		List<Todo> todosFetshed = todoRepository.findAll();

		// then
		Assert.assertTrue("Fetched result should'nt be null or empty", CollectionUtils.isNotEmpty(todosFetshed));
	}

	@Test
	public void whenFindById_thenReturnTodo() {
		Todo todo1 = new Todo(todoLabel, false);
		entityManager.persist(todo1);
		entityManager.flush();

		// when
		Optional<Todo> todoFetshed = todoRepository.findById(todo1.getId());

		// then
		Assert.assertTrue("Fetched todo should not be null", todoFetshed.isPresent());
	}

}
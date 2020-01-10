package com.siplec.todolist.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siplec.todolist.exception.ResourceNotFoundException;
import com.siplec.todolist.model.dao.Todo;
import com.siplec.todolist.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public Todo findById(Long todoId) throws ResourceNotFoundException {
		return todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
	}

	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	public Page<Todo> findAll(int page, int size, String sort, String direction, String filter) {
		int sizeEdited = size;
		if (size < 1) {
			sizeEdited = Integer.MAX_VALUE;
		}
		Pageable pageable = PageRequest.of(page, sizeEdited, Sort.by(sort).ascending());
		return todoRepository.findAll(pageable);
	}

	public @Valid Todo save(@Valid Todo todo) {
		todo.setCreatedDate(LocalDateTime.now());
		todoRepository.save(todo);
		return todo;
	}

	public Todo update(Long todoId, Todo todoDetails) throws ResourceNotFoundException {
		Todo todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		todo.setLabel(todoDetails.getLabel());
		todo.setDone(todoDetails.isDone());
		todo.setLastModifiedDate(LocalDateTime.now());
		return todoRepository.save(todo);
	}

	public Boolean delete(Long todoId) throws ResourceNotFoundException {
		Todo todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		todoRepository.delete(todo);
		return Boolean.TRUE;
	}
}

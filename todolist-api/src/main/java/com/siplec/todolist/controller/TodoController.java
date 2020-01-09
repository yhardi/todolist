package com.siplec.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siplec.todolist.exception.ResourceNotFoundException;
import com.siplec.todolist.model.dao.Todo;
import com.siplec.todolist.model.dto.TodoDto;
import com.siplec.todolist.service.TodoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TodoService todoService;

	@GetMapping("")
	@ResponseBody
	public List<Todo> getAllTodos() {
		return todoService.findAll();
	}
	
	@GetMapping("/pagination")
	@ResponseBody
	public Page<Todo> getAllTodos(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "-1", required = false) int size,
			@RequestParam(value = "sort", defaultValue = "label", required = false) String sort,
			@RequestParam(value = "direction", defaultValue = "asc", required = false) String direction,
			@RequestParam(value = "filter", defaultValue = "", required = false) String filter) {

		return todoService.findAll(page, size, sort, direction, filter);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public TodoDto getTodoById(@PathVariable(value = "id") Long todoId) throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId);
		return convertToDto(todo);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public @Valid TodoDto createTodo(@Valid @RequestBody TodoDto todoDto) {
		Todo todo = convertToEntity(todoDto);
		Todo todoCreated = todoService.save(todo);
		return convertToDto(todoCreated);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateTodo(@PathVariable(value = "id") Long todoId, @Valid @RequestBody TodoDto todoDtoDetail)
			throws ResourceNotFoundException {
		Todo todoDetail = convertToEntity(todoDtoDetail);
		Todo todo = todoService.update(todoId, todoDetail);
		ResponseEntity.ok(todo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteTodo(@PathVariable(value = "id") Long todoId) throws ResourceNotFoundException {
		todoService.delete(todoId);
	}

	private TodoDto convertToDto(Todo todo) {
		return modelMapper.map(todo, TodoDto.class);
	}

	private Todo convertToEntity(TodoDto todoDto) {
		return modelMapper.map(todoDto, Todo.class);
	}
}

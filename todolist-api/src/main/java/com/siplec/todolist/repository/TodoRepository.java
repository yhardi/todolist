package com.siplec.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siplec.todolist.model.dao.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

package com.siplec.todolist;

import static org.springframework.boot.SpringApplication.run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

	static Logger log = LogManager.getLogger(TodoListApplication.class);

	public static void main(String[] args) {
		log.info("-------------------------------------------------------- Try start app Hello");
		run(TodoListApplication.class, args);
		log.info("-------------------------------------------------------- App Hello started");
	}

}

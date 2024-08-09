package com.app.todo_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo_management.dto.TodoDto;
import com.app.todo_management.service.TodoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

	private TodoService todoService;

	// build add Todo REST API

	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savedDto = todoService.addTodo(todoDto);

		return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		TodoDto todoDto = todoService.getTodo(todoId);
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos() {

		List<TodoDto> todos = todoService.getAllTodos();
		return ResponseEntity.ok(todos);
	}

	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
		TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
		return ResponseEntity.ok(updatedTodo);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo deleted successfully");
	}
     
	
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
		TodoDto updatedTodo = todoService.completeTodo(todoId);

		return ResponseEntity.ok(updatedTodo);

	}
	
	
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
	TodoDto upadtedTodo =	todoService.inCompleteTodo(todoId);
	
	return ResponseEntity.ok(upadtedTodo);
		
	}

}

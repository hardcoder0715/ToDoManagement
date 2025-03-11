package com.dev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.TodoDto;
import com.dev.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
//@PreAuthorize("permitAll")
public class TodoController {
 
	
	private TodoService todoService;

	// build add todo api
	@PostMapping
	@PreAuthorize("hasRole('addmin') ")
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savedTodo = todoService.addTodo(todoDto);

		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);

	}

	// get todo rest api

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('admin' , 'user')")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
		TodoDto getTodoByid = todoService.getTodo(id);

		return new ResponseEntity<TodoDto>(getTodoByid, HttpStatus.OK);

	}

	// get all todo
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('admin' , 'user')")
	public ResponseEntity<List<TodoDto>> getAllTodo() {
		List<TodoDto> allTodo = todoService.getAllTodo();

		return new ResponseEntity<List<TodoDto>>(allTodo, HttpStatus.OK);

	}

	// update todo
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id) {
		TodoDto updateTodo = todoService.updateTodo(todoDto, id);

		return new ResponseEntity<TodoDto>(updateTodo, HttpStatus.OK);

	}

	// delete todo by id
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);

		return new ResponseEntity<>("todo Deleted Successfully with id  " + id, HttpStatus.OK);
	}

	@PatchMapping("/{id}/complete")
	@PreAuthorize("hasAnyRole('admin' , 'user')")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {

		TodoDto completeTodo = todoService.completeTodo(id);

		return new ResponseEntity<TodoDto>(completeTodo, HttpStatus.OK);
	}

	// incomplete todo
	@PatchMapping("{id}/incomplete")
	@PreAuthorize("hasAnyRole('admin' , 'user')")
	public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id) {
		TodoDto inCompleteTodo = todoService.inCompleteTodo(id);

		return new ResponseEntity<TodoDto>(inCompleteTodo, HttpStatus.OK);
	}

}

package com.dev.service;

import java.util.List;

import com.dev.dto.TodoDto;

public interface TodoService {


	//add todo
	public TodoDto addTodo(TodoDto todoDto); 
	
	//get todo
	
	public TodoDto getTodo(Long id);
	
	
	//get all TOdos rest api
	
	List<TodoDto> getAllTodo();
	
	
	//update todos
	
	public TodoDto updateTodo(TodoDto todoDto, Long id);
	
	
	// dlete todo
	public void deleteTodo(Long id);
	
	
	// complete todo
	
	TodoDto completeTodo(Long id);
	
	
	// incomplete todo
	
		TodoDto inCompleteTodo(Long id);
	
	
	
	
}

package com.dev.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dev.dto.TodoDto;
import com.dev.entity.Todo;
import com.dev.exception.ResourceNotFoundException;
import com.dev.repository.TodoRepository;
import com.dev.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceIMPL implements TodoService {

	private TodoRepository todoRepository;

	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {

		// convert tododto into todoJpa entity
//		Todo todo = new Todo();
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());

		// by using model mapper
		Todo todo = modelMapper.map(todoDto, Todo.class);

		// todo jpa entity
		Todo savedTodo = todoRepository.save(todo);

		// convert saved Jpa entity into todoDto

//		TodoDto savedTodoDto = new TodoDto();
//		savedTodoDto.setId(savedTodo.getId());
//		savedTodoDto.setTitle(savedTodo.getTitle());
//		savedTodoDto.setDescription(savedTodo.getDescription());
//		savedTodoDto.setCompleted(savedTodo.isCompleted());
//
//		// TODO Auto-generated method stub

		// by using model mapper class
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

		return savedTodoDto;
	}

	// get ToDO method
	@Override
	public TodoDto getTodo(Long id) {
		Todo todoById = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id=" + id));
//				.orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id = " + id));

		return modelMapper.map(todoById, TodoDto.class);
	}

	// get all Todo

	@Override
	public List<TodoDto> getAllTodo() {

		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {

		Todo updateTodo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("To do not found with id= " + id));

		updateTodo.setTitle(todoDto.getTitle());
		updateTodo.setDescription(todoDto.getDescription());
		updateTodo.setCompleted(todoDto.isCompleted());

		Todo updatedTodo = todoRepository.save(updateTodo);

		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	// delete todo

	@Override
	public void deleteTodo(Long id) {
		Todo deleteTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("to do not found with id =" + id));

		todoRepository.deleteById(id);
		
	}
	
	
	//complete todo

	@Override
	public TodoDto completeTodo(Long id) {
		Todo completeTodo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("To do not Found with id =" + id));
		
		
		completeTodo.setCompleted(true);
		
		Todo completedTodo = todoRepository.save(completeTodo);
		return modelMapper.map(completedTodo, TodoDto.class);
	}

	//Incomplete todo
	
	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo incompleteTodo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("To do not found with given id=" + id));
		
		incompleteTodo.setCompleted(Boolean.FALSE);
		
		Todo inCompletedTodo = todoRepository.save(incompleteTodo);
		
		return modelMapper.map(inCompletedTodo, TodoDto.class);
	}

}

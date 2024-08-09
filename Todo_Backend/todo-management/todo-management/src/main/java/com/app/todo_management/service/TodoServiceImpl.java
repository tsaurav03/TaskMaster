package com.app.todo_management.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.todo_management.custom_exception.ResourceNotFoundException;
import com.app.todo_management.dto.TodoDto;
import com.app.todo_management.entity.Todo;
import com.app.todo_management.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;

	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {

		// convert TodoDto into Todo Jpa Entity
		/*
		 * Todo todo = new Todo(); todo.setTitle(todoDto.getTitle());
		 * todo.setDescription(todoDto.getDescription());
		 * todo.setCompleted(todoDto.isCompleted());
		 */

		Todo todo = modelMapper.map(todoDto, Todo.class);

		// Todo Jpa entity
		Todo savedTodo = todoRepository.save(todo);

		// convert saved Todo Jpa entiy object into TodoDto Object
		/*
		 * TodoDto saveTodoDto = new TodoDto(); saveTodoDto.setId(savedTodo.getId());
		 * saveTodoDto.setTitle(savedTodo.getTitle());
		 * saveTodoDto.setDescription(savedTodo.getDescription());
		 * saveTodoDto.setCompleted(savedTodo.isCompleted());
		 */

		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not Found with id" + id));

		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));

		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());

		Todo upadatedTodo = todoRepository.save(todo);

		return modelMapper.map(upadatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
		todoRepository.deleteById(id);

	}

	@Override
	public TodoDto completeTodo(Long id) {

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
		todo.setCompleted(Boolean.TRUE);
		Todo upadtedTodo = todoRepository.save(todo);

		return modelMapper.map(upadtedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo( Long id) {

		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
		todo.setCompleted(Boolean.FALSE);

		Todo updatedTodo = todoRepository.save(todo);

		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}

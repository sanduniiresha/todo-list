package com.wired2perform.assignment.service;

import com.wired2perform.assignment.Exception.TodoNotFoundException;
import com.wired2perform.assignment.dto.TodoDTO;
import com.wired2perform.assignment.entity.Todo;
import com.wired2perform.assignment.entity.User;
import com.wired2perform.assignment.repository.TodoRepository;
import com.wired2perform.assignment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    public TodoDTO saveTodo(TodoDTO todoDTO) {

        if (Objects.isNull(todoDTO.getId())) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        User user = userRepository.findById(todoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + todoDTO.getId() + " not found"));

        Todo todo = new Todo();
        todo.setTodos(todoDTO.getTodos());
        todo.setCreatedDate(todoDTO.getCreatedDate());
        todo.setUpdatedDate(todoDTO.getUpdatedDate());
        todo.setUser(user);
        todo.setArchived(false);

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    public TodoDTO updateTodo(TodoDTO updatedTodoDTO, Long todoId) {

        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo with id " + todoId + " not found"));

        modelMapper.map(updatedTodoDTO, existingTodo);

        Todo updatedTodoObject = todoRepository.save(existingTodo);
        return modelMapper.map(updatedTodoObject, TodoDTO.class);
    }

    public TodoDTO getTodoByUserIdAndTodoId(Long todoId) {

        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        return todoOptional.map(todo -> modelMapper.map(todo, TodoDTO.class))
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

    }

    public List<TodoDTO> getTodosByUserId(Long userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        if (todos.isEmpty()) {
            throw new TodoNotFoundException("Todo not found");
        }
        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteTodo(Long todoId) {

        if (!todoRepository.existsById(todoId)) {
            throw new TodoNotFoundException("Todo with id " + todoId + " not found");
        }
        todoRepository.deleteById(todoId);
    }

    public void archiveTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo list not found"));

        todo.setArchived(true);
        todoRepository.save(todo);
    }

}

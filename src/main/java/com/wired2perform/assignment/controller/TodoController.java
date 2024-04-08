package com.wired2perform.assignment.controller;

import com.wired2perform.assignment.dto.TodoDTO;
import com.wired2perform.assignment.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TodoController provides method for Todos related operations
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * Create todos for given details
     * @param todoDTO userDTO with new todo details
     * @return newly created todo details
     */
    @PostMapping(path = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> createTodo(@Validated @RequestBody(required = true) TodoDTO todoDTO) {
        TodoDTO newTodo = todoService.saveTodo(todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);

    }

    /**
     * Retrieve a todo belongs to a user
     * @param todoId todoId
     * @return todo details
     */
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoDTO> getTodoByTodoId(@PathVariable Long todoId) {
        TodoDTO todo = todoService.getTodoByUserIdAndTodoId(todoId);
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    /**
     * Retrieve all todos belongs to a user
     * @param userId userId
     * @return todos list
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TodoDTO>> getTodosByUserId(@PathVariable Long userId) {
        List<TodoDTO> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

    /**
     * Update todo details for given todoId
     * @param todoId todoId
     * @param updatedTodoRequest todoDTO with updated values
     * @return UpdatedTodoDTO
     */
    @PutMapping("/todo/{todoId}")
    ResponseEntity<TodoDTO> updateTodo(
            @PathVariable Long todoId,
            @RequestBody @Validated TodoDTO updatedTodoRequest
    ) {
        TodoDTO updatedTodo = todoService.updateTodo(updatedTodoRequest, todoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedTodo);
    }

    /**
     * Delete todo by given todoId
     * @param todoId todoId
     * @return ResponseEntity status
     */
    @DeleteMapping("/todo/{todoId}")
    ResponseEntity<Void> deletePartner(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Archiving todo for given todoId
     * @param todoId todoId
     * @return ResponseEntity status
     */
    @PostMapping("/{todoId}/archive")
    public ResponseEntity<String> archiveTodoList(@PathVariable Long todoId) {
        todoService.archiveTodo(todoId);
        return ResponseEntity.ok("Todo list archived successfully");
    }

}

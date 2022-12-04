package org.aadi.todoapp.spring3.service;

import org.aadi.todoapp.spring3.model.TodoItem;
import org.aadi.todoapp.spring3.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class TodoItemService {
	OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );

    @Autowired
    private TodoItemRepository todoItemRepository;

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
        	//Instant instant = Instant.now();
        	//ZonedDateTime timeInIndia = instant.atZone(ZoneId.of("Asia/Kolkata"));
            todoItem.setCreatedAt(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        }
        todoItem.setUpdatedAt(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return todoItemRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }

}
package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private static final String ID_PATH = "/{id}";
    private static final String ID = "id";
    private static final int INDEX_START = 0;

    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    private Long generateId() {
        return ++newId;
    }

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public Task postTask(@RequestBody final Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @PatchMapping(ID_PATH)
    @PutMapping(ID_PATH)
    public Task updateTask(@PathVariable(ID) final Long id, @RequestBody final Task newTask) throws Exception {
        Optional<Task> taskOptional = tasks.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst();
        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        taskOptional.get().setTitle(newTask.getTitle());
        return taskOptional.get();
    }

    @DeleteMapping(ID_PATH)
    public void deleteTask(@PathVariable(ID) final Long id) {
        IntStream.range(INDEX_START, tasks.size())
                .filter(index -> Objects.equals(tasks.get(index).getId(), id))
                .findFirst()
                .ifPresent(tasks::remove);
    }
}

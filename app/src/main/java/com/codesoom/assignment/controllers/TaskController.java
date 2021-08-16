// TODO
// 1. Read Collection - GET /tasks => work in progress..
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    // @RequestMapping(path = "", method = RequestMethod.GET)
    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    // @RequestMapping(path = "", method = RequestMethod.POST)
    @PostMapping
    public String create() {
        return "Created";
    }

}

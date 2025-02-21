package com.codesoom.assignment.repository;


import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.storage.ListStorageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends ListStorageRepository<Task> {

    public Task update(Task target, Task source) {
        target.setTitle(source.getTitle());
        return target;
    }
}

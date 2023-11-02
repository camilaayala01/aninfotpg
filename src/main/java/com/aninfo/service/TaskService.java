package com.aninfo.service;

import com.aninfo.model.Task;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task)
    {
        return taskRepository.save(task);
    }
    public void save(Task task)
    {
        return taskRepository.save(task);
    }


    public Optional<Transaction> findById(Long id) {
        return taskRepository.findById(id);
    }


    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Collection<Task> getTasksByProject( ArrayList<Long> ids) {
        return taskRepository.findAllById(ids);
    }
}

package com.aninfo.repository;

import com.aninfo.model.Task;
import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Override
    List<Task> findAll();

    @Override
    List<Task> findAllById(Iterable<Long> ids);
}

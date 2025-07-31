package org.kubernetes.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    private final TodosRepo todosRepo;
    public List<String> getAll() {
        return todosRepo.findAll().stream().map(
                Todo::getTodo
        ).toList();
    }

    public void addTodo(String todo) {
        if (todo.length()>140)
            log.error("this todo : {} have more than 140 characters", todo);
        else {
            log.info(todo);
            todosRepo.save(Todo.builder().todo(todo).build());
        }
    }
}

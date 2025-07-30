package org.kubernetes.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodosRepo todosRepo;
    public List<String> getAll() {
        return todosRepo.findAll().stream().map(
                Todo::getTodo
        ).toList();
    }

    public void addTodo(String todo) {
        todosRepo.save(Todo.builder().todo(todo).build());
    }
}

package org.kubernetes.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private List<String>todos=new ArrayList<>();
    public List<String> getAll() {
        return todos;
    }

    public void addTodo(String todo) {
        todos.add(todo);
    }
}

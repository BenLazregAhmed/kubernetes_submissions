package org.kubernetes.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoRestController {
    private final TodoService todoService;
    @GetMapping
    public List<String>todos(){
        return todoService.getAll();
    }
    @PostMapping
    public ResponseEntity<String>addTodo(
            @RequestBody TodoRequest todoRequest
    ){
        todoService.addTodo(todoRequest.todo());
        return ResponseEntity.ok("todo added successfully !!");
    }
}

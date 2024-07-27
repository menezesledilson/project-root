package com.crud.project_root.controllers;

import com.crud.project_root.domain.Task;
import com.crud.project_root.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTaskAll() {
        List<Task> Tasks = taskService.listTaskAll();
        return ResponseEntity.ok().body(Tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTaskById(@PathVariable Long id) {
        Task task = taskService.searchTaskId(id);

        // Cria um mapa para a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task found.");
        response.put("task", task);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> postTask(@RequestBody Task task) {
        Task taskSalve = taskService.createTask(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskSalve.getId())
                .toUri();

        // Cria um mapa para a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task created successfully.");
        response.put("createdTask", taskSalve);

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        // Atualiza a tarefa usando o serviço
        Task updatedTask = taskService.updateTask(id, task);

        // Cria um mapa para a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task updated successfully.");
        response.put("updatedTask", updatedTask);

        // Retorna o status 200 OK com o mapa de resposta
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

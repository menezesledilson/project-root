package com.crud.project_root.controllers;


import com.crud.project_root.models.Tarefa;
import com.crud.project_root.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/task")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/all")
    public ResponseEntity<List<Tarefa>> getTaskAll() {
        List<Tarefa> tarefas = tarefaService.listTaskAll();
        return  ResponseEntity.ok().body(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTaskById(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.searchTaskId(id);
        return ResponseEntity.ok().body(tarefa);
    }
    @PostMapping("/create")
    public ResponseEntity<Tarefa> postTask(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaService.createTask(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaSalva.getId())
                .toUri();
        return ResponseEntity.created(uri).body(tarefaSalva);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        // Atualiza a tarefa usando o serviço
        Tarefa updatedTask = tarefaService.updateTask(id, tarefa);

        // Cria um mapa para a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task updated successfully.");
        response.put("updatedTask", updatedTask);

        // Retorna o status 200 OK com o mapa de resposta
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        tarefaService.deleteTask(id);
                return ResponseEntity.noContent().build();
    }

}

package com.crud.project_root.controllers;

import com.crud.project_root.models.Tarefa;
import com.crud.project_root.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService  tarefaService;

    @GetMapping("/all")
    public List<Tarefa> getAll() {
        return this.tarefaService.getAll();
    }

    @GetMapping("/all/{id}")
    public Optional<Tarefa> getTarefaId(@PathVariable Long id){
        return tarefaService.getTarefaId(id);
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteTarefa(@PathVariable("id") Long id) {
        tarefaService.deleteTarefa(id);
    }

}

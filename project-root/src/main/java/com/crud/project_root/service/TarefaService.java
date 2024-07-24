package com.crud.project_root.service;

import com.crud.project_root.models.Tarefa;
import com.crud.project_root.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getAll(){
        return this.tarefaRepository.findAll();
    }
    public Optional<Tarefa>getTarefaId(Long id){
        return  this.tarefaRepository.findById(id);
    }
    public  void deleteTarefa(Long id){
        this.tarefaRepository.deleteById(id);
    }
}

package com.crud.project_root.service;

import com.crud.project_root.models.Tarefa;
import com.crud.project_root.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getAll(){

        return this.tarefaRepository.findAll();
    }
    public Optional<Tarefa>getTarefaId(Long id){

        return  this.tarefaRepository.findById(id);
    }
    public  void deleteTarefa(Long id){

        this.tarefaRepository.deleteById(id);
    }

    public  Tarefa criarTarefa(Tarefa tarefa){
        return this.tarefaRepository.save(tarefa);
    }

    public Tarefa editarTarefa(Long id,Tarefa tarefa){
       tarefa.setId(id);
        return  this.tarefaRepository.save(tarefa);
    }

}

package com.crud.project_root.service;

import com.crud.project_root.models.Tarefa;
import com.crud.project_root.repositories.TarefaRepository;
import com.crud.project_root.service.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listTaskAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa searchTaskId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    public Tarefa createTask(Tarefa tarefa) {
        tarefa.setId(null);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa updateTask(Long id, Tarefa tarefa) {
        // Verifica se a tarefa existe
        Optional<Tarefa> existingTaskOpt = tarefaRepository.findById(id);

        if (!existingTaskOpt.isPresent()) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        // Recupera a tarefa existente
        Tarefa existingTask = existingTaskOpt.get();

        // Atualiza os campos da tarefa existente com os novos dados
        existingTask.setDescricao(tarefa.getDescricao());
        existingTask.setStatus(tarefa.getStatus());

        // Salva e retorna a tarefa atualizada
        return tarefaRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        // Antes de deletar, verificamos se a tarefa existe
        if (!tarefaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id);
        }

        this.tarefaRepository.deleteById(id);
    }
}

//https://pt.aliexpress.com/item/1005006727659597.html?spm=a2g0o.detail.0.0.6611TubsTubsjr&mp=1&gatewayAdapt=glo2bra
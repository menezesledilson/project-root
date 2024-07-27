package com.crud.project_root.service;

import com.crud.project_root.domain.Task;
import com.crud.project_root.repositories.TaskRepository;
import com.crud.project_root.service.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> listTaskAll() {
        return taskRepository.findAll();
    }

    public Task searchTaskId(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        // Verifica se a tarefa existe
        Optional<Task> existingTaskOpt = taskRepository.findById(id);

        if (!existingTaskOpt.isPresent()) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        // Recupera a tarefa existente
        Task existingTask = existingTaskOpt.get();

        // Atualiza os campos da tarefa existente com os novos dados
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());

        // Salva e retorna a tarefa atualizada
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        // Antes de deletar, verificamos se a tarefa existe
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID: " + id);
        }

        this.taskRepository.deleteById(id);
    }
}

